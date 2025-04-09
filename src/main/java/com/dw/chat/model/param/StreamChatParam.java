package com.dw.chat.model.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 流式聊天入参
 *
 * @author dawei
 */
@Data
public class StreamChatParam {

    @NotBlank(message = "不能为空")
    private String chatId;

    @NotBlank(message = "不能为空")
    private String content;

    private String modelId = "";

    /** 开启推理 */
    private boolean openReasoning;

    /** 开启网络搜索 */
    private boolean openSearch;

}
