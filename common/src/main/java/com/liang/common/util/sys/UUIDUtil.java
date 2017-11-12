package com.liang.common.util.sys;

import java.util.UUID;

/**
 * Created by liang on 2017/4/21.
 */
public class UUIDUtil {

    public static String get32UUID(){

        return UUID.randomUUID().toString().replace("-","");
    }

    public static String get36UUID(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println(get32UUID());
    }
}
