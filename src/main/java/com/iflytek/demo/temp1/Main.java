package com.iflytek.demo.temp1;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        int[] arr = {100,101,102};
        while (true){
            for (int i=0; i< arr.length; ++i){
                if (i == 1){
                    break;
                }
                else {
                    System.out.println("A");
                }
            }
            System.out.println("B");
        }
//        System.out.println("C");
    }
    /**
     * git status
     */
}
