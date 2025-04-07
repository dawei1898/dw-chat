package com.dw.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dw.chat.common.exception.BizException;
import com.dw.chat.dao.UserMapper;
import com.dw.chat.model.entity.DwcUser;
import com.dw.chat.model.param.RegisterParam;
import com.dw.chat.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务
 *
 * @author dawei
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;


    /**
     * 注册用户
     */
    @Override
    public void register(RegisterParam param) {
        // 校验信息
        QueryWrapper<DwcUser> nameQueryWrapper = new QueryWrapper<>();;
        nameQueryWrapper.setEntity(DwcUser.builder().name(param.getUsername()).build());
        List<DwcUser> existUser = userMapper.selectList(nameQueryWrapper);
        if (CollectionUtils.isNotEmpty(existUser)) {
            throw new BizException("用户名已存在!");
        }
        QueryWrapper<DwcUser> emailQueryWrapper = new QueryWrapper<>();;
        emailQueryWrapper.setEntity(DwcUser.builder().email(param.getEmail()).build());
        List<DwcUser> existUser2 = userMapper.selectList(emailQueryWrapper);
        if (CollectionUtils.isNotEmpty(existUser2)) {
            throw new BizException("邮箱已注册!");
        }
        // 保存用户
        DwcUser dwcUser = DwcUser.builder()
                .name(param.getUsername())
                .password(param.getPassword())
                .email(param.getEmail())
                .build();
        userMapper.insert(dwcUser);
    }


}
