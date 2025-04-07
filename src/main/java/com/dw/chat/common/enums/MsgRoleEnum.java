package com.dw.chat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MsgRoleEnum {

    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant"),
    FUNCTION("function"),
    TOOL("tool"),
    ;
    private final String name;
}