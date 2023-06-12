package problem01;

import java.util.Scanner;

public class Code08 {
//    在一个狭小的路口，每秒只能通过一辆车，假好车辆的颜色只有3种，找出N秒内经过的最多颜色的车辆数量。
//    三种颜色编号为0,1,2
//    输入描述
//    第一行输入的是通过的车辆颜色信息
//    [0,1,1,2]代表4秒钟通过的车辆颜色分别是0，1，1，2
//    第二行输入的是统计时间窗，整型，单位为秒
//    输出描述
//    输出指定时间窗内经过的最多颜色的车辆数量。
//    样例一：
//    输入
//    0 1 2 1
//    3
//    输出
//    2
//    样例解释
//    在3秒时间窗内，每个颜色最多出现2次。例为：【1,2,1]
//    样例二：
//    输入
//    0 1 2 1
//    2
//    输出
//    1
//    样例解释
//    在2秒时间窗内，每个颜色最多出现1次。

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input_str = in.nextLine();
        String[] input_list = input_str.split(" ");

        int[] cars = new int[input_list.length];
        for(int i=0;i<input_list.length;i++){
            cars[i]=Integer.parseInt(input_list[i]);
        }
        String window_size_str = in.nextLine();
        int window_size = Integer.valueOf(window_size_str);

//        初始化滑动窗口
        int[] car_count = new int[3];
        for(int i=0;i<window_size;i++){
            car_count[cars[i]] +=1;
        }

//        活动窗口向前滑
        int max_res = Math.max(Math.max(car_count[0],car_count[1]),car_count[2]);
        for(int i=window_size;i<cars.length;i++){
            car_count[cars[i]]+=1;
            car_count[cars[i-window_size]]-=1;
            max_res=Math.max(max_res,Math.max(Math.max(car_count[0],car_count[1]),car_count[2]));
        }
        System.out.println(max_res);
    }
}
