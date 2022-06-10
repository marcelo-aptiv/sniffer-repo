package com.acp.aptiv.sniffer.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EEnvironment {

  DEV("dev"), QA("qa"), RELEASE("release"), PROD("prod");

  private String name;

}
