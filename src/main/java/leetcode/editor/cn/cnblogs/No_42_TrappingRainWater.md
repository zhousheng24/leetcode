# Content
<p>Given <code>n</code> non-negative integers representing an elevation map where the width of each bar is <code>1</code>, compute how much water it can trap after raining.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png" style="width: 412px; height: 161px;" />
<pre>
<strong>Input:</strong> height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [4,2,0,3,2,5]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>n == height.length</code></li>
 <li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
 <li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>双指针</li><li>动态规划</li><li>单调栈</li></div></div><br><div><li>👍 4616</li><li>👎 0</li></div>

# Solution
## 1. 单调栈+动态规划
### Java
```java
class Solution {

    public int trap(int[] height) {
        // n == height.length
        // 1 <= n <= 2 * 10⁴
        // 0 <= height[i] <= 10⁵
        int n = height.length;
        // 单调递减栈
        int[] stack = new int[n];
        int top = 0;
        // 累加bar
        int[] sums = new int[n];
        sums[0] = height[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + height[i];
        }
        // dp[i]表示以下标i所对应的bar作为最右边的bar时，能trap多少雨水。
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (height[i - 1] >= height[i]) {
                stack[++top] = i;
                dp[i] = dp[i - 1];
            } else {
                while (top > 0 && height[stack[top]] < height[i]) {
                    top--;
                }
                dp[i] = dp[stack[top]]
                        + Math.min(height[stack[top]], height[i]) * (i - stack[top] - 1)
                        - (sums[i - 1] - sums[stack[top]]);
                if (height[stack[top]] >= height[i]) {
                    stack[++top] = i;
                } else {
                    stack[top] = i;
                }
            }
        }
        return dp[n - 1];
    }
}
```