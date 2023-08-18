# Content
<p>Given an input string <code>s</code>&nbsp;and a pattern <code>p</code>, implement regular expression matching with support for <code>'.'</code> and <code>'*'</code> where:</p>

<ul>
 <li><code>'.'</code> Matches any single character.​​​​</li>
 <li><code>'*'</code> Matches zero or more of the preceding element.</li>
</ul>

<p>The matching should cover the <strong>entire</strong> input string (not partial).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "aa", p = "a"
<strong>Output:</strong> false
<strong>Explanation:</strong> "a" does not match the entire string "aa".
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = "aa", p = "a*"
<strong>Output:</strong> true
<strong>Explanation:</strong> '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = "ab", p = ".*"
<strong>Output:</strong> true
<strong>Explanation:</strong> ".*" means "zero or more (*) of any character (.)".
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
 <li><code>1 &lt;= s.length&nbsp;&lt;= 20</code></li>
 <li><code>1 &lt;= p.length&nbsp;&lt;= 20</code></li>
 <li><code>s</code> contains only lowercase English letters.</li>
 <li><code>p</code> contains only lowercase English letters, <code>'.'</code>, and&nbsp;<code>'*'</code>.</li>
 <li>It is guaranteed for each appearance of the character <code>'*'</code>, there will be a previous valid character to match.</li>
</ul>

<div><div>Related Topics</div><div><li>递归</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 3672</li><li>👎 0</li></div>

# Solution
## DFS
### Java
```java
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
```