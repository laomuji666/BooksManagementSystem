package com.lmj.bms.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

    public static void logTimeAndString(String s) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss : ").format(new Date());
        System.out.println(time+s);
    }

}
