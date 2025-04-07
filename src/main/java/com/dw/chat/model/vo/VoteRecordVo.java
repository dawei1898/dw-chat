package com.dw.chat.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 点赞/踩记录
 * 
 *  @author dawei
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteRecordVo implements java.io.Serializable {
    /** 版本号 */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long voteId;

    /** 内容ID（消息、评论等） */
    private String contentId;

    /** 用户ID */
    private String userId;

    /** up-点赞，down-点踩 */
    private String voteType;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

}