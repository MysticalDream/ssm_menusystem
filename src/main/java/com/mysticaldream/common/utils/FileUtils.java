package com.mysticaldream.common.utils;

import java.io.File;

/**
 * @description: FileUtils
 * @date: 2022/6/16 17:50
 * @author: MysticalDream
 */
public class FileUtils {
    private FileUtils() {

    }

    /**
     * 获取文件名
     *
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        File file = new File(path);
        return file.getName();
    }
}
