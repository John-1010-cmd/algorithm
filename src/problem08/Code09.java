package problem08;

import java.util.Arrays;
import java.util.Scanner;

public class Code09 {
//    某农场主管理了一大片果园，fields表示不同果林的面积，单位m^2，
//    现在要为所有的果林施肥且必须在n天内完成，否则影响收成。
//    小布是果林的工作人员，他每次选择一片果林进行施肥，
//    且每一片果林施肥完后当天不再进行施肥作业。
//    假设施肥机的能效为k，单位m^2/day，
//    请问至少租赁能效为k为多少的施肥机才能确保不影响收成？
//    如果无法完成施肥任务，则返回-1.
//    第一行输入 m n m为fields个数，n为施肥任务必须在n天内完成
//    第二行输入fields fields[i]表示果林i的面积，单位m^2
//    示例1：
//    输入
//    5 7
//    5 7 9 15 10
//    输出
//    9
//    示例2：
//    输入
//    3 1
//    2 3 4
//    输出
//    -1

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m=in.nextInt();
        int n=in.nextInt();
        in.nextLine();
        Integer[] fields = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        if(n<m){
            System.out.println(-1);
            return;
        }

        Arrays.sort(fields);
        if(n==m){
            System.out.println(fields[n-1]);
            return;
        }

        int left=0;
        int right=fields[m-1];
        int result=-1;
        while(left+1<right){
//            取中间位置的值作为效能k，这里的k取的是其在fields中的index
            int k=(int)Math.ceil((double) (left+right)/2);
            int res=cal(k,fields);
            if(res-n>0){
                left=k;
            }else{
                result=k;
                right=k;
            }
        }
        System.out.println(result);
    }
//    判断效能为k时，所需的总天数
    public static int cal(int k,Integer[] fields){
        int days=0;
        for(int i=0;i<fields.length;i++){
            days+=Math.ceil(fields[i]/(double)k);
        }
        return days;
    }
}
