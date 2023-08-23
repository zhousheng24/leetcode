# Content
<p>Given an integer <code>n</code>, return <em>the number of structurally unique <strong>BST'</strong>s (binary search trees) which has exactly </em><code>n</code><em> nodes of unique values from</em> <code>1</code> <em>to</em> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/uniquebstn3.jpg" style="width: 600px; height: 148px;" />
<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 5
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= n &lt;= 19</code></li>
</ul>

<div><div>Related Topics</div><div><li>树</li><li>二叉搜索树</li><li>数学</li><li>动态规划</li><li>二叉树</li></div></div><br><div><li>👍 2338</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int numTrees(int n) {
        // 1 <= n <= 19
        // dp[i]表示包含[1,i]结点的不同二叉搜索树的个数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int j = 0;
            while (j <= i - 1) {
                dp[i] += dp[j] * dp[i - 1 - j];
                j++;
            }
        }
        return dp[n];
    }
}
```