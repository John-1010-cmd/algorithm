package problem01;

import java.util.*;

public class Code03 {
//    给定一个学符量S，S包括以空格分隔的若十个单词，请对S进行如下处理后输出：
//    1、单词内部调整：对每个单词字母重新按字典序排序
//    2、单词间顺序调整：
//    1）统计每个单词出现的次数，并按次数降序排列
//    2）次数相同，按单词长度升序排列
//    3）次数和单词长度均相同，按学典升序排列
//    请输出处理后的字符串，每个单词以一个空格分隔。
//    示例1
//    输入
//    This is an apple
//    输出
//    an is This aelpp
//    示例2
//    输入
//    My sister is in the house not in the yard
//    输出
//    in in eht eht My is not adry ehosu eirsst

    public static int max_machine = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] str1=str.split(" ");

//        1. 单词内部调整
        ArrayList<String> str_list=new ArrayList<>();
        for(int i=0;i<str1.length;i++){
            char[] chars=str1[i].toCharArray();
            Arrays.sort(chars);
            str_list.add(String.valueOf(chars));
        }
//        2. 单词间调整
//        先统计每个单词出现的次数
        HashMap<String,Integer> str_count = new HashMap<>();
        for(int i=0;i< str_list.size();i++){
            if(str_count.containsKey(str_list.get(i))){
                str_count.put(str_list.get(i),str_count.get(str_list.get(i))+1);
            }else{
                str_count.put(str_list.get(i),1);
            }
        }
//        按次数排序
        List<Map.Entry<String,Integer>> str_count_list = new ArrayList<Map.Entry<String,Integer>>(str_count.entrySet());
        Collections.sort(str_count_list,
                new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        if(o1.getValue()>o2.getValue()){
                            return -1;
                        }else if(o1.getValue().equals(o2.getValue())){
                            if(o1.getKey().length()>o2.getKey().length()){
                                return 1;
                            }else if(o1.getKey().length()==o2.getKey().length()){
                                return o1.getKey().compareTo(o2.getKey());
                            }else {
                                return -1;
                            }
                        }else{
                            return 1;
                        }
                    }
                });

        String result="";
        for(int i=0;i< str_count_list.size();i++){
            for(int j=0;j<str_count_list.get(i).getValue();j++){
                result = result + str_count_list.get(i).getKey()+" ";
            }
        }
        System.out.println(result);
    }

}
