package problem01;

import java.util.HashSet;
import java.util.Scanner;

public class Code10 {
//    在一行中输入一个字符串数组，如果其中一个字符串的所有以索引0开头的子串在数组中都有，
//    那么这个字符串就是潜在密码，在所有潜在密码中最长的是真正的密码，如果有多个长度相同的真止的密码，
//    那么取字典序最大的为唯一的真正的密码，求唯一的真正的密码。
//    示例1：
//    输入:
//    h he hel hell hello o ok n ni nin ninj ninja
//    输出：ninja
//    说明：按要求，hello、ok、ninja都是潜在密码。检查长度，hello、ninja是真正的密码。
//    检查字典序，ninja是唯一真正密码。
//    示例2：
//    输入：
//    a b c d f
//    输出：
//    f
//    说明：按要求，a b c d f都是潜在密码。检查长度，a b c d f是真正的密码。检查字典序，f是唯一真正密码。

    public static int min_time;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split(" ");

        HashSet<String> word_set = new HashSet<>();
        for(String s:strs){
            word_set.add(s);
        }

        String true_password = "";
        for(String s:strs){
//            条件1 检查这个词所有以索引0开头的子串在数组中是否都有
            boolean flag=true;
            for(int i=1;i<s.length();i++){
                String sub_str = s.substring(0,i);
                if(!word_set.contains(sub_str)){
                    flag=false;
                    break;
                }
            }

            if(flag){
//                条件2 比较密码长度
                if(s.length()>true_password.length()){
                    true_password=s;
                }
//                条件3 比较密码字典排序
                if(s.length()==true_password.length()&&s.compareTo(true_password)>0){
                    true_password=s;
                }
            }
        }
        System.out.println(true_password);
    }
}
