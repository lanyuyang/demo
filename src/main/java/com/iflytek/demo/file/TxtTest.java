package com.iflytek.demo.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 10:28 2019/4/8
 *  将string 内容写进 txt 文件中
 */
public class TxtTest {
    public static void main(String[] args) {
        String path = "F:\\tmp\\aaa\\b.txt";

        File file = new File("F:\\tmp\\aaa");
        if (!file.exists()){
            file.mkdirs();
        }

        if (createFile(path)){
            WriteStringToFile(path);
        }
    }

    private static boolean createFile(String filePath){
        boolean flag = false;
        File newF = new File(filePath);
        if (!newF.exists()){
            try {
                newF.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            flag = true;
        }
        return flag;
    }
    public static void WriteStringToFile(String filePath) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("http://www.jb51.net");// 往文件里写入字符串
            ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
