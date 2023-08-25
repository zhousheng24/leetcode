//You are a professional robber planning to rob houses along a street. Each 
//house has a certain amount of money stashed, the only constraint stopping you from 
//robbing each of them is that adjacent houses have security systems connected and 
//it will automatically contact the police if two adjacent houses were broken 
//into on the same night. 
//
// Given an integer array nums representing the amount of money of each house, 
//return the maximum amount of money you can rob tonight without alerting the 
//police. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//Total amount you can rob = 1 + 3 = 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [2,7,9,3,1]
//Output: 12
//Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 
//(money = 1).
//Total amount you can rob = 2 + 9 + 1 = 12.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
//
// Related Topics 数组 动态规划 👍 2702 👎 0


package leetcode.editor.cn;

// [LeetCode][198]house-robber
public class LeetCode198_HouseRobber {
    public static void main(String[] args) {
        Solution solution = new LeetCode198_HouseRobber().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {
            // 1 <= nums.length <= 100
            // 0 <= nums[i] <= 400
            int n = nums.length;
            // dp[i]表示最后抢劫编号（编号0是一个虚拟的房子，没有钱方便计算）为i的房子可以获得的最大金额
            int[] dp = new int[n + 1];
            dp[1] = nums[0];
            int max = nums[0];
            // max0表示房子编号在[0, i - 2]范围内所能抢到的最大金额
            int max0 = 0;
            for (int i = 2; i <= n; i++) {
                max0 = Math.max(max0, dp[i - 2]);
                dp[i] = max0 + nums[i - 1];
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}