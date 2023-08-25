# Content
<p>Given an <code>m x n</code> binary <code>matrix</code> filled with <code>0</code>'s and <code>1</code>'s, <em>find the largest square containing only</em> <code>1</code>'s <em>and return its area</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/max1grid.jpg" style="width: 400px; height: 319px;" />
<pre>
<strong>Input:</strong> matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/max2grid.jpg" style="width: 165px; height: 165px;" />
<pre>
<strong>Input:</strong> matrix = [["0","1"],["1","0"]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [["0"]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>m == matrix.length</code></li>
 <li><code>n == matrix[i].length</code></li>
 <li><code>1 &lt;= m, n &lt;= 300</code></li>
 <li><code>matrix[i][j]</code> is <code>'0'</code> or <code>'1'</code>.</li>
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>矩阵</li></div></div><br><div><li>👍 1524</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        // m == matrix.length
        // n == matrix[i].length
        // 1 <= m, n <= 300
        // matrix[i][j] is '0' or '1'.
        int m = matrix.length;
        int n = matrix[0].length;
        // dp[i][j][0]表示以[i,j]为右端点，水平方向连续'1'的最长长度
        // dp[i][j][1]表示以[i,j]为下端点，垂直方向连续'1'的最大高度
        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = matrix[0][0] == '1' ? 1 : 0;
        dp[0][0][1] = dp[0][0][0];
        int max = dp[0][0][0];
        for (int j = 1; j < n; j++) {
            dp[0][j][0] = matrix[0][j] == '1' ? dp[0][j - 1][0] + 1 : 0;
            dp[0][j][1] = matrix[0][j] == '1' ? 1 : 0;
            max = Math.max(max, dp[0][j][1]);
        }
        for (int i = 1; i < m; i++) {
            dp[i][0][0] = matrix[i][0] == '1' ? 1 : 0;
            dp[i][0][1] = matrix[i][0] == '1' ? dp[i - 1][0][1] + 1 : 0;
            max = Math.max(max, dp[i][0][0]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j][0] = matrix[i][j] == '1' ? dp[i][j - 1][0] + 1 : 0;
                dp[i][j][1] = matrix[i][j] == '1' ? dp[i - 1][j][1] + 1 : 0;
                int y = 1;
                int x = dp[i][j][0];
                while (y <= dp[i][j][1]) {
                    x = Math.min(x, dp[i - y + 1][j][0]);
                    if (x < y) {
                        break;
                    }
                    max = Math.max(max, y * y);
                    y++;
                }
            }
        }
        return max;
    }
}
```
## 2. 动态规划（优化）
### Java
```java
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
```