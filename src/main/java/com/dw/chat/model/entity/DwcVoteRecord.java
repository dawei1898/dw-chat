package com.dw.chat.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * <p>
 * 点赞/踩记录表
 * </p>
 *
 * @author dawei
 * @since 2025-04-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dwc_vote_record")
public class DwcVoteRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    @TableId(value = "vote_id", type = IdType.ASSIGN_ID)
    private Long voteId;

    /**
     * 内容ID（消息、评论等）
     */
    private String contentId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * up-点赞，down-点踩
     */
    private String voteType;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
