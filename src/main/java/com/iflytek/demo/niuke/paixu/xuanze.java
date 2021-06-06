package com.iflytek.demo.niuke.paixu;

public class xuanze {
    public static void main(String[] args) {
        int[] arr = {4,2,7,0,5,10};
        for (int i=0; i< arr.length; i++){
            int min = arr[i];
            int index = i;
            for (int j=i+1; j< arr.length; j++){
                if (min > arr[j]){
                    min = arr[j];
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i]= arr[index];
            arr[index] = temp;
        }
        for (int i=0; i< arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }
}
