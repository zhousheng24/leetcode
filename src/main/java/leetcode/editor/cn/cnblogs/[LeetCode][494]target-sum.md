# Content
<p>You are given an integer array <code>nums</code> and an integer <code>target</code>.</p>

<p>You want to build an <strong>expression</strong> out of nums by adding one of the symbols <code>'+'</code> and <code>'-'</code> before each integer in nums and then concatenate all the integers.</p>

<ul>
 <li>For example, if <code>nums = [2, 1]</code>, you can add a <code>'+'</code> before <code>2</code> and a <code>'-'</code> before <code>1</code> and concatenate them to build the expression <code>"+2-1"</code>.</li>
</ul>

<p>Return the number of different <strong>expressions</strong> that you can build, which evaluates to <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], target = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], target = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= nums.length &lt;= 20</code></li>
 <li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
 <li><code>0 &lt;= sum(nums[i]) &lt;= 1000</code></li>
 <li><code>-1000 &lt;= target &lt;= 1000</code></li>
</ul>

<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 1729</li><li>👎 0</li></div>

# Solution
## 1.动态规划
### Java
```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        // 1 <= nums.length <= 20
        // 0 <= nums[i] <= 1000
        // 0 <= sum(nums[i]) <= 1000
        // -1000 <= target <= 1000
        int n = nums.length;
        Map<Integer, Integer> cache = new HashMap<>();
        if (nums[0] == 0) {
            cache.put(0, 2);
        } else {
            cache.put(nums[0], 1);
            cache.put(-nums[0], 1);
        }
        for (int i = 1; i < n; i++) {
            Map<Integer, Integer> cache0 = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
                Integer pre = entry.getKey();
                Integer count = entry.getValue();
                int plus = pre + nums[i];
                int minus = pre - nums[i];
                cache0.put(plus, cache0.getOrDefault(plus, 0) + count);
                cache0.put(minus, cache0.getOrDefault(minus, 0) + count);
            }
            cache = cache0;
        }
        return cache.getOrDefault(target, 0);
    }
}
```