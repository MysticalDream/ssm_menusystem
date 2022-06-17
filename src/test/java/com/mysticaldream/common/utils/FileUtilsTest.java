package com.mysticaldream.common.utils;

import org.junit.Test;

/**
 * @description: FileUtilsTest
 * @date: 2022/6/16 17:55
 * @author: MysticalDream
 */
public class FileUtilsTest {
    @Test
    public void test() {
        String fileName = FileUtils.getFileName("/s/abc/1.png");
        System.out.println(fileName);
    }
}
