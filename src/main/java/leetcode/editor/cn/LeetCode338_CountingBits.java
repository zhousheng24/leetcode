//Given an integer n, return an array ans of length n + 1 such that for each i (
//0 <= i <= n), ans[i] is the number of 1's in the binary representation of i. 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: [0,1,1]
//Explanation:
//0 --> 0
//1 --> 1
//2 --> 10
// 
//
// Example 2: 
//
// 
//Input: n = 5
//Output: [0,1,1,2,1,2]
//Explanation:
//0 --> 0
//1 --> 1
//2 --> 10
//3 --> 11
//4 --> 100
//5 --> 101
// 
//
// 
// Constraints: 
//
// 
// 0 <= n <= 10⁵ 
// 
//
// 
// Follow up: 
//
// 
// It is very easy to come up with a solution with a runtime of O(n log n). Can 
//you do it in linear time O(n) and possibly in a single pass? 
// Can you do it without using any built-in function (i.e., like __builtin_
//popcount in C++)? 
// 
//
// Related Topics 位运算 动态规划 👍 1247 👎 0


package leetcode.editor.cn;

// [LeetCode][338]counting-bits
public class LeetCode338_CountingBits {
    public static void main(String[] args) {
        Solution solution = new LeetCode338_CountingBits().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] countBits(int n) {
            // 0 <= n <= 10⁵
            int[] ans = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                ans[i] = (i & 1) + ans[i >> 1];
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}