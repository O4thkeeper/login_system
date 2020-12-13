package com.example.login_system.utils;

import java.util.Random;

public class CommonUtil {


    private static final Random random = new Random();

    public static String getRandomNum(int num){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
