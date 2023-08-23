//A path in a binary tree is a sequence of nodes where each pair of adjacent 
//nodes in the sequence has an edge connecting them. A node can only appear in the 
//sequence at most once. Note that the path does not need to pass through the root. 
//
//
// The path sum of a path is the sum of the node's values in the path. 
//
// Given the root of a binary tree, return the maximum path sum of any non-
//empty path. 
//
// 
// Example 1: 
// 
// 
//Input: root = [1,2,3]
//Output: 6
//Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//
// 
//
// Example 2: 
// 
// 
//Input: root = [-10,9,20,null,null,15,7]
//Output: 42
//Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 
//= 42.
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [1, 3 * 10⁴]. 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 2031 👎 0


package leetcode.editor.cn;

// [LeetCode][124]binary-tree-maximum-path-sum
public class LeetCode124_BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        Solution solution = new LeetCode124_BinaryTreeMaximumPathSum().new Solution();
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
        int max = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            // The number of nodes in the tree is in the range [1, 3 * 10⁴].
            // -1000 <= Node.val <= 1000
            dfs(0, root);
            return max;
        }

        /**
         * 深度优先搜素
         *
         * @param sum  当前结点的父结点作为path的最后一个端点，path的最大sum
         * @param node 当前结点
         * @return 以当前结点作为起始端点的最大长度
         */
        private int dfs(int sum, TreeNode node) {
            if (null == node) {
                return 0;
            }
            sum = sum <= 0 ? node.val : sum + node.val;
            max = Math.max(max, sum);
            int left = Math.max(0, dfs(sum, node.left));
            int right = Math.max(0, dfs(sum, node.right));
            // 在以当前结点为顶点的子树中，经过当前结点的最大值
            int max0 = node.val + left + right;
            max = Math.max(max, max0);
            return node.val + Math.max(left, right);
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