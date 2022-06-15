package com.acp.aptiv.environment.check.dto.util;

import com.acp.aptiv.environment.check.util.CommitDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonDeserialize(using = CommitDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

  @JsonInclude(Include.NON_NULL)
  private String time;
  @JsonInclude(Include.NON_NULL)
  private User user;
  @JsonInclude(Include.NON_NULL)
  private Message message;
  @JsonInclude(Include.NON_NULL)
  private Id id;
}
