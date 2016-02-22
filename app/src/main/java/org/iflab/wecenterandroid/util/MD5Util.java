package org.iflab.wecenterandroid.util;

import org.iflab.wecenterandroid.Constant;

/**
 * Created by Lyn on 15/12/9.
 */
public class MD5Util {
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String getSign(String url){
        return MD5(url+ Constant.SIGN);
    }
}
