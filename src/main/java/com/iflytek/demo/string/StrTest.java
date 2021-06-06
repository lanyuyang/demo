package com.iflytek.demo.string;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 17:54 2019/4/16
 */
public class StrTest {
    public static void main(String[] args) {
        String str = "我们今天去我们的学校";
        String string = "我们";
        Matcher matcherKeyword = Pattern.compile(string).matcher(str);
        if (matcherKeyword.find()){
            System.out.println(matcherKeyword.start());

        }
        findStrIndex(str, string);
    }

    public static int findStrIndex(String str, String des){
        List<Integer> indesList = new ArrayList<Integer>();
        Matcher matcherKeyword = Pattern.compile(des).matcher(str);
        while (matcherKeyword.find()){
            indesList.add(matcherKeyword.start());
        }
//        if (matcherKeyword.find()){
//            indesList.add(matcherKeyword.start());
//        }
        for (int i=0; i< indesList.size(); i++){
            System.out.println(indesList.get(i));
        }
        return 0;
    }

    /**
     *  输入参数只包含 中文 英文 数字 下划线
     */
    public static void str(){
        // content 只包含中文 英文 数字 下划线
        String content= "12hkjh&";
        String regex = "^[a-zA-Z0-9_\u4e00-\u9fa5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match=pattern.matcher(content);
        boolean b=match.matches();
        if(b)
        {
            System.out.println("OK");
        }
        else
        {
            System.out.println("NO");
        }
    }
}
