package com.dw.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dw.chat.common.utils.UserContextHolder;
import com.dw.chat.dao.VoteRecordMapper;
import com.dw.chat.model.entity.DwcVoteRecord;
import com.dw.chat.model.param.VoteParam;
import com.dw.chat.model.vo.VoteRecordVo;
import com.dw.chat.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 点赞/踩记录服务
 *
 * @author dawei
 */

@Slf4j
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRecordMapper voteRecordMapper;

    /**
     * 保存点赞/踩记录
     */
    @Override
    public String saveVote(VoteParam param) {
        String userId = String.valueOf(UserContextHolder.getUserId());
        String contentId = param.getContentId();
        String voteType = param.getVoteType() == null ? "" : param.getVoteType();

        QueryWrapper<DwcVoteRecord> queryWrapper = new QueryWrapper<>();
        DwcVoteRecord query = DwcVoteRecord.builder()
                .contentId(contentId)
                .userId(Long.valueOf(userId))
                .build();
        queryWrapper.setEntity(query);
        DwcVoteRecord exist = voteRecordMapper.selectOne(queryWrapper);

        DwcVoteRecord dwcVoteRecord = DwcVoteRecord.builder()
                .contentId(contentId)
                .userId(Long.valueOf(userId))
                .voteType(voteType)
                .build();
        if (exist == null) {
            voteRecordMapper.insert(dwcVoteRecord);
        } else {
            dwcVoteRecord.setVoteId(exist.getVoteId());
            dwcVoteRecord.setUpdateTime(LocalDateTime.now());
            voteRecordMapper.updateById(dwcVoteRecord);
        }
        return String.valueOf(dwcVoteRecord.getVoteId());
    }


    /**
     * 批量查询点赞/踩记录
     */
    @Override
    public List<VoteRecordVo> queryVoteList(List<String> contentIds, String userId) {
        return voteRecordMapper.queryVotesByUserIdAndContentIds(contentIds, userId);
    }


}
