# Content
<p>Given a <code>rows x cols</code>&nbsp;binary <code>matrix</code> filled with <code>0</code>'s and <code>1</code>'s, find the largest rectangle containing only <code>1</code>'s and return <em>its area</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg" style="width: 402px; height: 322px;" />
<pre>
<strong>Input:</strong> matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The maximal rectangle is shown in the above picture.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [["0"]]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [["1"]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>rows == matrix.length</code></li>
 <li><code>cols == matrix[i].length</code></li>
 <li><code>1 &lt;= row, cols &lt;= 200</code></li>
 <li><code>matrix[i][j]</code> is <code>'0'</code> or <code>'1'</code>.</li>
</ul>

<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>动态规划</li><li>矩阵</li><li>单调栈</li></div></div><br><div><li>👍 1567</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    static final char ONE = '1';
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
                    int x = dp[i][j][0];
                    int y = 1;
                    while (y <= dp[i][j][1]) {
                        x = Math.min(x, dp[i - y + 1][j][0]);
                        max = Math.max(max, x * y);
                        y++;
                    }
                }
            }
        }
        return max;
    }
}
```