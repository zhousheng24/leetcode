//Given an integer array nums, return all the triplets [nums[i], nums[j], nums[
//k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0. 
//
// Notice that the solution set must not contain duplicate triplets. 
//
// 
// Example 1: 
//
// 
//Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]]
//Explanation: 
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
//The distinct triplets are [-1,0,1] and [-1,-1,2].
//Notice that the order of the output and the order of the triplets does not 
//matter.
// 
//
// Example 2: 
//
// 
//Input: nums = [0,1,1]
//Output: []
//Explanation: The only possible triplet does not sum up to 0.
// 
//
// Example 3: 
//
// 
//Input: nums = [0,0,0]
//Output: [[0,0,0]]
//Explanation: The only possible triplet sums up to 0.
// 
//
// 
// Constraints: 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 6238 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Java: 3Sum
public class No_15_ThreeSum {
    public static void main(String[] args) {
        Solution solution = new No_15_ThreeSum().new Solution();
        // [-1,0,1,2,-1,-4]
        int[] nums0 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result0 = solution.threeSum(nums0);
        System.out.println(result0);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            // 3 <= nums.length <= 3000
            // -10⁵ <= nums[i] <= 10⁵
            Arrays.sort(nums);
            int n = nums.length;
            List<List<Integer>> result = new ArrayList<>();
            int i = 0;
            while (i < n - 2) {
                int j = i + 1;
                int k = n - 1;
                while (j < k) {
                    int third = -nums[i] - nums[j];
                    while (j < k && nums[k] > third) {
                        k--;
                    }
                    if (j < k && nums[k] == third) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        do {
                            k--;
                        } while (j < k && nums[k] == nums[k + 1]);
                    }
                    do {
                        j++;
                    } while (j < k && nums[j] == nums[j - 1]);
                }
                do {
                    i++;
                } while (i < n - 2 && nums[i] == nums[i - 1]);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}