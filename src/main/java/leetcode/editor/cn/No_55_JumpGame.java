//You are given an integer array nums. You are initially positioned at the 
//array's first index, and each element in the array represents your maximum jump 
//length at that position. 
//
// Return true if you can reach the last index, or false otherwise. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum 
//jump length is 0, which makes it impossible to reach the last index.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 10⁵ 
// 
//
// Related Topics 贪心 数组 动态规划 👍 2433 👎 0


package leetcode.editor.cn;

// Java: Jump Game
public class No_55_JumpGame {
    public static void main(String[] args) {
        Solution solution = new No_55_JumpGame().new Solution();
        solution.canJump(new int[]{3, 2, 1});

    }

    //leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)

}