package com.xp.glasses.service.impl;

import com.xp.glasses.entity.User;
import com.xp.glasses.mapper.UserMapper;
import com.xp.glasses.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mrxiong
 * @date 2020/01/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @SuppressWarnings("unused")
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        super(userMapper);
        this.userMapper = userMapper;
    }


    @Override
    public User selectByWeChatId(String openid) {
        return userMapper.selectByWeChatId(openid);
    }
}
