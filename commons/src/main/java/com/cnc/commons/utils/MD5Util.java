package com.cnc.commons.utils;

import java.security.MessageDigest;
import java.util.UUID;

public class MD5Util {

    // MD5 加密生成32位字符串,解决了生成31 位问题
    public static String md5DigestAsHex(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] result = md.digest(msg.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int sign = b & 0xff;
                String str = Integer.toHexString(sign);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    public static void main(String[] args) {
        String random2 = "admin123";



        String res2 = MD5Util.md5DigestAsHex(random2);

        System.out.println(res2);


//
//        System.out.println(res1);
//        System.out.println(res2);
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));

    }


}
