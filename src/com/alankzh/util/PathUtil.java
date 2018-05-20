package com.alankzh.util;

public class PathUtil {
    public static String getWebInfPath() {
        String webInfPath=Thread.currentThread().getContextClassLoader().getResource("").toString();
        webInfPath = webInfPath.replace("file:/", "");
        webInfPath = webInfPath.replace("classes/", "");
        return webInfPath;
    }
}
