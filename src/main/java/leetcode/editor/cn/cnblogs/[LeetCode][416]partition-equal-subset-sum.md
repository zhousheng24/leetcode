# Content
<p>Given an integer array <code>nums</code>, return <code>true</code> <em>if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,11,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array can be partitioned as [1, 5, 5] and [11].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,5]
<strong>Output:</strong> false
<strong>Explanation:</strong> The array cannot be partitioned into equal sum subsets.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= nums.length &lt;= 200</code></li>
 <li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1865</li><li>👎 0</li></div>

# Solution
## 1.动态规划 + 01背包
### Java
```java
class Solution {
    public boolean canPartition(int[] nums) {
        // 1 <= nums.length <= 200
        // 1 <= nums[i] <= 100
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int half = sum >> 1;
        int n = nums.length;
        // dp[i][j]表示前i个数字中任意个数字累加的最大值，上限为j
        int[][] dp = new int[n + 1][half + 1];
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            if (num <= half) {
                dp[i][half] = num + dp[i - 1][half - num];
                if (dp[i][half] == half) {
                    return true;
                }
            }
            for (int j = half - 1; j >= 0; j--) {
                // 不累加当前数字
                dp[i][j] = dp[i - 1][j];
                // 累加当前数字
                if (num <= j) {
                    dp[i][j] = Math.max(dp[i][j], num + dp[i - 1][j - num]);
                }
            }
        }
        return false;
    }
}
```
## 2.动态规划（空间优化）
### Java
```java
class Solution {
    public boolean canPartition(int[] nums) {
        // 1 <= nums.length <= 200
        // 1 <= nums[i] <= 100
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int half = sum >> 1;
        int n = nums.length;
        // dp[j]表示前i个数字中任意个数字累加的最大值，上限为j
        int[] dp = new int[half + 1];
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            if (num <= half) {
                dp[half] = num + dp[half - num];
                if (dp[half] == half) {
                    return true;
                }
            }
            for (int j = half - 1; j > 0; j--) {
                // 累加当前数字
                if (num <= j) {
                    dp[j] = Math.max(dp[j], num + dp[j - num]);
                }
            }
        }
        return false;
    }
}
```