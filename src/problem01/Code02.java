package problem01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Code02 {
//    部门组织绿岛骑行团建活动。租用公共双人自行车，每辆自行车最多座两人，做最大载重M。
//    给出部门每个人的体重，请问最多需要租用多少双人自行车。
//    输入描述：
//    第一行两个数字m、n，分别代表自行车限重，部门总人数
//    第二行，n个数字，代表每个人的体重，体重都小于等于自行车限重m。
//    0<m<=200
//    0<n<=1000000
//    输出描述：最小需要的双人自行车数量。
//    示例1
//    输入
//    3 4
//    3 2 2 1
//    输出
//    3
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m=in.nextInt();
        int n=in.nextInt();
        ArrayList<Integer> weights=new ArrayList<>();
        for(int i=0;i<n;i++){
            int a = in.nextInt();
            weights.add(a);
        }
        Collections.sort(weights);

        int left=0;
        int right=weights.size()-1;
        int min_bikes=0;

        int temp_weight = weights.get(left)+weights.get(right);

        while(left<right){
            if(temp_weight>m){
                right--;
                min_bikes+=1;
                temp_weight=weights.get(left)+weights.get(right);
            }else{
                right--;
                left++;
                min_bikes+=1;
                temp_weight=weights.get(left)+weights.get(right);
            }
        }
        if(left==right){
            min_bikes++;
        }
        System.out.println(min_bikes);
    }
}
