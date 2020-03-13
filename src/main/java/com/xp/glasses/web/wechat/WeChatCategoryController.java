package com.xp.glasses.web.wechat;

import com.xp.glasses.entity.Category;
import com.xp.glasses.service.CategoryService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mrxiong
 */
@RestController
@RequestMapping("/weChat/category/")
public class WeChatCategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("childCategory")
    public BaseResponse childCategory(){


        List<Category> categories = categoryService.selectChild();

        return BaseResponse.build(categories);
    }
}
