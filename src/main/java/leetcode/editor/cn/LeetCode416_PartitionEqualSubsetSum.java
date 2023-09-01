//Given an integer array nums, return true if you can partition the array into 
//two subsets such that the sum of the elements in both subsets is equal or false 
//otherwise. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,5,11,5]
//Output: true
//Explanation: The array can be partitioned as [1, 5, 5] and [11].
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,3,5]
//Output: false
//Explanation: The array cannot be partitioned into equal sum subsets.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 1865 👎 0


package leetcode.editor.cn;

// [LeetCode][416]partition-equal-subset-sum
public class LeetCode416_PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new LeetCode416_PartitionEqualSubsetSum().new Solution();
        solution.canPartition(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartition(int[] nums) {
            // 1 <= nums.length <= 200
            // 1 <= nums[i] <= 100
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if ((sum & 1) == 1) {
                return false;
            }
            int half = sum >> 1;
            int n = nums.length;
            // dp[j]表示前i个数字中任意个数字累加的最大值，上限为j
            int[] dp = new int[half + 1];
            for (int i = 1; i <= n; i++) {
                int num = nums[i - 1];
                if (num <= half) {
                    dp[half] = num + dp[half - num];
                    if (dp[half] == half) {
                        return true;
                    }
                }
                for (int j = half - 1; j > 0; j--) {
                    // 累加当前数字
                    if (num <= j) {
                        dp[j] = Math.max(dp[j], num + dp[j - num]);
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}