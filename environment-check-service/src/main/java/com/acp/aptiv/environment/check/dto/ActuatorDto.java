package com.acp.aptiv.environment.check.dto;

import com.acp.aptiv.environment.check.dto.util.Git;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ActuatorDto {

  public static final String[] HEADER = {"SERVICE",
      "ENV", "URL", "BRANCH",
      "TIME",
      "MESSAGE",
      "ABBREV",
      "FULL",
      "LAST COMMIT NAME",
      "LAST COMMIT EMAIL",
      "LAST VERSION",
      "LAST BUILD NAME",
      "LAST BUILD EMAIL",
      "STATUS"};

  @Setter
  @JsonProperty(value = "service")
  private ServiceDto serviceDto;
  @JsonInclude(Include.NON_NULL)
  private Git git;
  @JsonInclude(Include.NON_EMPTY)
  private String status = "OK";

  public ActuatorDto(ServiceDto serviceDto, String status) {
    this.serviceDto = serviceDto;
    this.status = status;
  }

  public String[] toCSV() {
    String[] columns = new String[HEADER.length];
    columns[0] = serviceDto.getService().getName().toLowerCase();
    columns[1] = serviceDto.getEnvironment().getName();
    columns[2] = serviceDto.getActuator();

    if (Objects.nonNull(git)) {
      columns[3] = git.getBranch();
      if (Objects.nonNull(git.getCommit())) {
        columns[4] = git.getCommit().getTime();
        if (Objects.nonNull(git.getCommit().getMessage())) {
          columns[5] = git.getCommit().getMessage().getShortMessage();
        }
        if (Objects.nonNull(git.getCommit().getId())) {
          columns[6] = git.getCommit().getId().getAbbrev();
          columns[7] = git.getCommit().getId().getFull();
        }
        if (Objects.nonNull(git.getCommit().getUser())) {
          columns[8] = git.getCommit().getUser().getName();
          columns[9] = git.getCommit().getUser().getEmail();
        }
      }
      if (Objects.nonNull(git.getBuild())) {
        columns[10] = git.getBuild().getVersion();
        if (Objects.nonNull(git.getBuild().getUser())) {
          columns[11] = git.getBuild().getUser().getName();
          columns[12] = git.getBuild().getUser().getEmail();
        }
      }
    }
    columns[HEADER.length - 1] = status;
    return columns;
  }

  public String getRepository() {
    String repoUrl = "https://github.com/AptivConnectedServices/" + serviceDto.getService().getGitRepo();
    if (Objects.nonNull(git)) {
      return  repoUrl + "/compare/main..." + git.getBranch();
    }
    return repoUrl;
  }

  public boolean isSynced() {
    if (Objects.nonNull(git)) {
      return "main".equals(git.getBranch());
    }
    return false;
  }

  @JsonIgnore
  public String getServiceName() {
    return serviceDto.getName();
  }

  @JsonIgnore
  public Integer getServiceEnvironment() {
    return serviceDto.getOrder();
  }
}