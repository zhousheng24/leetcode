//Given a rows x cols binary matrix filled with 0's and 1's, find the largest 
//rectangle containing only 1's and return its area. 
//
// 
// Example 1: 
// 
// 
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1",
//"1"],["1","0","0","1","0"]]
//Output: 6
//Explanation: The maximal rectangle is shown in the above picture.
// 
//
// Example 2: 
//
// 
//Input: matrix = [["0"]]
//Output: 0
// 
//
// Example 3: 
//
// 
//Input: matrix = [["1"]]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// rows == matrix.length 
// cols == matrix[i].length 
// 1 <= row, cols <= 200 
// matrix[i][j] is '0' or '1'. 
// 
//
// Related Topics 栈 数组 动态规划 矩阵 单调栈 👍 1567 👎 0


package leetcode.editor.cn;

// [LeetCode][85]maximal-rectangle
public class LeetCode85_MaximalRectangle {
    public static void main(String[] args) {
        Solution solution = new LeetCode85_MaximalRectangle().new Solution();
        solution.maximalRectangle(new char[][]{
                {'0', '0', '1', '0'},
                {'0', '0', '1', '0'},
                {'0', '0', '1', '0'},
                {'0', '0', '1', '1'},
                {'0', '1', '1', '1'},
                {'0', '1', '1', '1'},
                {'1', '1', '1', '1'}
        });

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        static final char ONE = '1';
        static final char ZERO = '0';

        public int maximalRectangle(char[][] matrix) {
            // rows == matrix.length
            // cols == matrix[i].length
            // 1 <= row, cols <= 200
            // matrix[i][j] is '0' or '1'.
            int m = matrix.length;
            int n = matrix[0].length;
            int max = 0;
            // dp[i][j][0]表示以matrix[i][j]为右端点的水平最长连续'1'的长度
            // dp[i][j][1]表示以matrix[i][j]为下端点的垂直最长连续'1'的长度
            int[][][] dp = new int[m][n][2];
            // 初始化xy
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = new int[]{0, 0};
                }
            }
            if (matrix[0][0] == ONE) {
                dp[0][0][0] = 1;
                dp[0][0][1] = 1;
                max = 1;
            }
            for (int j = 1; j < n; j++) {
                if (matrix[0][j] == ONE) {
                    dp[0][j][0] = dp[0][j - 1][0] + 1;
                    dp[0][j][1] = 1;
                    max = Math.max(max, dp[0][j][0]);
                }
            }
            for (int i = 1; i < m; i++) {
                if (matrix[i][0] == ONE) {
                    dp[i][0][0] = 1;
                    dp[i][0][1] = dp[i - 1][0][1] + 1;
                    max = Math.max(max, dp[i][0][1]);
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (matrix[i][j] == ONE) {
                        dp[i][j][0] = dp[i][j - 1][0] + 1;
                        dp[i][j][1] = dp[i - 1][j][1] + 1;
                        int x = Integer.MAX_VALUE;
                        int y = 0;
                        int i0 = i;
                        while (i - i0 + 1 <= dp[i][j][1]) {
                            x = Math.min(x, dp[i0][j][0]);
                            y++;
                            max = Math.max(max, x * y);
                            i0--;
                        }
                    }
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}