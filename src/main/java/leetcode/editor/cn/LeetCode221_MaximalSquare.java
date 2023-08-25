//Given an m x n binary matrix filled with 0's and 1's, find the largest square 
//containing only 1's and return its area. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1",
//"1"],["1","0","0","1","0"]]
//Output: 4
// 
//
// Example 2: 
// 
// 
//Input: matrix = [["0","1"],["1","0"]]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: matrix = [["0"]]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] is '0' or '1'. 
// 
//
// Related Topics 数组 动态规划 矩阵 👍 1524 👎 0


package leetcode.editor.cn;

// [LeetCode][221]maximal-square
public class LeetCode221_MaximalSquare {
    public static void main(String[] args) {
        Solution solution = new LeetCode221_MaximalSquare().new Solution();
        solution.maximalSquare(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '1'}
        });
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        static final char ONE = '1';

        public int maximalSquare(char[][] matrix) {
            // m == matrix.length
            // n == matrix[i].length
            // 1 <= m, n <= 300
            // matrix[i][j] is '0' or '1'.
            int m = matrix.length;
            int n = matrix[0].length;
            // dp[i][j][0]表示以[i,j]为右端点，水平方向连续'1'的最长长度
            // dp[i][j][1]表示以[i,j]为下端点，垂直方向连续'1'的最大高度
            // dp[i][j][2]表示以[i,j]为右下端点，只包含'1'的最大正方形的边长
            int[][][] dp = new int[m][n][3];
            dp[0][0][0] = matrix[0][0] == ONE ? 1 : 0;
            dp[0][0][1] = dp[0][0][0];
            dp[0][0][2] = dp[0][0][0];
            int maxSide = dp[0][0][2];
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == ONE) {
                    dp[0][j][0] = dp[0][j - 1][0] + 1;
                    dp[0][j][1] = 1;
                }
                dp[0][j][2] = dp[0][j][1];
                maxSide = Math.max(maxSide, dp[0][j][2]);
            }
            for (int i = 1; i < m; i++) {
                if (matrix[i][0] == ONE) {
                    dp[i][0][0] = 1;
                    dp[i][0][1] = dp[i - 1][0][1] + 1;
                }
                dp[i][0][2] = dp[i][0][0];
                maxSide = Math.max(maxSide, dp[i][0][2]);
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][j] == ONE) {
                        dp[i][j][0] = dp[i][j - 1][0] + 1;
                        dp[i][j][1] = dp[i - 1][j][1] + 1;
                        dp[i][j][2] = Math.min(
                                dp[i - 1][j - 1][2] + 1,
                                Math.min(dp[i][j][0], dp[i][j][1])
                        );
                        maxSide = Math.max(maxSide, dp[i][j][2]);
                    }
                }
            }
            return maxSide * maxSide;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}