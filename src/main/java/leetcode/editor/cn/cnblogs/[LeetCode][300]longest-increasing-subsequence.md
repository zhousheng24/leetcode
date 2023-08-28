# Content
<p>Given an integer array <code>nums</code>, return <em>the length of the longest <strong>strictly increasing </strong></em><span data-keyword="subsequence-array"><em><strong>subsequence</strong></em></span>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,9,2,5,3,7,101,18]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,0,3,2,3]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,7,7,7,7,7,7]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= nums.length &lt;= 2500</code></li>
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><b>Follow up:</b>&nbsp;Can you come up with an algorithm that runs in&nbsp;<code>O(n log(n))</code> time complexity?</p>

<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>动态规划</li></div></div><br><div><li>👍 3353</li><li>👎 0</li></div>

# Solution
## 1. 动态规划
### Java
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        // 1 <= nums.length <= 2500
        // -10⁴ <= nums[i] <= 10⁴
        int n = nums.length;
        // dp[i]表示以下标为i的元素结尾的最长子序列的长度
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            // 遍历i之前的所有数字，
            // 1. 当nums[j] < nums[i]时，nums[i]可以添加到dp[j]所代表的子序列之后。
            // 2. 当nums[j] == nums[i]时，nums[i]可以替换调dp[j]所代表的子序列的最后一个数字即nums[j]。
            // 3. 当num[j] > nums[j]时，跳过。
            while (j >= 0) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                } else if (nums[j] == nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
                j--;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
```