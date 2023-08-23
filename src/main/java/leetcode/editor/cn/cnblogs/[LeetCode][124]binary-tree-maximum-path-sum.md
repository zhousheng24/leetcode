# Content
<p>A <strong>path</strong> in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence <strong>at most once</strong>. Note that the path does not need to pass through the root.</p>

<p>The <strong>path sum</strong> of a path is the sum of the node's values in the path.</p>

<p>Given the <code>root</code> of a binary tree, return <em>the maximum <strong>path sum</strong> of any <strong>non-empty</strong> path</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx1.jpg" style="width: 322px; height: 182px;" />
<pre>
<strong>Input:</strong> root = [1,2,3]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The optimal path is 2 -&gt; 1 -&gt; 3 with a path sum of 2 + 1 + 3 = 6.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx2.jpg" />
<pre>
<strong>Input:</strong> root = [-10,9,20,null,null,15,7]
<strong>Output:</strong> 42
<strong>Explanation:</strong> The optimal path is 15 -&gt; 20 -&gt; 7 with a path sum of 15 + 20 + 7 = 42.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li>The number of nodes in the tree is in the range <code>[1, 3 * 10<sup>4</sup>]</code>.</li>
 <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>动态规划</li><li>二叉树</li></div></div><br><div><li>👍 2031</li><li>👎 0</li></div>

# Solution
## 1. 动态规划 + DFS
### Java
```java
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // The number of nodes in the tree is in the range [1, 3 * 10⁴]
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