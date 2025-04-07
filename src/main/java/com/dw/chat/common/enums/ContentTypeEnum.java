package com.dw.chat.common.enums;



/**
 * 消息内容类型枚举
 */
public enum ContentTypeEnum {

    TEXT("text","文本"),
    IMAGE("image","图像");

    private final String code;

    private final String message;

    ContentTypeEnum(String code, String message) {
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
