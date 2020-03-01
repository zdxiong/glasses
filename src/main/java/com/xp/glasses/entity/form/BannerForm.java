package com.xp.glasses.entity.form;

import com.xp.glasses.constant.BannerType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Mrxiong
 * @date 2020/01/11
 */
@Data
public class BannerForm {

    /**
     * bannerId
     */
    private String id;

    /**
     * banner name
     */
    private String name;

    /**
     * 商品ID
     */
    private String mappingId;

    /**
     * 描述
     */
    private String description;

    /**
     * 访问地址
     */
    private String url;

    private BannerType type;

    /**
     * 排列序号
     */
    @NotNull(message = "请输入排列序号")
    private byte sort;

    @NotNull(message = "请选择一张图片")
    private MultipartFile image;

    /**
     * 创建时间
     */
    private Date createTime;
}
