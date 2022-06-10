package com.acp.aptiv.sniffer.dto;

import static java.lang.String.format;

import com.acp.aptiv.sniffer.util.EService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {

  private EService service;
  private String environment;

  public String getUrl() {
    return format("https://%s.api.aptivconnect.app/%s/actuator/info", environment, service.getActuator());
  }

  public String getName() {
    return service.getName();
  }

  public String getSquad() {
    return service.getSquad().name();
  }
}
