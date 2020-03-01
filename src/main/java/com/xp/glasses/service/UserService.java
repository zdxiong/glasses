package com.xp.glasses.service;

import com.xp.glasses.entity.User;

/**
 * @author Mrxiong
 * @date 2020/01/10
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据openId 获取用户信息
     * @param openid
     * @return
     */
    User selectByWeChatId(String openid);
}
