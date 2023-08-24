# Content
<p>Given an integer array <code>nums</code>, find a <span data-keyword="subarray-nonempty">subarray</span> that has the largest product, and return <em>the product</em>.</p>

<p>The test cases are generated so that the answer will fit in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-2,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> [2,3] has the largest product 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The result cannot be 2, because [-2,-1] is not a subarray.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
 <li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
 <li>The product of any prefix or suffix of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 2083</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int maxProduct(int[] nums) {
        // 1 <= nums.length <= 2 * 10⁴
        // -10 <= nums[i] <= 10
        // The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
        //integer.
        int n = nums.length;
        // dp[i][0]表示以下标为i的数字结尾的子数组的最大乘积
        // dp[i][1]表示以下标为i的数字结尾的子数组的最小乘积
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if (nums[i] > 0) {
                dp[i][0] = dp[i - 1][0] > 0 ? dp[i - 1][0] * nums[i] : nums[i];
                dp[i][1] = dp[i - 1][1] > 0 ? nums[i] : dp[i - 1][1] * nums[i];
            } else {
                dp[i][0] = dp[i - 1][1] > 0 ? nums[i] : dp[i - 1][1] * nums[i];
                dp[i][1] = dp[i - 1][0] > 0 ? dp[i - 1][0] * nums[i] : nums[i];
            }
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }
}
```