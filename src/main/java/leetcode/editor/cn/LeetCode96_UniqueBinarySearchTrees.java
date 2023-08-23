//Given an integer n, return the number of structurally unique BST's (binary 
//search trees) which has exactly n nodes of unique values from 1 to n. 
//
// 
// Example 1: 
// 
// 
//Input: n = 3
//Output: 5
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 19 
// 
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 2338 👎 0


package leetcode.editor.cn;

// [LeetCode][96]unique-binary-search-trees
public class LeetCode96_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new LeetCode96_UniqueBinarySearchTrees().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numTrees(int n) {
            // 1 <= n <= 19
            // dp[i]表示包含[1,i]结点的不同二叉搜索树的个数
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                int j = 0;
                while (j <= i - 1) {
                    dp[i] += dp[j] * dp[i - 1 - j];
                    j++;
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}