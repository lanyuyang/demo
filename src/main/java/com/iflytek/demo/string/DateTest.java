package com.iflytek.demo.string;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 9:30 2019/4/8
 *   将2019-01-25T01:30:00.000+0000  格式化为 2018-12-30 09:30:00
 */
public class DateTest {
    public static void main(String[] args) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss.sssZ");
            Date date = formatter.parse("2019-01-25T01:30:00.000+0000");

            System.out.println(date);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate=sdf.format(date);
            System.out.println(sDate);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
