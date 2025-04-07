package com.dw.chat.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dw.chat.common.exception.BizException;
import com.dw.chat.common.utils.RequestHolder;
import com.dw.chat.common.utils.UserContextHolder;
import com.dw.chat.components.auth.AuthConstant;
import com.dw.chat.components.auth.AuthUtil;
import com.dw.chat.components.auth.LoginUser;
import com.dw.chat.dao.UserMapper;
import com.dw.chat.model.entity.DwcUser;
import com.dw.chat.model.param.LoginParam;
import com.dw.chat.model.param.RegisterParam;
import com.dw.chat.model.vo.UserVo;
import com.dw.chat.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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


    /**
     * 用户登录
     */
    @Override
    public String login(LoginParam param) {
        // 查询用户
        DwcUser dwcUser = new DwcUser();
        dwcUser.setName(param.getUsername());
        QueryWrapper<DwcUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(dwcUser);
        DwcUser exist = userMapper.selectOne(queryWrapper);
        if (exist == null) {
            throw new BizException("用户不存在!");
        }
        if (!StringUtils.equals(param.getPassword(), exist.getPassword())) {
            throw new BizException("密码不正确!");
        }

        // 生成登录用户信息
        LoginUser loginUser = LoginUser.builder()
                .tokenId(IdUtil.fastSimpleUUID())
                .userId(exist.getId())
                .username(exist.getName())
                .ipaddr(RequestHolder.getHttpServletRequestIpAddress())
                .loginTime(System.currentTimeMillis())
                .expireTime(System.currentTimeMillis() + AuthConstant.EXPIRE_TIME)
                .build();

        // 生成token
        String token = AuthUtil.buildToken(loginUser);

        // 记录登录log
        return token;
    }

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        LoginUser loginUser = UserContextHolder.getUser();
        if (loginUser != null) {
            String tokenId = loginUser.getTokenId();
            AuthUtil.removeToken(tokenId);
        }
    }

    /**
     * 查询一个用户
     */
    @Override
    public UserVo queryUser(Long userId) {
        if (userId == null) {
            return null;
        }
        DwcUser dwcUser = userMapper.selectById(userId);
        if (dwcUser == null) {
            throw new BizException("用户不存在!");
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(dwcUser, userVo);
        return userVo;
    }

}
