package com.mysticaldream.common.utils;

import org.junit.Test;

/**
 * @description: Md5UtilsTest
 * @date: 2022/5/29 15:57
 * @author: MysticalDream
 */
public class Md5UtilsTest {

    @Test
    public void hash() {
        String hash = Md5Utils.hash("123");
        System.out.println(hash);
    }

    @Test
    public void test1() {
        char a = 34;
        System.out.println(a);
    }
}