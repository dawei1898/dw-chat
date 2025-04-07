package com.dw.chat.model.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 点赞/踩入参
 */
@Data
public class VoteParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 内容ID（消息、评论等） */
    @NotBlank
    private String contentId;

    /** 用户ID */
    private String userId;

    /** up-点赞，down-点踩 , ""-取消 */
    private String voteType;

}
