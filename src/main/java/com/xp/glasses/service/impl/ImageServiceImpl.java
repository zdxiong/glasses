package com.xp.glasses.service.impl;

import com.xp.glasses.config.ServerConfig;
import com.xp.glasses.constant.ImageType;
import com.xp.glasses.entity.Image;
import com.xp.glasses.mapper.ImageMapper;
import com.xp.glasses.service.ImageService;
import com.xp.glasses.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Mrxiong
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ImageServiceImpl extends BaseServiceImpl implements ImageService {

    @Autowired
    ServerConfig serverConfig;

    private ImageMapper imageMapper;

    public ImageServiceImpl(ImageMapper imageMapper) {
        super(imageMapper);
        this.imageMapper = imageMapper;
    }

    @Override
    public void saveImage(String mappingId, MultipartFile img, ImageType type, Byte sort) throws IOException {
        Image image = new Image();
        String id = IdUtils.initId();
        image.setId(id);
        image.setMappingId(mappingId);
        image.setSort(sort);
        image.setType(type);
        String originalFilename = img.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        image.setUrl(id + suffix);
        // 添加图片信息
        super.insert(image);
//        String prefix = serverConfig.getUrl() + serverConfig.projectName;
        File file = new File(serverConfig.filePath +File.separator+ image.getUrl());
        img.transferTo(file);

    }
}
