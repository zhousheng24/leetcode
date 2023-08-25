//Given an integer n, return the least number of perfect square numbers that 
//sum to n. 
//
// A perfect square is an integer that is the square of an integer; in other 
//words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 
//are perfect squares while 3 and 11 are not. 
//
// 
// Example 1: 
//
// 
//Input: n = 12
//Output: 3
//Explanation: 12 = 4 + 4 + 4.
// 
//
// Example 2: 
//
// 
//Input: n = 13
//Output: 2
//Explanation: 13 = 4 + 9.
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数学 动态规划 👍 1795 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

// [LeetCode][279]perfect-squares
public class LeetCode279_PerfectSquares {
    public static void main(String[] args) {
        Solution solution = new LeetCode279_PerfectSquares().new Solution();
        solution.numSquares(12);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numSquares(int n) {
            // 1 <= n <= 10⁴
            // dp[i]表示数字i最少由几个平方数累加获取。
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                int j = (int) Math.sqrt(i);
                while (j > 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
                    j--;
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}