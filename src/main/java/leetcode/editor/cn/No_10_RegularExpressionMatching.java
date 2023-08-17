//Given an input string s and a pattern p, implement regular expression 
//matching with support for '.' and '*' where: 
//
// 
// '.' Matches any single character. 
// '*' Matches zero or more of the preceding element. 
// 
//
// The matching should cover the entire input string (not partial). 
//
// 
// Example 1: 
//
// 
//Input: s = "aa", p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
// 
//
// Example 2: 
//
// 
//Input: s = "aa", p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, 
//by repeating 'a' once, it becomes "aa".
// 
//
// Example 3: 
//
// 
//Input: s = "ab", p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 20 
// s contains only lowercase English letters. 
// p contains only lowercase English letters, '.', and '*'. 
// It is guaranteed for each appearance of the character '*', there will be a 
//previous valid character to match. 
// 
//
// Related Topics 递归 字符串 动态规划 👍 3672 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

// Java: Regular Expression Matching
public class No_10_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new No_10_RegularExpressionMatching().new Solution();
        String s0 = "aaa";
        String p0 = "a*a";
        System.out.println(solution.isMatch(s0, p0));
        String s1 = "aaa";
        String p1 = "aaaa";
        solution.isMatch(s1, p1);
        String s2 = "cabbbbcbcacbabc";
        String p2 = ".*b.*.ab*.*b*a*c";
        solution.isMatch(s2, p2);
        String s3 = "mississippi";
        String p3 = "mis*is*p*.";
        solution.isMatch(s3, p3);
        String s4 = "a";
        String p4 = "ab*";
        solution.isMatch(s4, p4);
        String s5 = "aaaaaaaaaaaaaaaaaaab";
        String p5 = "a*a*a*a*a*a*a*a*a*a*";
        System.out.println(solution.isMatch(s5, p5));
        String s6 = "abc";
        String p6 = "a***abc";
        System.out.println(solution.isMatch(s6, p6));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        static final String FREQ_ONE = "1";
        static final String FREQ_ANY = "*";

        // '.' Matches any single character.
        // '*' Matches zero or more of the preceding element.
        public boolean isMatch(String s, String p) {
            List<Group> groupPatterns = groupPattern(p);
            return doMatch(s, 0, groupPatterns, 0);
        }

        private boolean doMatch(String s, int i, List<Group> groups, int j) {
            if (i >= s.length()) {
                return j >= groups.size() || FREQ_ANY.equals(groups.get(j).freq) && doMatch(s, i, groups, j + 1);
            } else if (j >= groups.size()) {
                return false;
            }
            Group group = groups.get(j);
            if (FREQ_ANY.equals(group.freq)) {
                char p0 = group.p.charAt(0);
                char s0 = s.charAt(i);
                if (p0 == '.' || s0 == p0) {
                    return doMatch(s, i, groups, j + 1) || doMatch(s, i + 1, groups, j);
                } else {
                    return doMatch(s, i, groups, j + 1);
                }
            } else if (FREQ_ONE.equals(group.freq)) {
                if (s.length() - i < group.p.length()) {
                    return false;
                } else {
                    int l = 0;
                    while (l < group.p.length()) {
                        char s0 = s.charAt(i);
                        char p0 = group.p.charAt(l);
                        if (p0 != '.' && p0 != s0) {
                            return false;
                        }
                        l++;
                        i++;
                    }
                    return doMatch(s, i, groups, j + 1);
                }
            }
            return false;
        }

        private List<Group> groupPattern(String p) {
            int length = p.length();
            int l = 0;
            int r = 0;
            List<Group> groups = new ArrayList<>();
            Group pre = null;
            while (r < length) {
                char c = p.charAt(r);
                if (c == '*') {
                    if (r == 0 || p.charAt(r - 1) != '*') {
                        String p0 = p.substring(l, r - 1);
                        if (r - 1 > l) {
                            pre = new Group(p0, FREQ_ONE);
                            groups.add(pre);
                        }
                        String p1 = p.substring(r - 1, r);
                        // 连续的重复pattern处理，比如"a*a*a*a*"
                        if (null == pre || !FREQ_ANY.equals(pre.freq) || !p1.equals(pre.p)) {
                            pre = new Group(p1, FREQ_ANY);
                            groups.add(pre);
                        }
                    }
                    l = r + 1;
                }
                r++;
            }
            if (l < length) {
                groups.add(new Group(p.substring(l, length), FREQ_ONE));
            }
            return groups;
        }

        class Group {
            String p;
            String freq;

            public Group(String p, String freq) {
                this.p = p;
                this.freq = freq;
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}