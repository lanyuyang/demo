package com.iflytek.demo.file;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 16:55 2019/4/12
 */
public class TxtTest1 {
    public static void main(String[] args) {
        readTxt();
    }

    /**
     * 读取 指定txt文件，
     */
    private static void readTxt(){
        Integer ONE = 1;
        Map<String, List<String>> dictMap = new HashMap<String, List<String>>();
        String key = null;
        List<String> keyWordList = null;
        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("F:\\tmp\\source.txt")),
                    "utf-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                // 设置为Map的键
                if (lineTxt.contains("[") && lineTxt.contains("]")){
                    if (!StringUtils.isEmpty(key) && keyWordList.size() != 0){
                        dictMap.put(key,keyWordList);
                    }
                    keyWordList = new ArrayList<String>();
                    key = lineTxt.replace("[","").replace("]","").trim();
                }else {
                    // 循环加进键对应的List列表中
                    if (!StringUtils.isEmpty(lineTxt)){
                        keyWordList.add(lineTxt);
                    }
                }
            }
            System.out.println(dictMap.size());
            System.out.println("hello");
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }
        /* 输出数据 */
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("F:\\tmp\\value_map.txt")),
                    "UTF-8"));
            for (String name : dictMap.keySet()) {
                bw.write(name + " " + dictMap.get(name));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }
}
