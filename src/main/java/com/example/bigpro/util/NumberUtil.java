package com.example.bigpro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
    private NumberUtil() {
    }

    /**
     * 生成指定长度的随机数
     *
     * @param length
     * @return
     */
    public static int genRandomNum(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }


}
