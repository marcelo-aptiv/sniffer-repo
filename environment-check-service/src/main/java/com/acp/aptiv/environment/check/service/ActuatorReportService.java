package com.acp.aptiv.environment.check.service;

import com.acp.aptiv.environment.check.dto.ActuatorDto;

import com.acp.aptiv.environment.check.util.EService;
import java.util.List;

public interface ActuatorReportService {

    List<ActuatorDto> process(EService serviceName);

}
