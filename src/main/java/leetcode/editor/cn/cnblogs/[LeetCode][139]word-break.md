# Content
<p>Given a string <code>s</code> and a dictionary of strings <code>wordDict</code>, return <code>true</code> if <code>s</code> can be segmented into a space-separated sequence of one or more dictionary words.</p>

<p><strong>Note</strong> that the same word in the dictionary may be reused multiple times in the segmentation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "leetcode", wordDict = ["leet","code"]
<strong>Output:</strong> true
<strong>Explanation:</strong> Return true because "leetcode" can be segmented as "leet code".
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = "applepenapple", wordDict = ["apple","pen"]
<strong>Output:</strong> true
<strong>Explanation:</strong> Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= s.length &lt;= 300</code></li>
 <li><code>1 &lt;= wordDict.length &lt;= 1000</code></li>
 <li><code>1 &lt;= wordDict[i].length &lt;= 20</code></li>
 <li><code>s</code> and <code>wordDict[i]</code> consist of only lowercase English letters.</li>
 <li>All the strings of <code>wordDict</code> are <strong>unique</strong>.</li>
</ul>

<div><div>Related Topics</div><div><li>字典树</li><li>记忆化搜索</li><li>数组</li><li>哈希表</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 2248</li><li>👎 0</li></div>

# Solution
## 1. 字典树 + DFS
### Java
```java
class Solution {
    DictNode head = new DictNode('\u0000');
    public boolean wordBreak(String s, List<String> wordDict) {
        // 1 <= s.length <= 300
        // 1 <= wordDict.length <= 1000
        // 1 <= wordDict[i].length <= 20
        // s and wordDict[i] consist of only lowercase English letters.
        // All the strings of wordDict are unique.
        List<String> filteredWordDict = wordDict.stream()
                .filter(o -> o.length() <= s.length())
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        List<String> newWordDict = new ArrayList<>();
        for (int i = 0; i < filteredWordDict.size(); i++) {
            String word0 = filteredWordDict.get(i);
            int len0 = word0.length();
            if (len0 > s.length()) {
                continue;
            }
            boolean ignore = false;
            for (int j = i + 1; j < filteredWordDict.size(); j++) {
                String word1 = filteredWordDict.get(j);
                int len1 = word1.length();
                if (len1 <= s.length() && !word1.equals(word0) && len1 / len0 > 1 && len1 % len0 == 0) {
                    int i0 = 0;
                    int j0 = 0;
                    while (j0 < len1) {
                        if (word1.charAt(j0) != word0.charAt(i0 % len0)) {
                            break;
                        }
                        i0++;
                        j0++;
                    }
                    if (j0 >= len1) {
                        ignore = true;
                        break;
                    }
                }
            }
            if (!ignore) {
                newWordDict.add(word0);
            }
        }
        // 初始化字典树
        DictNode dictNode;
        boolean[] hash = new boolean[26];
        for (String word : newWordDict) {
            dictNode = head;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int j = c - 'a';
                if (null == dictNode.children[j]) {
                    dictNode.children[j] = new DictNode(c);
                    hash[j] = true;
                }
                dictNode = dictNode.children[j];
            }
            dictNode.word = word;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!hash[s.charAt(i) - 'a']) {
                return false;
            }
        }
        return dfs(s, 0, head);
    }
    private boolean dfs(String s, int i, DictNode node) {
        if (i >= s.length()) {
            return node == head;
        }
        int j = s.charAt(i) - 'a';
        DictNode child = node.children[j];
        if (null == child) {
            return false;
        } else if (null != child.word) {
            return dfs(s, i + 1, head) || dfs(s, i + 1, child);
        } else {
            return dfs(s, i + 1, child);
        }
    }
}
class DictNode {
    char c;
    DictNode[] children;
    String word;
    public DictNode(char c) {
        this.c = c;
        children = new DictNode[26];
        word = null;
    }
}
```
## 2. 动态规划
### Java
```java
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
```