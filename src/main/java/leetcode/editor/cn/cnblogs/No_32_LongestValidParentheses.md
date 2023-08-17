# Content

<p>Given a string containing just the characters <code>'('</code> and <code>')'</code>, return <em>the length of the longest valid (well-formed) parentheses </em><span data-keyword="substring-nonempty"><em>substring</em></span>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "(()"
<strong>Output:</strong> 2
<strong>Explanation:</strong> The longest valid parentheses substring is "()".
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = ")()())"
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest valid parentheses substring is "()()".
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = ""
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
 <li><code>s[i]</code> is <code>'('</code>, or <code>')'</code>.</li>
</ul>

<div><div>Related Topics</div><div><li>栈</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 2329</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int longestValidParentheses(String s) {
        // dp[i]表示字符串s中以i下标结尾的最长有效括号串的长度。
        int n = s.length();
        if (n < 2) {
            return 0;
        }
        int[] dp = new int[n];
        if (s.charAt(0) == '(' && s.charAt(1) == ')') {
            dp[1] = 2;
        }
        for (int i = 2; i < n; i++) {
            char c0 = s.charAt(i - 1);
            char c1 = s.charAt(i);
            if (c1 == ')') {
                if (c0 == '(') {
                    dp[i] = dp[i - 2] + 2;
                } else if (dp[i - 1] > 0) {
                    // i0表示s中以（i - 1）下标结尾的最长有效括号串前一个下标
                    int i0 = i - 1 - dp[i - 1];
                    if (i0 >= 0) {
                        char c2 = s.charAt(i0);
                        if (c2 == '(') {
                            dp[i] = (i0 > 0 ? dp[i0 - 1] : 0) + dp[i - 1] + 2;
                        }
                    }
                }

            }
        }
        int longest = 0;
        for (int j = 0; j < n; j++) {
            longest = Math.max(longest, dp[j]);
        }
        return longest;
    }
}
```