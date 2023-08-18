# Content
<p>Given an integer array <code>nums</code>, find the <span data-keyword="subarray-nonempty">subarray</span> with the largest sum, and return <em>its sum</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The subarray [4,-1,2,1] has the largest sum 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The subarray [1] has the largest sum 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,4,-1,7,8]
<strong>Output:</strong> 23
<strong>Explanation:</strong> The subarray [5,4,-1,7,8] has the largest sum 23.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> If you have figured out the <code>O(n)</code> solution, try coding another solution using the <strong>divide and conquer</strong> approach, which is more subtle.</p>

<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>动态规划</li></div></div><br><div><li>👍 6253</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // 1 <= nums.length <= 10⁵
        // -10⁴ <= nums[i] <= 10⁴
        int n = nums.length;
        // dp[i]表示以下标i结尾的求和最大的子数组的求和的值
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
```
## 2. 分治法
### Java
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // 1 <= nums.length <= 10⁵
        // -10⁴ <= nums[i] <= 10⁴
        Status status = get(nums, 0, nums.length - 1);
        return status.mSum;
    }

    public Status get(int[] nums, int l, int r) {
        if (l > r) {
            return new Status(0, 0, 0, 0);
        } else if (l == r) {
            return new Status(nums[l], nums[l], nums[l], nums[l]);
        } else {
            int mid = l + r >> 1;
            Status lStatus = get(nums, l, mid);
            Status rStatus = get(nums, mid + 1, r);
            int lSum = Math.max(lStatus.lSum, lStatus.iSum + rStatus.lSum);
            int rSum = Math.max(rStatus.rSum, rStatus.iSum + lStatus.rSum);
            int mSum = Math.max(
                    // 不跨区比较
                    Math.max(lStatus.mSum, rStatus.mSum),
                    // 跨区比较
                    Math.max(
                            Math.max(lSum, rSum),
                            lStatus.rSum + rStatus.lSum
                    )
            );
            int iSum = lStatus.iSum + rStatus.iSum;
            return new Status(lSum, rSum, mSum, iSum);
        }

    }

    /**
     * [l,r]区间状态
     */
    class Status {
        // 表示 [l,r] 内以 l 为左端点的最大子段和
        private final int lSum;
        // 表示 [l,r] 内以 r 为右端点的最大子段和
        private final int rSum;
        // 表示 [l,r] 内的最大子段和
        private final int mSum;
        // 表示 [l,r] 的区间和
        private final int iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }


}
```