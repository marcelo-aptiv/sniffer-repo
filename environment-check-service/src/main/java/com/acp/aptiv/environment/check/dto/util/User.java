package com.acp.aptiv.environment.check.dto.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String email;
    private String name;

}