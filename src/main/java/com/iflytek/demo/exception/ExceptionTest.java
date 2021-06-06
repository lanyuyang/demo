package com.iflytek.demo.exception;

/**
 * @ Author     ：yylan
 * @ Date       ：Created in 17:14 2019/4/30
 */
public class ExceptionTest {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
       try {
           String str1 = args[0];
           String str2 = args[1];
           j = Integer.parseInt(str2);
           int temp = i/j;
           System.out.println("两个数相除 ： "+ temp);
           System.out.println("----------------------");
       }catch (ArithmeticException e){
           System.out.println("算术异常"+ e.getMessage());
       }catch (NumberFormatException e){
           System.out.println("数字转换异常"+ e.getMessage());
       }catch (ArrayIndexOutOfBoundsException e){
           System.out.println("数组越界异常"+ e.getMessage());
       }
        System.out.println("----------------------");
    }
}
