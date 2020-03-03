package com.xp.glasses.web.wechat;

import com.xp.glasses.utils.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/cart/")
public class WeChatCartController {


    @RequestMapping("add")
    public BaseResponse addCart(){


        return null;
    }

}
