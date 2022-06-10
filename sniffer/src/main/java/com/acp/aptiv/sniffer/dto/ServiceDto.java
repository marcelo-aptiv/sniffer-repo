package com.acp.aptiv.sniffer.dto;

import static com.acp.aptiv.sniffer.util.EEnvironment.PROD;
import static java.lang.String.format;

import com.acp.aptiv.sniffer.util.EEnvironment;
import com.acp.aptiv.sniffer.util.EService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {

  private EService service;
  private EEnvironment environment;
  private Integer order;

  public String getUrl() {
    if (environment.equals(PROD)) {
      return format("https://api.aptivconnect.app/%s/actuator/info", service.getActuator());
    }
    if (service.isCdtEnv() && environment.equals(EEnvironment.DEV)) {
      return format("https://cdt.api.aptivconnect.app/%s/actuator/info", service.getActuator());
    }
    return format("https://%s.api.aptivconnect.app/%s/actuator/info", environment, service.getActuator());
  }

  public String getName() {
    return service.getName();
  }

  public String getSquad() {
    return service.getSquad().name();
  }
}
