package com.acp.aptiv.environment.check.dto.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Message{

    @JsonProperty(value = "short")
    private String shortMessage;

}
