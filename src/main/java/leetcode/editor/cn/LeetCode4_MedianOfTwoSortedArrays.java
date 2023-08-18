//Given two sorted arrays nums1 and nums2 of size m and n respectively, return 
//the median of the two sorted arrays. 
//
// The overall run time complexity should be O(log (m+n)). 
//
// 
// Example 1: 
//
// 
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
// 
//
// Example 2: 
//
// 
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
// 
//
// 
// Constraints: 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 6699 👎 0


package leetcode.editor.cn;

// [LeetCode][4]median-of-two-sorted-arrays
public class LeetCode4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new LeetCode4_MedianOfTwoSortedArrays().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            if (m + n == 0) {
                return 0.0;
            }
            int median1 = (m + n - 1) >> 1;
            int median2 = (m + n) >> 1;
            int median1Val = 0;
            int median2Val = 0;
            int i = 0;
            int j = 0;
            int k = 0;
            while (true) {
                int target;
                if (i >= m) {
                    target = nums2[j++];
                } else if (j >= n) {
                    target = nums1[i++];
                } else if (nums1[i] <= nums2[j]) {
                    target = nums1[i++];
                } else {
                    target = nums2[j++];
                }
                if (k == median1) {
                    median1Val = target;
                }
                if (k == median2) {
                    median2Val = target;
                    break;
                }
                k++;
            }
            return (median1Val + median2Val) / 2.0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}