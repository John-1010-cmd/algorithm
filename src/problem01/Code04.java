package problem01;

import java.util.ArrayList;
import java.util.Scanner;

public class Code04 {
//    给一个无向图染色，可以填红黑两种颜色，必须保证相邻两个节点不能同时为红色，输出有多少种不同的染色方案？
//    输入描述：
//    第一行输入M（图中节点数）N（边数）
//    后续N行格式为：V1V2表示一个V1到V2的边
//    数据范围：1<=M<=15.0<=N<=M*3，不能保证所有节点都是连通的。
//    输出描述：输出一个数字表示染色方案的个数。
//    示例1
//    输入
//    4 4
//    1 2
//    2 4
//    3 4
//    1 3
//    输出
//    7
//    说明：
//    4个节点，4条边，
//    1号节点和2号节点相连，
//    2号节点和4号节点相连，
//    3号节点和4号节点相连，
//    1号节点和3号节点相连，
//    若想必须保证相邻两个节点不能同时为红色，总共7种方案。

//    数据范围小 可以考虑暴力破解
//    图的表达方式 基本就是考察节点和边的处理方式
//    节点的表达方式 int -> 二进制数字 1 1000101 1表示红色 0表示黑色
//    边的表达方式 ArrayList<int> 只有两个元素 表示两节点相连 存到数组之中
//    有了节点和边 直接遍历所有的可能表达方式
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        节点数
        int m = in.nextInt();
//        边数
        int n = in.nextInt();

        ArrayList<ArrayList<Integer>> edgeArrayList = new ArrayList<>();
        for(int i=0;i<n;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            ArrayList<Integer> singleEdge = new ArrayList<>();
            singleEdge.add(a);
            singleEdge.add(b);
            edgeArrayList.add(singleEdge);
        }

        int count = 0;
        for(int i=0;i<(1<<m);i++){
            boolean flag = true;
            for(int j=0;j<n;j++){
                if(((i>>(m-edgeArrayList.get(j).get(0)))&1)==1 &&
                        ((i>>(m-edgeArrayList.get(j).get(1)))&1)==1
                ){
                    flag = false;
                    break;
                }
            }
            if(flag){
                count++;
            }
        }
        System.out.println(count);
    }
}
