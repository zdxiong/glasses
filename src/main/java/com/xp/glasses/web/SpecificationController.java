package com.xp.glasses.web;

import com.xp.glasses.apo.validate.ParamValidated;
import com.xp.glasses.entity.form.SpeForm;
import com.xp.glasses.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @author Mrxiong
 */
@Controller
@RequestMapping("/spe/")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;


    /**
     * 添加规格
     *
     * @param speForm
     * @return
     */
    @RequestMapping("insert")
    @ParamValidated
    public String insert(SpeForm speForm) throws IOException {
        Long attachPrice = speForm.getAttachPrice();
        if (attachPrice != null) {
            speForm.setAttachPrice(speForm.getAttachPrice() * 100);
        }
        specificationService.insert(speForm);
        return "redirect:/goods/myGoods";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam String id) {

        specificationService.deleteById(id);

        return "redirect:/goods/myGoods";
    }

}
