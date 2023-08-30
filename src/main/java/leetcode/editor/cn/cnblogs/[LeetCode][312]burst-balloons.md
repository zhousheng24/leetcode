# Content
<p>You are given <code>n</code> balloons, indexed from <code>0</code> to <code>n - 1</code>. Each balloon is painted with a number on it represented by an array <code>nums</code>. You are asked to burst all the balloons.</p>

<p>If you burst the <code>i<sup>th</sup></code> balloon, you will get <code>nums[i - 1] * nums[i] * nums[i + 1]</code> coins. If <code>i - 1</code> or <code>i + 1</code> goes out of bounds of the array, then treat it as if there is a balloon with a <code>1</code> painted on it.</p>

<p>Return <em>the maximum coins you can collect by bursting the balloons wisely</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,5,8]
<strong>Output:</strong> 167
<strong>Explanation:</strong>
nums = [3,1,5,8] --&gt; [3,5,8] --&gt; [3,8] --&gt; [8] --&gt; []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5]
<strong>Output:</strong> 10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>n == nums.length</code></li>
 <li><code>1 &lt;= n &lt;= 300</code></li>
 <li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1267</li><li>👎 0</li></div>

# Solution
## 1.动态规划
### Java
```java
class Solution {
    public int maxCoins(int[] nums) {
        // n == nums.length
        // 1 <= n <= 300
        // 0 <= nums[i] <= 100
        int n = nums.length;
        int[] plus = new int[n + 2];
        System.arraycopy(nums, 0, plus, 1, n);
        plus[0] = 1;
        plus[plus.length - 1] = 1;
        // dp[i][j]表示戳爆开区间(i,j)内的气球所能获取到最多的硬币数
        // 所谓开区间表示坐标为i和j的气球不可以戳爆
        // i < k < j，假设k是最后一个戳爆的气球，那么dp[i][j] = dp[i][k] + dp[k][j] + plus[i] * plus[j] * plus[k]
        int[][] dp = new int[plus.length][plus.length];
        // interval表示区间(i,j)的长度
        int interval = 3;
        while (interval <= plus.length) {
            int i = 0;
            while (i <= plus.length - interval) {
                int j = i + interval - 1;
                int k = i + 1;
                while (k < j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + plus[i] * plus[j] * plus[k]);
                    k++;
                }
                i++;
            }
            interval++;
        }
        return dp[0][plus.length - 1];
    }
}
```