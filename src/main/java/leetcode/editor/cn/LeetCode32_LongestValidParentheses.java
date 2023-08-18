//Given a string containing just the characters '(' and ')', return the length 
//of the longest valid (well-formed) parentheses substring. 
//
// 
// Example 1: 
//
// 
//Input: s = "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()".
// 
//
// Example 2: 
//
// 
//Input: s = ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()".
// 
//
// Example 3: 
//
// 
//Input: s = ""
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] is '(', or ')'. 
// 
//
// Related Topics 栈 字符串 动态规划 👍 2329 👎 0


package leetcode.editor.cn;

// [LeetCode][32]longest-valid-parentheses
public class LeetCode32_LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new LeetCode32_LongestValidParentheses().new Solution();
        System.out.println(solution.longestValidParentheses(")()())"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            // dp[i]表示字符串s中以i下标结尾的最长有效括号串的长度。
            int n = s.length();
            if (n < 2) {
                return 0;
            }
            int[] dp = new int[n];
            if (s.charAt(0) == '(' && s.charAt(1) == ')') {
                dp[1] = 2;
            }
            for (int i = 2; i < n; i++) {
                char c0 = s.charAt(i - 1);
                char c1 = s.charAt(i);
                if (c1 == ')') {
                    if (c0 == '(') {
                        dp[i] = dp[i - 2] + 2;
                    } else if (dp[i - 1] > 0) {
                        // i0表示s中以（i - 1）下标结尾的最长有效括号串前一个下标
                        int i0 = i - 1 - dp[i - 1];
                        if (i0 >= 0) {
                            char c2 = s.charAt(i0);
                            if (c2 == '(') {
                                dp[i] = (i0 > 0 ? dp[i0 - 1] : 0) + dp[i - 1] + 2;
                            }
                        }
                    }

                }
            }
            int longest = 0;
            for (int j = 0; j < n; j++) {
                longest = Math.max(longest, dp[j]);
            }
            return longest;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}