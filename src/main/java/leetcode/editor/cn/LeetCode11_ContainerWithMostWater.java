//You are given an integer array height of length n. There are n vertical lines 
//drawn such that the two endpoints of the iᵗʰ line are (i, 0) and (i, height[i]).
// 
//
// Find two lines that together with the x-axis form a container, such that the 
//container contains the most water. 
//
// Return the maximum amount of water a container can store. 
//
// Notice that you may not slant the container. 
//
// 
// Example 1: 
// 
// 
//Input: height = [1,8,6,2,5,4,8,3,7]
//Output: 49
//Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,
//3,7]. In this case, the max area of water (blue section) the container can 
//contain is 49.
// 
//
// Example 2: 
//
// 
//Input: height = [1,1]
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
//
// Related Topics 贪心 数组 双指针 👍 4477 👎 0


package leetcode.editor.cn;

// [LeetCode][11]container-with-most-water
public class LeetCode11_ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new LeetCode11_ContainerWithMostWater().new Solution();
        // [1,8,6,2,5,4,8,3,7]
        // [1,2]
        //[1,2,1]
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        solution.maxArea(heights);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            // 2 <= n <= 10⁵
            // 0 <= height[i] <= 10⁴
            int n = height.length;
            int max = 0;
            int l = 0;
            int r = n - 1;
            while (r > l) {
                int h0 = height[l];
                int h1 = height[r];
                max = Math.max(max, Math.min(h0, h1) * (r - l));
                if (h0 <= h1) {
                    l++;
                } else {
                    r--;
                }
            }
            return max;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}