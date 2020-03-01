package com.xp.glasses.entity.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Mrxiong
 */
@Data
public class SpeForm {

    private String id;

    @NotNull(message = "请选择商品")
    private String goodsId;

    @NotNull(message = "请填写规格名称")
    @NotBlank(message = "请填写规格名称")
    private String name;

    @NotNull(message = "请填写规格值")
    @NotBlank(message = "请填写规格值")
    private String value;

    @NotNull(message = "请填写规库存")
    private Integer stock;

    @NotNull(message = "请填写附加价值")
    private Long attachPrice;

    @NotNull(message = "请选择规格图片")
    private MultipartFile image;


}
