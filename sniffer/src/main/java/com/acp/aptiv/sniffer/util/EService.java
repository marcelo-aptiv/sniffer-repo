package com.acp.aptiv.sniffer.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EService {

  WKID("wkid", "Wkid"),
  STATE_MACHINE("state-machine","State Machine"),
  BATCH_JOBS("acp-api-batch-jobs", "Batch Jobs"),
  UNIT_CONVERSION("unit-conversion", "Unit Conversion"),
  GLOBAL_PARAMETER("global-parameters","GPL"),
  SOFTWARE_FILE("software-file", "SW"),
  USER_MANAGEMENT("user-management", "User Management"),
  VEHICLE("vehicle", "Vehicle"),
  USER_PERSISTENCE("user-persistence", "User Persistence"),
  SYNTHETIC("synthetic", "Synthetic"),
  LOGIC_BUILDER("logic-builder", "Logic Builder"),
  DEPLOYMENT("deployment", "Deployment"),
  TRIGGERED_EVENT("acp-api-triggered-event", "Triggered Event"),
  USER_ALERT("acp-api-user-alert", "User Alert");

  private String actuator;
  private String name;
}
