//Given n pairs of parentheses, write a function to generate all combinations 
//of well-formed parentheses. 
//
// 
// Example 1: 
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// 
// Example 2: 
// Input: n = 1
//Output: ["()"]
// 
// 
// Constraints: 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 3345 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

// Java: Generate Parentheses
public class No_22_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new No_22_GenerateParentheses().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            // 1 <= n <= 8
            List<String> result = new ArrayList<>();
            dfs(result, new StringBuilder(), n, -1);
            return result;
        }

        private void dfs(List<String> result, StringBuilder builder, int n, int top) {
            if (n <= 0) {
                while (top >= 0) {
                    builder.append(')');
                    top--;
                }
                if (builder.length() > 0) {
                    result.add(builder.toString());
                }
            } else {
                if (top >= 0) {
                    dfs(result, new StringBuilder(builder).append(')'), n, top - 1);
                }
                dfs(result, new StringBuilder(builder).append('('), n - 1, top + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}