package com.xp.glasses.web.wechat;

import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.constant.UserType;
import com.xp.glasses.entity.User;
import com.xp.glasses.service.UserService;
import com.xp.glasses.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/user")
public class WeChatUserController {

    private static final String loginUrl = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String appid = "wxd9ba10e267b763bc";
    private static final String secret = "3dba6f58dde7fa910c32825e9bb07e92";
    private static final String grant_type = "authorization_code";

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public BaseResponse login(@RequestParam("code") String js_code,
                              @RequestParam("encryptedData") String encryptedData,
                              @RequestParam("iv") String iv,
                              HttpSession session) throws Exception {
        if (StringUtils.isEmpty(js_code)){
            return BaseResponse.build(ResponseCode.INVALID_PARAMS);
        }
        // 登录
        Map jscode2session = jscode2session(js_code);

        if (jscode2session.containsKey("session_key") && jscode2session.containsKey("openid")){
            String session_key = (String) jscode2session.get("session_key");
            String openid = (String) jscode2session.get("openid");
            // 解密用户信息
            String decryptData = DecryptOfDiyIV.decryptData(encryptedData, session_key, iv);
            Map map = JacksonUtil.jsonToBean(decryptData, Map.class);
            User user = userService.selectByWeChatId(openid);
            if (Objects.isNull(user)){
                String id = IdUtils.initId();
                user = new User();
                user.setId(id);
                user.setAvatar((String) map.get("avatarUrl"));
                user.setEmail(null);
                user.setGender((Integer) map.get("gender"));
                user.setIdCard(null);
                user.setPassword(null);
                user.setPhone(null);
                user.setType(UserType.general.name());
                user.setUserName((String) map.get("nickName"));
                user.setCreateTime(new Date());
                user.setUpdateTime(null);
                user.setWeChat(openid);
                userService.insert(user);
            }
            String sessionId = session.getId();
            user.setWeChat(null);
            session.setAttribute("currentUser",user);
            Map<String,Object> res = new HashMap<>(1);
            res.put("userInfo",user);
            res.put("sessionId",sessionId);
            return BaseResponse.build(res);
        }
        return BaseResponse.build(ResponseCode.FAIL,"登录失败");
    }


    public Map jscode2session(String js_code){
        Map<String, String> loginParams = new HashMap<>(4);
        loginParams.put("appid", appid);
        loginParams.put("secret", secret);
        loginParams.put("js_code", js_code);
        loginParams.put("grant_type", grant_type);
        String response = HttpClientUtil.doGet(loginUrl, loginParams);
        return JacksonUtil.jsonToBean(response, Map.class);
    }
}
