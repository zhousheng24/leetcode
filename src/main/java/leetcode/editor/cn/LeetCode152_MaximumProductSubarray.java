//Given an integer array nums, find a subarray that has the largest product, 
//and return the product. 
//
// The test cases are generated so that the answer will fit in a 32-bit integer.
// 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.
// 
//
// Example 2: 
//
// 
//Input: nums = [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -10 <= nums[i] <= 10 
// The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit 
//integer. 
// 
//
// Related Topics 数组 动态规划 👍 2083 👎 0


package leetcode.editor.cn;

// [LeetCode][152]maximum-product-subarray
public class LeetCode152_MaximumProductSubarray {
    public static void main(String[] args) {
        Solution solution = new LeetCode152_MaximumProductSubarray().new Solution();
        solution.maxProduct(new int[]{-2, 3, -4});

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProduct(int[] nums) {
            // 1 <= nums.length <= 2 * 10⁴
            // -10 <= nums[i] <= 10
            // The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
            //integer.
            int n = nums.length;
            // dp[i][0]表示以下标为i的数字结尾的子数组的最大乘积
            // dp[i][1]表示以下标为i的数字结尾的子数组的最小乘积
            int[][] dp = new int[n][2];
            dp[0][0] = nums[0];
            dp[0][1] = nums[0];
            int max = nums[0];
            for (int i = 1; i < n; i++) {
                if (nums[i] == 0) {
                    dp[i][0] = 0;
                    dp[i][1] = 0;
                } else if (nums[i] > 0) {
                    dp[i][0] = dp[i - 1][0] > 0 ? dp[i - 1][0] * nums[i] : nums[i];
                    dp[i][1] = dp[i - 1][1] > 0 ? nums[i] : dp[i - 1][1] * nums[i];
                } else {
                    dp[i][0] = dp[i - 1][1] > 0 ? nums[i] : dp[i - 1][1] * nums[i];
                    dp[i][1] = dp[i - 1][0] > 0 ? dp[i - 1][0] * nums[i] : nums[i];
                }
                max = Math.max(max, dp[i][0]);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}