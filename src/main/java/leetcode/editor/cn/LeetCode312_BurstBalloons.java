//You are given n balloons, indexed from 0 to n - 1. Each balloon is painted 
//with a number on it represented by an array nums. You are asked to burst all the 
//balloons. 
//
// If you burst the iᵗʰ balloon, you will get nums[i - 1] * nums[i] * nums[i + 1
//] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if 
//there is a balloon with a 1 painted on it. 
//
// Return the maximum coins you can collect by bursting the balloons wisely. 
//
// 
// Example 1: 
//
// 
//Input: nums = [3,1,5,8]
//Output: 167
//Explanation:
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// Example 2: 
//
// 
//Input: nums = [1,5]
//Output: 10
// 
//
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// 0 <= nums[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 1267 👎 0


package leetcode.editor.cn;

// [LeetCode][312]burst-balloons
public class LeetCode312_BurstBalloons {
    public static void main(String[] args) {
        Solution solution = new LeetCode312_BurstBalloons().new Solution();
        solution.maxCoins(new int[]{2, 3, 7, 9, 1});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int maxCoins(int[] nums) {
            // n == nums.length
            // 1 <= n <= 300
            // 0 <= nums[i] <= 100
            int n = nums.length;
            int[] plus = new int[n + 2];
            System.arraycopy(nums, 0, plus, 1, n);
            plus[0] = 1;
            plus[plus.length - 1] = 1;
            // dp[i][j]表示戳爆开区间(i,j)内的气球所能获取到最多的硬币数
            // 所谓开区间表示坐标为i和j的气球不可以戳爆
            // i < k < j，假设k是最后一个戳爆的气球，那么dp[i][j] = dp[i][k] + dp[k][j] + plus[i] * plus[j] * plus[k]
            int[][] dp = new int[plus.length][plus.length];
            // interval表示区间(i,j)的长度
            int interval = 3;
            while (interval <= plus.length) {
                int i = 0;
                while (i <= plus.length - interval) {
                    int j = i + interval - 1;
                    int k = i + 1;
                    while (k < j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + plus[i] * plus[j] * plus[k]);
                        k++;
                    }
                    i++;
                }
                interval++;
            }
            return dp[0][plus.length - 1];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}