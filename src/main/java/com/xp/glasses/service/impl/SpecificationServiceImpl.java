package com.xp.glasses.service.impl;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ImageType;
import com.xp.glasses.entity.GoodsSpecification;
import com.xp.glasses.entity.Image;
import com.xp.glasses.entity.form.SpeForm;
import com.xp.glasses.mapper.SpecificationMapper;
import com.xp.glasses.service.ImageService;
import com.xp.glasses.service.SpecificationService;
import com.xp.glasses.utils.IdUtils;
import com.xp.glasses.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecificationServiceImpl extends BaseServiceImpl implements SpecificationService {

    SpecificationMapper specificationMapper;

    @Autowired
    ServerConfig serverConfig;

    @Autowired
    ImageService imageService;

    public SpecificationServiceImpl(SpecificationMapper specificationMapper) {
        super(specificationMapper);
        this.specificationMapper = specificationMapper;
    }


    @Override
    public Integer insert(Object o) throws IOException {
        SpeForm speForm = (SpeForm) o;
        speForm.setId(IdUtils.initId());
        MultipartFile image = speForm.getImage();
        String newName = ImageUtils.randomName(image);
        Image speImage = new Image();
        speImage.setId(IdUtils.initId());
        speImage.setType(ImageType.SPECIFICATION);
        speImage.setMappingId(speForm.getId());
        speImage.setUrl(newName);
        speImage.setSort((byte) 0);
        imageService.insert(speImage);
        super.insert(speForm);
        File file = new File(serverConfig.filePath + File.separator + newName);
        image.transferTo(file);
        return 1;
    }

    @Override
    public Integer deleteById(String id) {

        GoodsSpecification goodsSpecification = (GoodsSpecification) specificationMapper.selectById(id);

        Image img = goodsSpecification.getImg();

        Integer delete = specificationMapper.deleteById(id);

        if (img != null) {
            imageService.deleteById(img.getId());

            File file = new File(serverConfig.filePath + File.separator + img.getUrl());

            if (file.exists()) {
                file.delete();
            }

        }
        return delete;
    }

    @Override
    public List<GoodsSpecification> getGoodsSpes(String goodsId) {
        return specificationMapper.getGoodsSpes( goodsId);
    }
}
