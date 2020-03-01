package com.xp.glasses.web;

import com.xp.glasses.entity.Category;
import com.xp.glasses.service.CategoryService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mrxiong
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("childCategory")
    @ResponseBody
    public BaseResponse childCategories() {
        List<Category> categories = categoryService.selectChild();
        return BaseResponse.build(categories);
    }

}
