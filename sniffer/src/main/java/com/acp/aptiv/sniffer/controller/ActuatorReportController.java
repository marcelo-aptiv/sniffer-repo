package com.acp.aptiv.sniffer.controller;

import com.acp.aptiv.sniffer.service.ActuatorReportService;
import com.acp.aptiv.sniffer.util.EService;
import com.acp.aptiv.sniffer.util.FileGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/report")
public class ActuatorReportController {

  private final ActuatorReportService actuatorReportService;

  @GetMapping(value = "/csv")
  public ResponseEntity processActuatorReportCsv(@RequestParam(value = "serviceName", required = false) EService service){

    log.info("Started Actuator explorer");

    var report = actuatorReportService.process(service);

    var resource = FileGenerator.getFile(report);

    log.info("Finished Actuator explorer with {} results", report.size());

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @GetMapping(value = "/table")
  public ResponseEntity processActuatorReportTable(@RequestParam(value = "serviceName", required = false) EService service){

    log.info("Started Actuator explorer");

    var report = actuatorReportService.process(service);

    log.info("Finished Actuator explorer with {} results", report.size());

    return ResponseEntity.ok(report);
  }
}
