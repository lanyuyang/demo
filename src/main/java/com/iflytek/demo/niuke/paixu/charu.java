package com.iflytek.demo.niuke.paixu;

public class charu {
    public static void main(String[] args) {
        int[] arr = {4,2,7,0,5,10};
        for (int i=1; i< arr.length; i++){
            for (int j=i; j>0;j--){
                if (arr[j] < arr[j-1]){
                    int temp = arr[j-1];
                    arr[j-1]= arr[j];
                    arr[j] = temp;
                }else {
                    break;
                }
            }
        }
        for (int i=0; i< arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }
}
