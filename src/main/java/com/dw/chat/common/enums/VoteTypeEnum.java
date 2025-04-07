package com.dw.chat.common.enums;


import java.util.Objects;

/**
 * 点赞/踩类型枚举
 */
public enum VoteTypeEnum {

    UP("up","点赞"),
    DOWN("down","点踩"),
    CANCEL("","取消");

    private final String code;

    private final String message;

    VoteTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMsgByCode(String code){
        for (VoteTypeEnum value : VoteTypeEnum.values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value.getMessage();
            }
        }
        return "";
    }

}
