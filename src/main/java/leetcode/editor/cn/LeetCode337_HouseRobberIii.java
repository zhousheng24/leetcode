//The thief has found himself a new place for his thievery again. There is only 
//one entrance to this area, called root. 
//
// Besides the root, each house has one and only one parent house. After a tour,
// the smart thief realized that all houses in this place form a binary tree. It 
//will automatically contact the police if two directly-linked houses were broken 
//into on the same night. 
//
// Given the root of the binary tree, return the maximum amount of money the 
//thief can rob without alerting the police. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,2,3,null,3,null,1]
//Output: 7
//Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
// 
//
// Example 2: 
// 
// 
//Input: root = [3,4,5,1,3,null,1]
//Output: 9
//Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 10⁴]. 
// 0 <= Node.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1769 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

// [LeetCode][337]house-robber-iii
public class LeetCode337_HouseRobberIii {
    public static void main(String[] args) {
        Solution solution = new LeetCode337_HouseRobberIii().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        Map<TreeNode, Integer> stealCache = new HashMap<>();
        Map<TreeNode, Integer> notStealCache = new HashMap<>();

        public int rob(TreeNode root) {
            // The number of nodes in the tree is in the range [1, 10⁴].
            // 0 <= Node.val <= 10⁴
            TreeNode head = new TreeNode(0, root, null);
            dfs(head);
            return notStealCache.get(head);
        }

        private void dfs(TreeNode node) {
            if (null == node) {
                return;
            }
            dfs(node.left);
            dfs(node.right);
            Integer leftSteal = stealCache.getOrDefault(node.left, 0);
            Integer leftNotSteal = notStealCache.getOrDefault(node.left, 0);
            Integer rightSteal = stealCache.getOrDefault(node.right, 0);
            Integer rightNotSteal = notStealCache.getOrDefault(node.right, 0);
            stealCache.put(node, node.val + leftNotSteal + rightNotSteal);
            notStealCache.put(node, Math.max(leftSteal, leftNotSteal) + Math.max(rightSteal, rightNotSteal));
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}