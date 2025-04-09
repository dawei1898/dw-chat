package com.dw.chat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelEnum {

    QWEN_MAX("qwen-max"),
    QWQ_32B("qwq-32b"),
    DEEPSEEK_V3("deepseek-v3"),
    DEEPSEEK_R1("deepseek-r1"),
    ;
    private final String name;
}