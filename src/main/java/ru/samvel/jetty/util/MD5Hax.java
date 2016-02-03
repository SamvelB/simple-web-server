package ru.samvel.jetty.util;


import org.apache.commons.codec.digest.DigestUtils;


public class MD5Hax {

    private static String salt = "vik-toria";

    public static String makeHax(String str) {
        String saltPass = str + salt;
        String stringMD5Hex = DigestUtils.md5Hex(saltPass);
        return stringMD5Hex;
    }
}
