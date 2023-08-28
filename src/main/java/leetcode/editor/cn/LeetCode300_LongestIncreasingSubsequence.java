//Given an integer array nums, return the length of the longest strictly 
//increasing subsequence. 
//
// 
// Example 1: 
//
// 
//Input: nums = [10,9,2,5,3,7,101,18]
//Output: 4
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the 
//length is 4.
// 
//
// Example 2: 
//
// 
//Input: nums = [0,1,0,3,2,3]
//Output: 4
// 
//
// Example 3: 
//
// 
//Input: nums = [7,7,7,7,7,7,7]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
// Follow up: Can you come up with an algorithm that runs in O(n log(n)) time 
//complexity? 
//
// Related Topics 数组 二分查找 动态规划 👍 3353 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

// [LeetCode][300]longest-increasing-subsequence
public class LeetCode300_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new LeetCode300_LongestIncreasingSubsequence().new Solution();
        solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            // 1 <= nums.length <= 2500
            // -10⁴ <= nums[i] <= 10⁴
            int n = nums.length;
            // dp[i]表示以下标为i的元素结尾的最长子序列的长度
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int max = 1;
            for (int i = 1; i < n; i++) {
                int j = i - 1;
                // 遍历i之前的所有数字，
                // 1. 当nums[j] < nums[i]时，nums[i]可以添加到dp[j]所代表的子序列之后。
                // 2. 当nums[j] == nums[i]时，nums[i]可以替换调dp[j]所代表的子序列的最后一个数字即nums[j]。
                // 3. 当num[j] > nums[j]时，跳过。
                while (j >= 0) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    } else if (nums[j] == nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j]);
                    }
                    j--;
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}