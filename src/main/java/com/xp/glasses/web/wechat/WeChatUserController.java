package com.xp.glasses.web.wechat;

import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.constant.UserType;
import com.xp.glasses.entity.User;
import com.xp.glasses.service.UserService;
import com.xp.glasses.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/user/")
public class WeChatUserController {

    Logger logger = LoggerFactory.getLogger(getClass());
    private static final String loginUrl = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String appid = "wxd9ba10e267b763bc";
    private static final String secret = "3dba6f58dde7fa910c32825e9bb07e92";
    private static final String grant_type = "authorization_code";


    private final String nickName = "nickName";
    private final String gender = "gender";
    private final String language = "language";
    private final String city = "city";
    private final String province = "province";
    private final String country = "country";
    private final String avatarUrl = "avatarUrl";
    private final String openId = "openId";


    @Autowired
    UserService userService;

    @RequestMapping("login")
    public BaseResponse login(String userInfo) throws Exception {

        if (StringUtils.isEmpty(userInfo)) {
            return BaseResponse.build(ResponseCode.INVALID_PARAMS, "未捕获到用户信息");
        }

        Map<String, Object> userInfoMap = JacksonUtil.jsonToBean(userInfo, Map.class);
        User user = new User();
        Date nowTime = new Date();

        StringBuffer addr = new StringBuffer();
        if (userInfoMap.containsKey(nickName)) {
            user.setUserName((String) userInfoMap.get(nickName));
        }
        if (userInfoMap.containsKey(gender)) {
            user.setGender((Integer) userInfoMap.get(gender));
        }
        if (userInfoMap.containsKey(language)) {
            user.setLanguage((String) userInfoMap.get(language));
        }
        if (userInfoMap.containsKey(country)) {
            addr.append(userInfoMap.get(country)).append("-");
        }
        if (userInfoMap.containsKey(province)) {
            addr.append(userInfoMap.get(province)).append("-");
        }
        if (userInfoMap.containsKey(city)) {
            addr.append(userInfoMap.get(city));
        }
        if (userInfoMap.containsKey(avatarUrl)) {
            user.setAvatar((String) userInfoMap.get(avatarUrl));
        }
        if (userInfoMap.containsKey(openId)) {
            user.setWeChat((String) userInfoMap.get(openId));
        }
        user.setAddr(addr.toString());
        // 已注册的用户
        User exsitUser = userService.selectByWeChatId(user.getWeChat());
        Map<String, Object> res = new HashMap<>(1);
        if (!Objects.isNull(exsitUser)) {
            // 修改其信息
            user.setId(exsitUser.getId());
            user.setUpdateTime(nowTime);
            userService.update(user);
            res.put("userId", exsitUser.getId());
            return BaseResponse.build(res);
        }
        String id = IdUtils.initId();
        user.setId(id);
        user.setCreateTime(nowTime);
        user.setUpdateTime(nowTime);
        user.setType(UserType.general.name());
        userService.insert(user);
        res.put("userId", id);
        return BaseResponse.build(res);
    }


    public Map jscode2session(String js_code) {
        Map<String, String> loginParams = new HashMap<>(4);
        loginParams.put("appid", appid);
        loginParams.put("secret", secret);
        loginParams.put("js_code", js_code);
        loginParams.put("grant_type", grant_type);
        String response = HttpClientUtil.doGet(loginUrl, loginParams);
        return JacksonUtil.jsonToBean(response, Map.class);
    }


    @RequestMapping("session")
    public BaseResponse session(@RequestHeader(value = "clientInfo") HttpHeaders httpHeaders, @RequestParam String code) {
        String clientInfo = httpHeaders.getFirst("clientInfo");
        logger.info("clientInfo:{}",clientInfo);

        if (StringUtils.isEmpty(code)) {
            return BaseResponse.build(ResponseCode.INVALID_PARAMS, "code 凭证不可为空");
        }

        Map jscode2session = jscode2session(code);

        if (jscode2session.containsKey("session_key") && jscode2session.containsKey("openid")) {
            return BaseResponse.build(jscode2session);
        }

        return BaseResponse.build(ResponseCode.FAIL, "登录失败");
    }
}
