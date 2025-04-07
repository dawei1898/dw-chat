package com.dw.chat.service;

import com.dw.chat.model.param.VoteParam;
import com.dw.chat.model.vo.VoteRecordVo;

import java.util.List;

/**
 * 点赞/踩记录服务
 *
 * @author dawei
 */

public interface VoteService {

    /**
     * 保存点赞/踩记录
     */
    String saveVote(VoteParam param);

    /**
     * 批量查询点赞/踩记录
     */
    List<VoteRecordVo> queryVoteList(List<String> contentIds, String userId);

}
