//Given a string containing digits from 2-9 inclusive, return all possible 
//letter combinations that the number could represent. Return the answer in any order. 
//
//
// A mapping of digits to letters (just like on the telephone buttons) is given 
//below. Note that 1 does not map to any letters. 
// 
// 
// Example 1: 
//
// 
//Input: digits = "23"
//Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// Example 2: 
//
// 
//Input: digits = ""
//Output: []
// 
//
// Example 3: 
//
// 
//Input: digits = "2"
//Output: ["a","b","c"]
// 
//
// 
// Constraints: 
//
// 
// 0 <= digits.length <= 4 
// digits[i] is a digit in the range ['2', '9']. 
// 
//
// Related Topics 哈希表 字符串 回溯 👍 2577 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

// Java: Letter Combinations of a Phone Number
public class No_17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new No_17_LetterCombinationsOfAPhoneNumber().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 2-abc
        // 3-def
        // 4-gih
        // 5-jkl
        // 6-mno
        // 7-pqrs
        // 8-tuv
        // 9-wxyz
        final char[][] map = new char[][]{
                {},
                {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'i', 'h'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        public List<String> letterCombinations(String digits) {
            // 0 <= digits.length <= 4
            // digits[i] is a digit in the range ['2', '9'].
            List<String> result = new ArrayList<>();
            dfs(digits, 0, new StringBuilder(), result);
            return result;
        }

        public void dfs(String digits, int i, StringBuilder builder, List<String> result) {
            if (i >= digits.length()) {
                if (builder.length() > 0) {
                    result.add(builder.toString());
                }
                return;
            }
            int num = Integer.parseInt(String.valueOf(digits.charAt(i)));
            for (char c : map[num]) {
                StringBuilder newBuilder = new StringBuilder(builder);
                newBuilder.append(c);
                dfs(digits, i + 1, newBuilder, result);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}