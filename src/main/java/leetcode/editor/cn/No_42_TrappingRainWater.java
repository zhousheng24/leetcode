//Given n non-negative integers representing an elevation map where the width 
//of each bar is 1, compute how much water it can trap after raining. 
//
// 
// Example 1: 
// 
// 
//Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [
//0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) 
//are being trapped.
// 
//
// Example 2: 
//
// 
//Input: height = [4,2,0,3,2,5]
//Output: 9
// 
//
// 
// Constraints: 
//
// 
// n == height.length 
// 1 <= n <= 2 * 10⁴ 
// 0 <= height[i] <= 10⁵ 
// 
//
// Related Topics 栈 数组 双指针 动态规划 单调栈 👍 4616 👎 0


package leetcode.editor.cn;

// Java: Trapping Rain Water
public class No_42_TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new No_42_TrappingRainWater().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int trap(int[] height) {
            // n == height.length
            // 1 <= n <= 2 * 10⁴
            // 0 <= height[i] <= 10⁵
            int n = height.length;
            // 单调递减栈
            int[] stack = new int[n];
            int top = 0;
            // 累加bar
            int[] sums = new int[n];
            sums[0] = height[0];
            for (int i = 1; i < n; i++) {
                sums[i] = sums[i - 1] + height[i];
            }
            // dp[i]表示以下标i所对应的bar作为最右边的bar时，能trap多少雨水。
            int[] dp = new int[n];
            for (int i = 1; i < n; i++) {
                if (height[i - 1] >= height[i]) {
                    stack[++top] = i;
                    dp[i] = dp[i - 1];
                } else {
                    while (top > 0 && height[stack[top]] < height[i]) {
                        top--;
                    }
                    dp[i] = dp[stack[top]]
                            + Math.min(height[stack[top]], height[i]) * (i - stack[top] - 1)
                            - (sums[i - 1] - sums[stack[top]]);
                    if (height[stack[top]] >= height[i]) {
                        stack[++top] = i;
                    } else {
                        stack[top] = i;
                    }
                }
            }
            return dp[n - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}