# Content
<p>Given two strings <code>word1</code> and <code>word2</code>, return <em>the minimum number of operations required to convert <code>word1</code> to <code>word2</code></em>.</p>

<p>You have the following three operations permitted on a word:</p>

<ul>
 <li>Insert a character</li>
 <li>Delete a character</li>
 <li>Replace a character</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = "horse", word2 = "ros"
<strong>Output:</strong> 3
<strong>Explanation:</strong>
horse -&gt; rorse (replace 'h' with 'r')
rorse -&gt; rose (remove 'r')
rose -&gt; ros (remove 'e')
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = "intention", word2 = "execution"
<strong>Output:</strong> 5
<strong>Explanation:</strong>
intention -&gt; inention (remove 't')
inention -&gt; enention (replace 'i' with 'e')
enention -&gt; exention (replace 'n' with 'x')
exention -&gt; exection (replace 'n' with 'c')
exection -&gt; execution (insert 'u')
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>0 &lt;= word1.length, word2.length &lt;= 500</code></li>
 <li><code>word1</code> and <code>word2</code> consist of lowercase English letters.</li>
</ul>

<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 3082</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int minDistance(String word1, String word2) {
        // 0 <= word1.length, word2.length <= 500
        // word1 and word2 consist of lowercase English letters.
        int m = word1.length();
        int n = word2.length();
        // dp[i][j]表示word1中包含左端点的长度为i的子串转化到word2中包含左端点的长度为j的子串所需要的最少操作数
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    // 当word1中第i个字符和word2中第j个字符相同时，即这两个位置上的字符不需要转换，
                    // 那么我们可以直接认为dp[i][j]和dp[i - 1][j - 1]相同。
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 当word1中第i个字符和word2中第j个字符不同时，从以下三种情况中选择操作数最少的那个：
                    // 1. 将word1中包含左端点的长度为(i - 1)的子串转换为word2，并且”删除“word1中最右端的一个字符；
                    // 2. 将word1中包含左端点的长度为(i - 1)的子串转换为word2中包含左端点的长度为(j - 1)的子串，
                    // 并且将word1中最右端的一个字符”替换“为word2中最右端的资格字符；
                    // 3. 将word1转换为word2中包含左端点的长度为(j - 1)的子串，并且”插入“word2中最右端的一个字符。
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
```