package com.xp.glasses.web;

import com.xp.glasses.apo.validate.ParamValidated;
import com.xp.glasses.entity.User;
import com.xp.glasses.mapper.UserMapper;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户-控制器
 *
 * @author Mrxiong
 * @date 2020/02/10
 */
@Controller
@RequestMapping("/user/")
@ResponseBody
@Validated
public class UserController {

    @Autowired
    UserMapper userMapper;
    /**
     * @param userId
     * @return
     * @Validate 标签用于验证实体类包装的对象参数
     */
    @RequestMapping("login")
    @ParamValidated
    public BaseResponse<User> getUser(User userId) {
        return BaseResponse.build(userMapper.selectById(userId.getId()));
    }
}
