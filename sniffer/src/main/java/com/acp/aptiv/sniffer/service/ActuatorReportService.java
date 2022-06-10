package com.acp.aptiv.sniffer.service;

import com.acp.aptiv.sniffer.dto.ActuatorDto;

import com.acp.aptiv.sniffer.util.EService;
import java.util.List;

public interface ActuatorReportService {

    List<ActuatorDto> process(EService serviceName);

}
