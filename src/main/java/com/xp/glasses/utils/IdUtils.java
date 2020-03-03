package com.xp.glasses.utils;

import java.util.UUID;

/**
 * @author Mrxiong
 */
public class IdUtils {

    /**
     * 生成UUID 作为ID
     * @return
     */
    public static String initId() {
       return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
