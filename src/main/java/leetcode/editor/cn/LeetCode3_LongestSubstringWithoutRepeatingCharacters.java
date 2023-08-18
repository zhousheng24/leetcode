//Given a string s, find the length of the longest substring without repeating 
//characters. 
//
// 
// Example 1: 
//
// 
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
// 
//
// Example 2: 
//
// 
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// Example 3: 
//
// 
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a 
//substring.
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s consists of English letters, digits, symbols and spaces. 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 9492 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

// [LeetCode][3]longest-substring-without-repeating-characters
public class LeetCode3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LeetCode3_LongestSubstringWithoutRepeatingCharacters().new Solution();
        String s = "abba";
        solution.lengthOfLongestSubstring(s);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int resultLen = 0;
            int l = 0;
            int[] hash = new int[128];
            Arrays.fill(hash, -1);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (-1 != hash[c] && hash[c] >= l) {
                    l = hash[c] + 1;
                }
                hash[c] = i;
                resultLen = Math.max(resultLen, i - l + 1);
            }
            return resultLen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}