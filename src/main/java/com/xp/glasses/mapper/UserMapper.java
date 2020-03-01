package com.xp.glasses.mapper;

import com.xp.glasses.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @author Mrxiong
 * @date 2020/02/10
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User>{

    /**
     * 根据openid 获取用户
     * @param openid
     * @return
     */
    User selectByWeChatId(String openid);
}
