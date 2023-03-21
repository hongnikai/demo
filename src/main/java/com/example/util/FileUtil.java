package com.example.util;

import java.util.Properties;

public class FileUtil {
    /**
     * 根据当前操作系统获取动态位置
     */
    public static String getByPath(String path) {
        //查看当前服务器操作系统
        Properties props = System.getProperties();
        String property = props.getProperty("os.name");
        String userHomePath = props.getProperty("user.home");
        String filePath = "";//文件存放地址
        if (property.contains("Windows")) {
            String[] arr = userHomePath.split(":");
            String pan = arr[0] + ":";
            filePath = pan + path;
        } else if (property.contains("Linux")) {
            filePath = path;
        }
        return filePath;
    }

}
