package com.acp.aptiv.sniffer.service;

import com.acp.aptiv.sniffer.dto.ActuatorDto;
import com.acp.aptiv.sniffer.dto.ServiceDto;
import com.acp.aptiv.sniffer.util.EEnvironment;
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
      SERVICES.add(new ServiceDto(serviceName, EEnvironment.DEV, 1));
      SERVICES.add(new ServiceDto(serviceName, EEnvironment.QA, 2));
      SERVICES.add(new ServiceDto(serviceName, EEnvironment.RELEASE, 3));
      SERVICES.add(new ServiceDto(serviceName, EEnvironment.PROD, 4));
    });
  }

  private final RestTemplate restTemplate;

  @Override
  public List<ActuatorDto> process(EService serviceName) {
    try {
      Comparator<ActuatorDto> actuatorDtoComparator = Comparator
          .comparing(ActuatorDto::getServiceName)
          .thenComparing(ActuatorDto::getServiceEnvironment);

      return SERVICES.parallelStream()
          .filter(serviceDto -> {
            if (Objects.isNull(serviceName)) {
              return true;
            }
            return serviceDto.getService().equals(serviceName);
          })
          .map(serviceDto -> {
            log.info("Hitting Service: {} {} - {}", serviceDto.getEnvironment().getName(), serviceDto.getService(), serviceDto.getActuator());
            try {
              var responseEntity = restTemplate
                  .exchange(serviceDto.getActuator(), HttpMethod.GET, null,
                      new ParameterizedTypeReference<ActuatorDto>() {
                      });
              var actuatorDto = responseEntity.getBody();
              actuatorDto.setServiceDto(serviceDto);
              return actuatorDto;
            } catch (Exception e) {
              log.error("Error triggering: {} - {}", serviceDto.getActuator(), e.getMessage());
              return new ActuatorDto(serviceDto, e.getMessage());
            }
          })
          .sorted(actuatorDtoComparator)
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }
}
