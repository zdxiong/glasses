package com.xp.glasses.web;

import com.xp.glasses.apo.validate.ParamValidated;
import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.entity.Page;
import com.xp.glasses.entity.ReturnData;
import com.xp.glasses.entity.Shop;
import com.xp.glasses.entity.form.ShopInsertForm;
import com.xp.glasses.mapper.ShopMapper;
import com.xp.glasses.service.ShopService;
import com.xp.glasses.utils.BaseResponse;
import com.xp.glasses.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 门店-控制器
 *
 * @author Mrxiong
 * @date 2020/01/10
 */
@RequestMapping("/shop/")
@Controller
@Validated
public class ShopController {


    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopMapper shopMapper;

    /**
     * 添加门店
     *
     * @param
     * @return
     */
    @PostMapping(value = "/insert")
    @ParamValidated
    public String insertShop(ShopInsertForm shopInsertForm) throws IOException {
        shopService.insert(shopInsertForm);
        return "redirect:/shop/myShop";
    }

    /**
     * 分页查询门店列表页面
     *
     * @param page
     * @param pageSize
     * @param modelAndView
     * @return
     */
    @GetMapping("myShop")
    public ModelAndView shop(@RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "pageSize", defaultValue = "15") Integer pageSize,
                             ModelAndView modelAndView) {
        PageInfo<Shop> pageInfo = shopService.shopListPageQuery(page, pageSize);
        modelAndView.setViewName("shop");
        modelAndView.addObject("user", "夏鹏");
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }


    @GetMapping("shops")
    @ResponseBody
    public ReturnData shopList(Page page) {
        int offset = page.getOffset();
        int pageNumber = page.getLimit();
        Map<String, Object> map = new HashMap<>(2);
        map.put("start", offset);
        map.put("pageSize", pageNumber);
        List res = shopMapper.select(map);
        Integer count = shopMapper.count(null);
        ReturnData returnData = new ReturnData();
        returnData.setRows(res);
        returnData.setTotal(count);
        return returnData;
    }


    @RequestMapping("update")
    @ParamValidated
    public String update(Shop shop) throws Exception {
        shopService.update(shop);
        return "redirect:/shop/myShop";
    }


    @GetMapping("delete")
    public String deleteShopById(@RequestParam("id") String id) {
        shopService.deleteById(id);
        return "redirect:/shop/myShop";
    }

    @DeleteMapping("deleteMany")
    @ResponseBody
    public BaseResponse deleteManny(@RequestParam("ids[]") String[] ids) {
        if (ids == null || ids.length == 0) {
            return BaseResponse.build(ResponseCode.INVALID_PARAMS, "请选择删除的行", null);
        }

        List<String> strings = Arrays.asList(ids);
        shopService.deleteByIds(strings);

        return BaseResponse.build();


    }


    @GetMapping("find")
    @ResponseBody
    public BaseResponse selectShopById(@RequestParam String id) {
        return BaseResponse.build(shopService.selectById(id));
    }

    @GetMapping("shopInfo")
    @ResponseBody
    public BaseResponse shopInfoById(@RequestParam String id) {
        Shop shop = shopService.shopInfoById(id);
        if (null == shop) {
            return BaseResponse.build(ResponseCode.NOT_FOUND, "未找到信息", null);
        }
        return BaseResponse.build(shop);
    }


    /**
     * 1：添加 2-修改
     *
     * @param model
     * @param type
     * @return
     */
    @GetMapping("addOrEdit")
    public String add(Model model, Integer type, String id) {

        Object o = new ShopInsertForm();
        // 添加
        if (type == 1) {
            model.addAttribute("type", "add");
            model.addAttribute("action", "/shop/insert");
        }
        // 编辑
        if (type == 2) {
            model.addAttribute("type", "edit");
            model.addAttribute("action", "/shop/update");
            o = shopService.selectById(id);
        }
        // 查出其门店信息
        model.addAttribute("shop", o);
        return "addOrEditShop";
    }


    @ResponseBody
    @RequestMapping("all")
    public BaseResponse allShops() {
        List allShops = shopService.select(null);
        return BaseResponse.build(allShops);
    }
}
