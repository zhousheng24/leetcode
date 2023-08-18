//Given an integer array nums, find the subarray with the largest sum, and 
//return its sum. 
//
// 
// Example 1: 
//
// 
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: The subarray [4,-1,2,1] has the largest sum 6.
// 
//
// Example 2: 
//
// 
//Input: nums = [1]
//Output: 1
//Explanation: The subarray [1] has the largest sum 1.
// 
//
// Example 3: 
//
// 
//Input: nums = [5,4,-1,7,8]
//Output: 23
//Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
// Follow up: If you have figured out the O(n) solution, try coding another 
//solution using the divide and conquer approach, which is more subtle. 
//
// Related Topics 数组 分治 动态规划 👍 6253 👎 0


package leetcode.editor.cn;

// [LeetCode][53]maximum-subarray
public class LeetCode53_MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new LeetCode53_MaximumSubarray().new Solution();
        solution.maxSubArray(new int[]{-1});

    }

    //leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)

}