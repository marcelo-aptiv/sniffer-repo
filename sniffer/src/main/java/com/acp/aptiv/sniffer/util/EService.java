package com.acp.aptiv.sniffer.util;

import static com.acp.aptiv.sniffer.util.ESquad.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EService {

  DEPLOYMENT(B, "deployment", "Deployment", "acp-api-deployment"),
  DEVICE_PROVISIONING(B, "device-provisioning", "Device Provisioning", "acp-api-device-provisioning"),
  GLOBAL_PARAMETER(WW, "global-parameters","GPL", "acp-api-global-parameters"),
  LOGIC_BUILDER(WW, "logic-builder", "Logic Builder", "acp-api-logic-builder"),
  DEPLOYMENT_PROCESSOR(B, "deployment-processor", "Deployment Processor", "acp-api-ota-deployment-processor"),
  SOFTWARE_FILE(WW, "software-file", "SW", "acp-api-software-file"),
  STATE_MACHINE(WW, "state-machine","State Machine", "acp-api-state-machine"),
  SYNTHETIC(WW, "synthetic", "Synthetic", "acp-api-synthetic"),
  UNIT_CONVERSION(WW, "unit-conversion", "Unit Conversion", "acp-api-unit-conversion"),
  USER_MANAGEMENT(B, "user-management", "User Management", "acp-api-user-management"),
  USER_PERSISTENCE(B, "user-persistence", "User Persistence", "acp-api-user-persistence"),
  VEHICLE(B, "vehicle", "Vehicle", "acp-api-vehicle "),
  WKID(WW, "wkid", "Wkid", "acp-api-wkid"),
  BATCH_JOBS(WW, "acp-api-batch-jobs", "Batch Jobs", "acp-api-batch-jobs"),
  STATUS_MONITOR(WW, "acp-api-status-monitor", "Status Monitor", "acp-system-status-monitor-spring");

  private ESquad squad;
  private String actuator;
  private String name;
  private String gitRepo;
}
