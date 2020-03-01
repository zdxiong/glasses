package com.xp.glasses.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Mrxiong
 */
public class MD5Util {

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String saltDB) {
        String str = "" + saltDB.charAt(0) + saltDB.charAt(2) + formPass + saltDB.charAt(5) + saltDB.charAt(4);
        return md5(str);
    }


}

