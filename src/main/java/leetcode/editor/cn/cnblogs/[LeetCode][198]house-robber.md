# Content
<p>You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and <b>it will automatically contact the police if two adjacent houses were broken into on the same night</b>.</p>

<p>Given an integer array <code>nums</code> representing the amount of money of each house, return <em>the maximum amount of money you can rob tonight <b>without alerting the police</b></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,7,9,3,1]
<strong>Output:</strong> 12
<strong>Explanation:</strong> Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= nums.length &lt;= 100</code></li>
 <li><code>0 &lt;= nums[i] &lt;= 400</code></li>
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 2702</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int rob(int[] nums) {
        // 1 <= nums.length <= 100
        // 0 <= nums[i] <= 400
        int n = nums.length;
        // dp[i]表示最后抢劫编号（编号0是一个虚拟的房子，没有钱方便计算）为i的房子可以获得的最大金额
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        int max = nums[0];
        // max0表示房子编号在[0, i - 2]范围内所能抢到的最大金额
        int max0 = 0;
        for (int i = 2; i <= n; i++) {
            max0 = Math.max(max0, dp[i - 2]);
            dp[i] = max0 + nums[i - 1];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
```