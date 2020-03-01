package com.xp.glasses.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mrxiong
 */
public class ImageUtils {



    public static String randomName(MultipartFile file) {
        String imageId = IdUtils.initId();
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        return imageId + suffix;

    }
}
