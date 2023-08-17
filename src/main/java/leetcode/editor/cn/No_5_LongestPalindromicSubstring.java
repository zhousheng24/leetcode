//Given a string s, return the longest palindromic substring in s. 
//
// 
// Example 1: 
//
// 
//Input: s = "babad"
//Output: "bab"
//Explanation: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: "bb"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consist of only digits and English letters. 
// 
//
// Related Topics 字符串 动态规划 👍 6695 👎 0


package leetcode.editor.cn;

// Java: Longest Palindromic Substring
public class No_5_LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new No_5_LongestPalindromicSubstring().new Solution();
        // TODO TEST
        String s = "abb";
        solution.longestPalindrome(s);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            // "babad"
            // "cbbd"
            // 1 <= s.length <= 1000
            // s consist of only digits and English letters.
            int len = s.length();
            float center = 0;
            String longest = "";
            while (center < len) {
                int l = (int) center;
                int r = (int) (center + 0.5);
                while (l >= 0 && r < s.length()) {
                    if (s.charAt(l) == s.charAt(r)) {
                        if (longest.length() < (r - l + 1)) {
                            longest = s.substring(l, r + 1);
                        }
                        l--;
                        r++;
                    } else {
                        break;
                    }

                }
                center += 0.5;
            }
            return longest;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}