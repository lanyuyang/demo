package com.iflytek.demo.niuke.src.华为;

import org.apache.commons.lang.StringUtils;

import java.util.Scanner;
public class kaopuNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int num = sc.nextInt();
            int count = num;
            for (int i= 1;i<= num;i++){
                String str = String.valueOf(i);
                if (str.contains("7")){
                    count--;
                }
            }
            System.out.println(count);
        }
    }
}
