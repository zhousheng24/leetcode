//You are given an integer array nums and an integer target. 
//
// You want to build an expression out of nums by adding one of the symbols '+' 
//and '-' before each integer in nums and then concatenate all the integers. 
//
// 
// For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 
//and concatenate them to build the expression "+2-1". 
// 
//
// Return the number of different expressions that you can build, which 
//evaluates to target. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,1,1,1,1], target = 3
//Output: 5
//Explanation: There are 5 ways to assign symbols to make the sum of nums be 
//target 3.
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// Example 2: 
//
// 
//Input: nums = [1], target = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
//
// Related Topics 数组 动态规划 回溯 👍 1729 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

// [LeetCode][494]target-sum
public class LeetCode494_TargetSum {
    public static void main(String[] args) {
        Solution solution = new LeetCode494_TargetSum().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)

}