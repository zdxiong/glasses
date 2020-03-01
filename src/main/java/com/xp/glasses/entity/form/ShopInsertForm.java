package com.xp.glasses.entity.form;

import com.xp.glasses.apo.validate.Timetable;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 添加门店表单
 *
 * @author Mrxiong
 * @date 2020/01/10
 */
@Data
public class ShopInsertForm {

    /**
     * 门店ID
     */
    private String id;

    /**
     * 门店名称
     */
    @NotNull(message = "请填写门店名称")
    @NotBlank(message = "请填写门店名称")
    private String name;

    /**
     * 店长
     */
    @NotNull(message = "请填写店长名称")
    @NotBlank(message = "请填写店长名称")
    private String shopkeeper;

    /**
     * 固定电话
     */
    private String landLine;

    /**
     * 移动电话
     */
    @NotNull(message = "请填写移动电话")
    @NotBlank(message = "请填写移动电话")
    private String cellphone;

    /**
     * 上班时间
     */
    @NotNull(message = "请选择上班时间")
    @NotBlank(message = "请选择上班时间")
    @Timetable(message = "请填写正确的上班时间 如早上8点整(08:00)")
    private String startTime;

    /**
     * 下班时间
     */
    @NotNull(message = "请选择下班时间")
    @NotBlank(message = "请选择下班时间")
    @Timetable(message = "请填写正确的下班时间 如下午6点整(18:00)")
    private String quittingTime;

    /**
     * 工作日
     */
    @NotNull(message = "请填写工作日")
    @NotBlank(message = "请填写工作日")
    private String workDay;

    /**
     * 门店地址
     */
    @NotNull(message = "请填写门店地址")
    @NotBlank(message = "请填写门店地址")
    private String address;


    @NotNull(message = "请添加铺面介绍")
    @NotBlank(message = "请添加铺面介绍")
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;
}
