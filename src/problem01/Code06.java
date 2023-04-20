package problem01;

import java.util.HashMap;
import java.util.Scanner;

public class Code06 {
//    求单向链表中间的节点值，如果奇数个节点取中间，偶数个取偏右边的那个值。
//    输入描述：第一行链表头节点地址path后续输入的节点数n
//    后续输入每行表示一个节点，格式："节点地址节点值下一个节点地址（-1表示空指针）
//    输入保证链表不会出现环，并且可能存在一些节点不属于链表。
//    输出描述：链表中间节点值。
//    示例
//    输入
//    00010 4
//    00000 3 -1
//    00010 5 12309
//    11451 6 00000
//    12309 7 11451
//    输出
//    6
    public static class ListNode{
        public int value;
        public int next;
        public ListNode(int value,int next){
            this.value = value;
            this.next = next;
        }
}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String head_node_str = in.nextLine();
        String[] head_info = head_node_str.split(" ");
        int head_addr = Integer.valueOf(head_info[0]);
        int count = Integer.valueOf((head_info[1]));

        HashMap<Integer,ListNode> node_info = new HashMap<>();
        for(int i=0;i<count;i++){
            String node_str = in.nextLine();
            String[] node_list_info=node_str.split(" ");
            int addr = Integer.valueOf(node_list_info[0]);
            int val = Integer.valueOf(node_list_info[1]);
            int next = Integer.valueOf(node_list_info[2]);
            ListNode listNode = new ListNode(val,next);
            node_info.put(addr,listNode);
        }

//        构造链表 剔除无效节点
        int size = 1,cur = 0;
        ListNode head=node_info.get(head_addr);
        ListNode thead=head;
        while(thead.next!=-1){
            size++;
            thead=node_info.get(thead.next);
        }
//        找中间节点
        while(head.next!=-1){
            if(size/2==cur){
                System.out.println(head.value);
            }
            head=node_info.get(head.next);
            cur++;
        }
    }
}
