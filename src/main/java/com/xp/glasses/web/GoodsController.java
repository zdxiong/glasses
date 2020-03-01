package com.xp.glasses.web;

import com.xp.glasses.apo.validate.ParamValidated;
import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.entity.*;
import com.xp.glasses.entity.form.GoodsForm;
import com.xp.glasses.service.CategoryService;
import com.xp.glasses.service.GoodsService;
import com.xp.glasses.service.ShopService;
import com.xp.glasses.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
 * @author Mrxiong
 */
@RequestMapping("goods")
@Controller
public class GoodsController {

    @Autowired
    ServerConfig serverConfig;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ShopService shopService;


    @RequestMapping("myGoods")
    public String goodsPage(@RequestParam(name = "page", defaultValue = "1") Integer page,
                            @RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize,
                            ModelAndView modelAndView) {

        return "goods";

    }


//    /**
//     * 1：添加 2-修改
//     *
//     * @param model
//     * @param type
//     * @return
//     */
//    @GetMapping("addOrEdit")
//    public String add(Model model, Integer type, String id) {
//        // 查询出类别
//        List<Category> categories = categoryService.selectChild();
//        // 获取门店
//        List<Shop> shops = shopService.select(null);
//        model.addAttribute("categories", categories);
//        model.addAttribute("shops", shops);
//        Goods goods = new Goods();
//        // 添加
//        if (type == 1) {
//            model.addAttribute("type", "add");
//            model.addAttribute("action", serverConfig.getUrl() + serverConfig.projectName + "/goods/insert");
//        }
//        // 编辑
//        if (type == 2) {
//            Object o = goodsService.select(new HashMap(1) {{
//                put("goodsId", id);
//            }});
//            goods = (Goods) ((List) o).get(0);
//            model.addAttribute("type", "edit");
//
//            model.addAttribute("action", serverConfig.getUrl() + serverConfig.projectName + "/goods/update");
//        }
//        model.addAttribute("goods", goods);
//        return "addOrEditGoods";
//    }


    @RequestMapping("list")
    @ResponseBody
//    @ParamValidated
    public ReturnData bootstrapTableData(GoodsQueryParam goodsQueryParam) {

        ReturnData returnData = goodsService.bootstrapTableData(goodsQueryParam);

        return returnData;
    }


    /**
     * 依据商品ID查询商品规格详情
     *
     * @param goodsId
     * @return
     */
    @RequestMapping("specifications")
    @ResponseBody
    public List<GoodsSpecification> specifications(@RequestParam String goodsId) {
        List<GoodsSpecification> specifications = goodsService.specifications(goodsId);
        return specifications;

    }


    @RequestMapping("insert")
    @ParamValidated
    public String insert(GoodsForm goodsForm) throws IOException {
        // 插入
        goodsService.insert(goodsForm);

        return "redirect:/goods/myGoods";
    }


    @RequestMapping("delete")
    @ResponseBody
    public BaseResponse delete(@RequestParam String id) {

        goodsService.deleteById(id);

        return BaseResponse.build();
    }

    @RequestMapping("update")
    public String update(GoodsForm goodsForm) throws IOException {

        String goodsId = goodsForm.getId();
        if (StringUtils.isEmpty(goodsId)) {
            return "redirect:/goods/myGoods";
        }
        goodsService.update(goodsForm);
        return "redirect:/goods/myGoods";
    }

    @RequestMapping("all")
    @ResponseBody
    public BaseResponse allGoods(){
        List<Goods> goods= goodsService.all();

        return BaseResponse.build(goods);
    }

}
