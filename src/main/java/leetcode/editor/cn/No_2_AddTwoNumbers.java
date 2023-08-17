//You are given two non-empty linked lists representing two non-negative 
//integers. The digits are stored in reverse order, and each of their nodes contains a 
//single digit. Add the two numbers and return the sum as a linked list. 
//
// You may assume the two numbers do not contain any leading zero, except the 
//number 0 itself. 
//
// 
// Example 1: 
// 
// 
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
// 
//
// Example 2: 
//
// 
//Input: l1 = [0], l2 = [0]
//Output: [0]
// 
//
// Example 3: 
//
// 
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in each linked list is in the range [1, 100]. 
// 0 <= Node.val <= 9 
// It is guaranteed that the list represents a number that does not have 
//leading zeros. 
// 
//
// Related Topics 递归 链表 数学 👍 9855 👎 0


package leetcode.editor.cn;

// Java: Add Two Numbers
public class No_2_AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new No_2_AddTwoNumbers().new Solution();
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
        static final int MOD = 10;

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0, null);
            next(head, l1, l2, 0);
            return head.next;
        }

        public void next(ListNode l0, ListNode l1, ListNode l2, int carry) {
            int num1 = 0;
            int num2 = 0;
            if (null != l1 && null != l2) {
                num1 = l1.val;
                num2 = l2.val;
            } else if (null != l2) {
                num2 = l2.val;
            } else if (null != l1) {
                num1 = l1.val;
            } else {
                if (carry == 1) {
                    l0.next = new ListNode(1);
                }
                return;
            }
            int sum = num1 + num2 + carry;
            int digit = sum % MOD;
            carry = sum / MOD;
            ListNode node = new ListNode(digit);
            l0.next = node;
            next(node, null == l1 ? null : l1.next, null == l2 ? null : l2.next, carry);
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