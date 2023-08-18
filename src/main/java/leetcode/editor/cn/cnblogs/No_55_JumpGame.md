# Content
<p>You are given an integer array <code>nums</code>. You are initially positioned at the array's <strong>first index</strong>, and each element in the array represents your maximum jump length at that position.</p>

<p>Return <code>true</code><em> if you can reach the last index, or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,1,1,4]
<strong>Output:</strong> true
<strong>Explanation:</strong> Jump 1 step from index 0 to 1, then 3 steps to the last index.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,0,4]
<strong>Output:</strong> false
<strong>Explanation:</strong> You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 <li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 2433</li><li>👎 0</li></div>

# Solution
## 1. 贪心
### Java
```java
class Solution {
    Boolean[] cache;
    public boolean canJump(int[] nums) {
        // 1 <= nums.length <= 10⁴
           // 0 <= nums[i] <= 10⁵
           cache = new Boolean[nums.length];
           return dfs(nums, 0);
    }
    public boolean dfs(int[] nums, int pos) {
        if (pos >= nums.length - 1) {
            return true;
        }
        if (null != cache[pos]) {
            return cache[pos];
        }
        int steps = nums[pos];
        while (steps > 0) {
            if (dfs(nums, pos + steps)) {
                return true;
            }
            steps--;
        }
        cache[pos] = false;
        return false;
    }
}
```
## 2. 动态规划
### Java
```java
class Solution {
    public boolean canJump(int[] nums) {
        // 1 <= nums.length <= 10⁴
        // 0 <= nums[i] <= 10⁵
        int n = nums.length;
        // dp[i]表示下标为i的位置是否可到达
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int i = 0;
        while (i < n && dp[i]) {
            int j = Math.min(i + nums[i], n - 1);
            if (j == n - 1) {
                dp[j] = true;
                break;
            }
            while (j > i) {
                dp[j] = true;
                j--;
            }
            i++;
        }
        return dp[n - 1];
    }
}
```
## 3. 双指针
### Java
```java
class Solution {
    public boolean canJump(int[] nums) {
        // 1 <= nums.length <= 10⁴
        // 0 <= nums[i] <= 10⁵
        // l表示当前位置
        int l = 0;
        // r表示最远可到达的位置
        int r = 0;
        while (l <= r) {
            r = Math.max(r, l + nums[l]);
            if (r >= nums.length - 1) {
                return true;
            }
            l++;
        }
        return false;
    }
}
```
