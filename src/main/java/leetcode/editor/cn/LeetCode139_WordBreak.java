//Given a string s and a dictionary of strings wordDict, return true if s can 
//be segmented into a space-separated sequence of one or more dictionary words. 
//
// Note that the same word in the dictionary may be reused multiple times in 
//the segmentation. 
//
// 
// Example 1: 
//
// 
//Input: s = "leetcode", wordDict = ["leet","code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".
// 
//
// Example 2: 
//
// 
//Input: s = "applepenapple", wordDict = ["apple","pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple 
//pen apple".
//Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//Output: false
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s and wordDict[i] consist of only lowercase English letters. 
// All the strings of wordDict are unique. 
// 
//
// Related Topics 字典树 记忆化搜索 数组 哈希表 字符串 动态规划 👍 2248 👎 0


package leetcode.editor.cn;

import java.util.*;

// [LeetCode][139]word-break
public class LeetCode139_WordBreak {
    public static void main(String[] args) {
        Solution solution = new LeetCode139_WordBreak().new Solution();
        solution.wordBreak("leetcode", Arrays.asList("leet", "code"));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            // 1 <= s.length <= 300
            // 1 <= wordDict.length <= 1000
            // 1 <= wordDict[i].length <= 20
            // s and wordDict[i] consist of only lowercase English letters.
            // All the strings of wordDict are unique.
            Map<Integer, Set<String>> words = new TreeMap<>(Comparator.naturalOrder());
            for (String word : wordDict) {
                words.computeIfAbsent(word.length(), i -> new HashSet<>()).add(word);
            }
            int n = s.length();
            // dp[i]表示包括左端点长度为i的子串是否符合要求
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 1; i <= n; i++) {
                for (Map.Entry<Integer, Set<String>> entry : words.entrySet()) {
                    Integer wordLen = entry.getKey();
                    Set<String> hash = entry.getValue();
                    int j = i - wordLen;
                    dp[i] = j >= 0 && dp[j] && hash.contains(s.substring(j, i));
                    if (j < 0 || dp[i]) {
                        break;
                    }
                }
            }
            return dp[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}