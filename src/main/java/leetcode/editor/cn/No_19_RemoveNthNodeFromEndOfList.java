//Given the head of a linked list, remove the nᵗʰ node from the end of the list 
//and return its head. 
//
// 
// Example 1: 
// 
// 
//Input: head = [1,2,3,4,5], n = 2
//Output: [1,2,3,5]
// 
//
// Example 2: 
//
// 
//Input: head = [1], n = 1
//Output: []
// 
//
// Example 3: 
//
// 
//Input: head = [1,2], n = 1
//Output: [1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the list is sz. 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
// Follow up: Could you do this in one pass? 
//
// Related Topics 链表 双指针 👍 2634 👎 0


package leetcode.editor.cn;

// Java: Remove Nth Node From End of List
public class No_19_RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new No_19_RemoveNthNodeFromEndOfList().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // The number of nodes in the list is sz.
            // 1 <= sz <= 30
            // 0 <= Node.val <= 100
            // 1 <= n <= sz
            ListNode pre = new ListNode(0, head);
            int i = 0;
            ListNode removePre = pre;
            ListNode node = pre;
            while (null != node.next) {
                node = node.next;
                i++;
                if (i > n) {
                    removePre = removePre.next;
                }
            }
            ListNode remove = removePre.next;
            removePre.next = remove.next;
            remove.next = null;
            return pre.next;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}