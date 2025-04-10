package com.dw.chat.common.enums;



/**
 * 消息类型枚举
 */
public enum MsgTypeEnum {

    USER("user","用户"),
    AI("ai","机器人");

    private final String code;

    private final String message;

    MsgTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
