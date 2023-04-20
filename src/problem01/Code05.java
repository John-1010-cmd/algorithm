package problem01;

import java.util.*;

public class Code05 {
//    给定一个数组nums,将元素分为若干个组，使得每组和相等，求出满足条件的所有分组中，组内元素和的最小值
//    输入描述：第一行输入m
//    接着输入m个数，表示此数组
//    数据范围：1<=m<=50,1<=nums[i]<=50
//    输出描述：最小拆分数组和。
//    示例：
//    输入：
//    7
//    4 3 2 3 5 2 1
//    输出：
//    5
//    说明：可以等分的情况有：
//    4个子集(5)，(1,4)，(2,3)，(2,3)
//    2个子集(5,1,4)，(2,3,2,3)
//    但最小的为5。

//    1. 将数组拆分 每个子数组的和相等 如【2,2,4】拆分为【2，2】【4】
//    2. 要求所有的可能拆分条件下，子数组的和最小 如【1，1,1,1】可以拆分为【1】【1】【1】【1】和【1,1】【1,1】
//    3. 和leetcode698很像，首先要判定是否能拆分成等和子数组。这里也给出动态规划解法的官方描述
//    用一个整数S来表示当前可用的数字集合：
//    从低位到高位，第i位为0则表示数字nums[i]可以使用，否则表示nums[i]已被使用。
//    然后用dp[S]来表示在可用的数字状态为S的情况下是否可能可行，
//    初始全部状态为记录为不可行状态False，只记dp[0]=True为可行状态。
//    同样每次对于当前状态下从可用的数字中选择一个数字，
//    若此时选择全部数字取模后小于等于per。则说明选择该数字后的状态再继续往下添加数字是可能能满足题意的，
//    并此时标记状为可能可行状态，否则就一定不能达到满足。最终U即可，其中U表示全部数字使用的集合状态。
//
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        String param_str = in.nextLine();
        int count = Integer.valueOf(param_str);

        //构造输入数据结构,并求和
        int[] nums = new int[count];
        String num_str = in.nextLine();
        int sum = 0;
        String[] num_list = num_str.split(" ");
        for (int i = 0; i < count; i++) {
            nums[i] = Integer.valueOf(num_list[i]);
            sum += Integer.valueOf(num_list[i]);
        }

        // 最大可以等分为m个子数组
        for (int i = count; i > 0; i--) {
            //从最大的可能行开始，满足条件即为为最小的情况
            if (canPartitionKSubsets(nums, i, sum)) {
                System.out.println(sum / i);
                break;
            }
        }
    }

    public static boolean canPartitionKSubsets(int[] nums, int k, int all) {
        if (all % k != 0) {
            return false;
        }
        int per = all / k;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        boolean[] dp = new boolean[1 << n];
        int[] curSum = new int[1 << n];
        dp[0] = true;
        for (int i = 0; i < 1 << n; i++) {
            if (!dp[i]) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (curSum[i] + nums[j] > per) {
                    break;
                }
//                如果取模为0就组合成一个新的子集 将当前状态的累和清零
                if (((i >> j) & 1) == 0) {
                    int next = i | (1 << j);
                    if (!dp[next]) {
                        curSum[next] = (curSum[i] + nums[j]) % per;
                        dp[next] = true;
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}


