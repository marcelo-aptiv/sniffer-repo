package com.acp.aptiv.environment.check.util;

import static com.acp.aptiv.environment.check.util.ESquad.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EService {

  DEPLOYMENT(B, "deployment", "Deployment", "acp-api-deployment", true),
  DEVICE_PROVISIONING(B, "device-provisioning", "Device Provisioning", "acp-api-device-provisioning", false),
  GLOBAL_PARAMETER(WW, "global-parameters","Global Parameters", "acp-api-global-parameters", false),
  LOGIC_BUILDER(WW, "logic-builder", "Logic Builder", "acp-api-logic-builder", false),
  DEPLOYMENT_PROCESSOR(B, "deployment-processor", "Deployment Processor", "acp-api-ota-deployment-processor", false),
  SOFTWARE_FILE(WW, "software-file", "Software Files", "acp-api-software-file", false),
  STATE_MACHINE(WW, "state-machine","State Machine", "acp-api-state-machine", false),
  SYNTHETIC(WW, "synthetic", "Synthetic", "acp-api-synthetic", false),
  UNIT_CONVERSION(WW, "unit-conversion", "Unit Conversion", "acp-api-unit-conversion", false),
  USER_MANAGEMENT(B, "user-management", "User Management", "acp-api-user-management", false),
  USER_PERSISTENCE(B, "user-persistence", "User Persistence", "acp-api-user-persistence", false),
  VEHICLE(B, "vehicle", "Vehicle", "acp-api-vehicle ", false),
  WKID(WW, "wkid", "Wkid", "acp-api-wkid", false),
  BATCH_JOBS(WW, "acp-api-batch-jobs", "Batch Jobs", "acp-api-batch-jobs", false),
  STATUS_MONITOR(WW, "acp-api-status-monitor", "Status Monitor", "acp-system-status-monitor-spring", false);

  private ESquad squad;
  private String actuator;
  private String name;
  private String gitRepo;
  private boolean cdtEnv;
}
