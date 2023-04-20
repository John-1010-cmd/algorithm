package problem01;

import java.util.Scanner;

public class Code07 {
//    小明在学习二进制时，发现了一类不含101的数，也就是将数字用二进制表示，不能出现101。
//    现在给定一个整数区间[l,r]，请问这个区间包含了多少个不含101的数？
//    输入描述
//    输入的唯一一行包含两个正整数l,r（1≤1≤r≤10^9）。
//    输出描述
//    输出的唯一一行包含一个整数，表示在[l,r]区间内一共有几个不含101的数。
//    样例样例一：
//    输入
//    1 10
//    输出
//    8
//    样例解释
//    区间[1,10]内，5的二进制表示为101，10的二进制表示为1010，因此区间【1，10】内有10-2=8个不含101的数。
//    样例二：
//    输入
//    10 20
//    输出
//    7
//    样例解释
//    区间[10,20]内，满足条件的数字有[12,14,15,16,17,18,19]因此答案为7。

//    核心在于两点
//    1. 将一个十进制数字转成二进制的字符串形式
//    2. 字符串是否包含'101'
//    Integer.toString(num) 数字转字符串
//    Integer.toBinaryString(num) 数字转二进制
//    Integer.toHexString(num) 数字转十六进制
//    Integer.toOctalString(num) 数字转八进制

//    暴力超时比较严重
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int left = in.nextInt();
//        int right = in.nextInt();
//        int result = right - left + 1;
//        for(int i=left;i<=right;i++){
//            String num_str = Integer.toBinaryString(i);
//            if(num_str.contains("101")){
//                result -= 1;
//            }
//        }
//        System.out.println(result);
//    }

//    数组DP
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int left = in.nextInt();
        int right = in.nextInt();
        System.out.println(dp(right)-dp(left-1));
    }
    public static int dp(int num){
        String number = Integer.toBinaryString(num);
        Integer[] single_binary_nums = new Integer[number.length()];
        for(int i=0;i<number.length();i++){
            single_binary_nums[i]=Integer.valueOf(number.charAt(i)+"");
        }
        int[][][] binary_dp = new int[single_binary_nums.length][2][2];
        return search(0,true,binary_dp,single_binary_nums,0,0);
    }
    public static int search(int p,boolean flag,int[][][] binary_dp,Integer[] single_binary_nums,int pre,int prepre){
        if(p== single_binary_nums.length){
            return 1;
        }
        if(!flag && binary_dp[p][pre][prepre]!=0){
            return binary_dp[p][pre][prepre];
        }
        int index=flag?single_binary_nums[p]:1;
        int count=0;
        for(int i=0;i<index+1;i++){
            if(i==1&&pre==0&&prepre==1){
                continue;
            }
            count+=search(p+1,flag&&i==index,binary_dp,single_binary_nums,i,pre);
        }
        if(!flag){
            binary_dp[p][pre][prepre]=count;
        }
        return count;
    }
}


