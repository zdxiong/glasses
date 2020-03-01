package com.xp.glasses.service.impl;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.entity.form.BannerForm;
import com.xp.glasses.mapper.BannerMapper;
import com.xp.glasses.service.BannerService;
import com.xp.glasses.utils.IdUtils;
import com.xp.glasses.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author Mrxiong
 * @date 2020/01/11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BannerServiceImpl extends BaseServiceImpl implements BannerService {

    private BannerMapper bannerMapper;

    @Autowired
    private ServerConfig serverConfig;


    public BannerServiceImpl(BannerMapper bannerMapper) {
        super(bannerMapper);
        this.bannerMapper = bannerMapper;
    }

    @Override
    public void insertBanner(BannerForm bannerForm) throws IOException {
        MultipartFile image = bannerForm.getImage();

        String randomName = ImageUtils.randomName(image);

        if (bannerForm.getType() == null){
            bannerForm.setMappingId(null);
        }
        bannerForm.setId(IdUtils.initId());
        bannerForm.setCreateTime(new Date());
        bannerForm.setUrl(randomName);

        // 添加banner基础信息
        super.insert(bannerForm);

        // 保存文件
        File file = new File(serverConfig.filePath + File.separator +randomName);

        image.transferTo(file);
    }

    @Override
    public Integer count(Map map) {
        return bannerMapper.count(map);
    }
}
