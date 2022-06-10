package com.acp.aptiv.sniffer.service;

import com.acp.aptiv.sniffer.dto.ActuatorDto;
import com.acp.aptiv.sniffer.dto.ServiceDto;
import com.acp.aptiv.sniffer.util.EService;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActuatorReportServiceImpl implements ActuatorReportService {

  private static final Set<ServiceDto> SERVICES;

  static {
    SERVICES = new HashSet<>();

    Stream.of(EService.values()).forEach(serviceName -> {
      SERVICES.add(new ServiceDto(serviceName, "dev"));
      SERVICES.add(new ServiceDto(serviceName, "cdt"));
      SERVICES.add(new ServiceDto(serviceName, "qa"));
      SERVICES.add(new ServiceDto(serviceName, "release"));
    });
  }

  private final RestTemplate restTemplate;

  @Override
  public List<ActuatorDto> process(EService serviceName) {
    try {
      return SERVICES.parallelStream()
          .filter(serviceDto -> {
            if (Objects.isNull(serviceName)) {
              return true;
            }
            return serviceDto.getService().equals(serviceName);
          })
          .map(serviceDto -> {
            log.info("Calling {} {} - {}", serviceDto.getEnvironment(), serviceDto.getService(), serviceDto.getUrl());
            try {
              var responseEntity = restTemplate
                  .exchange(serviceDto.getUrl(), HttpMethod.GET, null,
                      new ParameterizedTypeReference<ActuatorDto>() {
                      });
              var actuatorDto = responseEntity.getBody();
              actuatorDto.setServiceDto(serviceDto);
              return actuatorDto;
            } catch (Exception e) {
              log.error("Error triggering: {} - {}", serviceDto.getUrl(), e.getMessage());
              return new ActuatorDto(serviceDto, e.getMessage());
            }
          })
          .sorted(Comparator.comparing(actuatorDto -> actuatorDto.getServiceDto().getService()))
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }
}
