package com.iflytek.demo.device;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 15:53 2019/5/9
 * DESC : 获取cpu的核数
 */
public class CpuCoreNum {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
