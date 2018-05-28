package com.bigbaozi.aspectjlibrary.utils;

/**
 * Name: NoDoubleClickUtils
 * Author: zhangfenglin
 * Email: zhfenglin@163.com
 * Comment: //TODO
 * Date: 2018-05-28 14:07
 */
public class NoDoubleClickUtils {
    private static long lastClickTime = 0;
    private final static int SPACE_TIME = 500;

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime >
                SPACE_TIME) {
            isClick2 = false;
        } else {
            isClick2 = true;
        }
        lastClickTime = currentTime;
        return isClick2;
    }
}