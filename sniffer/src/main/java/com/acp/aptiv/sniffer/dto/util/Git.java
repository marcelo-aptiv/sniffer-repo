package com.acp.aptiv.sniffer.dto.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Git {

  @JsonInclude(Include.NON_NULL)
  private String branch;
  @JsonInclude(Include.NON_NULL)
  private Commit commit;
  @JsonInclude(Include.NON_NULL)
  private Build build;

}
