package com.dw.chat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dw.chat.model.entity.DwcVoteRecord;
import com.dw.chat.model.vo.VoteRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 点赞/踩记录表 Mapper 接口
 * </p>
 *
 * @author dawei
 * @since 2025-04-07
 */
public interface VoteRecordMapper extends BaseMapper<DwcVoteRecord> {

    List<VoteRecordVo> queryVotesByUserIdAndContentIds(@Param("contentIds") List<String> contentIds,
                                                       @Param("userId") String userId);

}

