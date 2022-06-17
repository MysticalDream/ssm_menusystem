package com.mysticaldream.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

/**
 * md5加密工具
 *
 * @description: Md5Utils
 * @date: 2022/5/29 15:51
 * @author: MysticalDream
 */
public class Md5Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Md5Utils.class);


    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            return algorithm.digest();
        } catch (Exception e) {
            LOG.error("MD5 Error...", e);
        }
        return null;
    }

    /**
     * 字节hash转化十六进制字符串
     *
     * @param hash
     * @return
     */
    private static String toHex(byte[] hash) {
        if (hash == null) {
            return null;
        }

        StringBuilder buf = new StringBuilder(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * 获取字符串的32位md5小写摘要
     *
     * @param s
     * @return
     */
    public static String hash(String s) {
        try {
            return new String(Objects.requireNonNull(toHex(md5(s))).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOG.error("not supported charset...{}", e);
            return s;
        }
    }

}
