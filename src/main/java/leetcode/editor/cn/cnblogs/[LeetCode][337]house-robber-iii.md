# Content
<p>The thief has found himself a new place for his thievery again. There is only one entrance to this area, called <code>root</code>.</p>

<p>Besides the <code>root</code>, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if <strong>two directly-linked houses were broken into on the same night</strong>.</p>

<p>Given the <code>root</code> of the binary tree, return <em>the maximum amount of money the thief can rob <strong>without alerting the police</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/10/rob1-tree.jpg" style="width: 277px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,2,3,null,3,null,1]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/10/rob2-tree.jpg" style="width: 357px; height: 293px;" />
<pre>
<strong>Input:</strong> root = [3,4,5,1,3,null,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong> Maximum amount of money the thief can rob = 4 + 5 = 9.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li>
 <li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>动态规划</li><li>二叉树</li></div></div><br><div><li>👍 1769</li><li>👎 0</li></div>

# Solution
## 1. DFS + 动态规划
### Java
```java
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
```