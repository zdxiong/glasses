package com.xp.glasses.service;

import com.xp.glasses.constant.ImageType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Mrxiong
 */
public interface ImageService extends BaseService {
    /**
     * 添加图片信息
     *
     * @param mappingId
     * @param img
     * @param type
     * @param sort
     * @throws IOException
     */
    void saveImage(String mappingId, MultipartFile img, ImageType type, Byte sort) throws IOException;
}
