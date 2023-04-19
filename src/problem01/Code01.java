package problem01;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Code01 {
//    公司创新实验室正在研究如何最小化资源成本，最大化资源利用率，请你设计算法帮他们解决一个任务混部问题：
//    有taskNum项任务，
//    每个任务有开始时间（startTime），结束时间（endTime），并行度（parallelism）三个属性，
//    并行度是指这个任务运行时将会占用的服务器数量，
//    一个服务器在每个时刻可以被任意任务使用但最多被一个任务占用，任务运行完成立即释放（结束时刻不占用）。
//    任务混部问题是指给定一批任务，让这批任务由同一批服务器承载运行，请你计算完成这批任务混部最少需要多少服务器，
//    从而最大最大化控制资源成本。
//    输入描述：
//    第一行输入为taskNum，表示有taskNum项任务接下来askNum行，每行三个整数，
//    表示每个任务的开始时间（startTime），结束时间（endTime），并行度（parallelism）
//    输出描述：一个整数，表示最少需要的服务器数量
//    示例1
//    输入
//    3
//    2 3 1
//    6 9 2
//    0 5 1
//    输出
//    2
//    说明一共有三个任务，
//    第一个任务在时间区间【2，3】运行，占用1个服务器，
//    第二个任务在时间区间【6，9】运行，占用2个服务器，
//    第三个任务在时间区间【0，5】运行，占用1个服务器，
//    需要最多服务器的时间区间为【2，3】和【6，9】需要2个服务器。
//    示例2
//    输入
//    2
//    3 9 2
//    4 7 3
//    输出
//    5
//    说明一共两个任务，
//    第一个任务在时间区间【3，9】运行，占用2个服务器，
//    第二个任务在时间区间【4，7】运行，占用3个服务器，
//    需要最多服务器的时间区间为【4，7】，需要5个服务器
//    备注：
//    1≤=taskNum<=100000
//    0<=startTime<endTime<=50000
//    1<=parallelism<=100
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] ranges = new int[n][3];
        for(int i = 0;i<n;i++){
            ranges[i][0]=in.nextInt();
            ranges[i][1]=in.nextInt();
            ranges[i][2]=in.nextInt();
        }
        System.out.println(minMeetingRooms(ranges));
    }

    public static int minMeetingRooms(int[][] ranges){
//        按任务开始时间从小到大排序
        Arrays.sort(ranges,(a,b)->a[0]-b[0]);
//        按任务结束时间从小到大排序
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        int res=0;
        int temp_res=0;
        for(int i=0;i<ranges.length;i++){
            while(pq.size()>0){
//                检索但不删除此队列的头部 按任务开始时间升序检索
                Integer[] top=pq.peek();
//                检索的任务结束时间 在 当前任务开始时间 之前
                if(top[0]<ranges[i][0]){
//                    检索并删除此队列的头部 任务结束时间
                    Integer[] poll= pq.poll();
//                    检索的任务所需服务器数可以复用 减去当前所需服务器数
                    temp_res-=poll[1];
                }else{
                    break;
                }
            }
//            将指定的元素插入到此优先级队列中。
            pq.offer(new Integer[]{ranges[i][1],ranges[i][2]});
//            目前所需服务器数 直接加上 当前任务所需服务器数
            temp_res+=ranges[i][2];
//            获得最终所需服务器数
            if(temp_res>res){
                res=temp_res;
            }
        }
        return res;
    }
}
