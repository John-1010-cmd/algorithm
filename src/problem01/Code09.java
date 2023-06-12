package problem01;

import java.util.*;

public class Code09 {
//    给定一个队列，但是这个队列比较特殊，可以从头部添加数据，也可以从尾部添加数据，但是只能从头部删除数据。
//    输入一个数字n，会依次添加数字1~n（也就是添加n次）。
//    但是在添加数据的过程中，也会册除数据，要求删除必须按照1～n按照顺序进行除，所以在删除时，
//    可以根据需要调整队列中数字的顺序以满足删除条件。
//    输入描述：
//    第一行一个数据N，表示数据的范围。
//    接下来的2N行是添加和删除语包。
//    其中：head add x表示M头部添加元素x，tail add表示M尾部添加元素，remove表示删除元素。
//    输出描述：
//    输出一个数字，表示最小的调整顺序次数。
//    示例：
//    5
//    head add 1
//    tail add 2
//    remove
//    head add 3
//    tail add 4
//    head add 5
//    remove
//    remove
//    remove
//    remove
//    输出：1
//    说明：
//    第1步：[1]
//    第2步：[1,2]
//    第3步：头部删除1，无需调整，还剩[2]
//    第4步：[3,2]
//    第5步：[3,2,4]
//    第6步：[5,3,2,4]
//    第7步：头部删除2，调整顺序再删除，还剩[3，4，5]
//    第8步：头部删除3，无需调整，还剩[4，5]
//    第9步：头部删除4，无需调整，还剩[5]
//    第10步：头部删除5，无需调整
//    只需要调整1次

//    数据按照顺序添加 所以只有队列大小大于0 且删除数据时才会产生调整的需要 只需要记录下需要调整的次数即可

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> list=new ArrayList<>();
//        while(in.hasNextLine()){
//            list.add(in.nextLine());
//        }
//        int n=Integer.parseInt(list.get(0));
        int n = Integer.parseInt(in.nextLine());
        list.add(n+"");
        for(int i=0;i<2*n;i++){
            list.add(in.nextLine());
        }
        Deque<Integer> deque=new LinkedList<>();
        int start = 1;
        int count = 0;
        for(int j=1;j< list.size();j++){
            String str = list.get(j);
            if(str.equals("remove")){
                if(!deque.isEmpty()){
                    if(deque.peekFirst()==start){
                        deque.removeFirst();
                        start++;
                    }else{
//                        此时需要调整顺序了
                        List<Integer> list1=new ArrayList<>();
                        while(!deque.isEmpty()){
                            list1.add(deque.removeFirst());
                        }
                        Collections.sort(list1);
                        for(int temp=0;temp<list1.size();temp++){
                            deque.addLast(list1.get(temp));
                        }
                        deque.removeFirst();
                        count++;
                        start++;
                    }
                }
                continue;
            }
            String[] strs =str.split(" ");
//            如果是添加指令
            if("add".equals(strs[1])){
                if("head".equals(strs[0])){
//                    从头部添加
                    deque.addFirst(Integer.parseInt(strs[2]));
                }else{
//                    从尾部添加
                    deque.addLast(Integer.parseInt(strs[2]));
                }
            }
//            System.out.println(deque);
        }
        System.out.println(count);
    }
}
