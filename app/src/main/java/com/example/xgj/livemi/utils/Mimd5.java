package com.example.xgj.livemi.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chen on 2017/4/17.
 */

public class Mimd5 {
    public static String stringToMD5(String original) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("MD5").digest(original.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(secretBytes.length * 2);
        byte[] bytes = secretBytes;
        int length = secretBytes.length;

        for (int i = 0; i < length; ++i) {
            byte b;
            if (((b = bytes[i]) & 255) < 16) {
                stringBuilder.append("0");
            }

            stringBuilder.append(Integer.toHexString(b & 255));
        }

        return stringBuilder.toString();
    }

    public static String encodeByMD5(String secretString) {
        if (secretString == null) {
            return null;
        } else {
            try {
                MessageDigest messageDigest;
                (messageDigest = MessageDigest.getInstance("MD5")).update(secretString.getBytes());
                byte[] bytes = messageDigest.digest();
                StringBuffer stringBuffer = new StringBuffer("");

                for (int i = 0; i < bytes.length; ++i) {
                    int b;
                    if ((b = bytes[i]) < 0) {
                        b += 256;
                    }
                    if (b < 16) {
                        stringBuffer.append("0");
                    }

                    stringBuffer.append(Integer.toHexString(b));
                }
                return stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
