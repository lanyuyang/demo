package com.iflytek.demo.niuke.abc;


import java.util.ArrayList;
import java.util.List;

/**
 * 去联系最大值  滑动窗口
 */
public class Test1 {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = nums[0];
        for (int i=0; i< nums.length; i++){
            int temp = 0;
            for (int j=i; j< nums.length; j++){
                temp = temp+nums[j];
                if (temp > res){
                    res = temp;
                }
            }
        }
        System.out.println(res);
    }
}
