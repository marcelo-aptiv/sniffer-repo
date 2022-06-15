package com.acp.aptiv.environment.check.dto.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
public class Build {

  @JsonInclude(Include.NON_NULL)
  private String version;
  @JsonInclude(Include.NON_NULL)
  private User user;

}
