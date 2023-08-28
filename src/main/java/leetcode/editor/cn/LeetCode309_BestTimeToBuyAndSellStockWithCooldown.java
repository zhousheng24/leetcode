//You are given an array prices where prices[i] is the price of a given stock 
//on the iᵗʰ day. 
//
// Find the maximum profit you can achieve. You may complete as many 
//transactions as you like (i.e., buy one and sell one share of the stock multiple times) 
//with the following restrictions: 
//
// 
// After you sell your stock, you cannot buy stock on the next day (i.e., 
//cooldown one day). 
// 
//
// Note: You may not engage in multiple transactions simultaneously (i.e., you 
//must sell the stock before you buy again). 
//
// 
// Example 1: 
//
// 
//Input: prices = [1,2,3,0,2]
//Output: 3
//Explanation: transactions = [buy, sell, cooldown, buy, sell]
// 
//
// Example 2: 
//
// 
//Input: prices = [1]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 1 <= prices.length <= 5000 
// 0 <= prices[i] <= 1000 
// 
//
// Related Topics 数组 动态规划 👍 1573 👎 0


package leetcode.editor.cn;

// [LeetCode][309]best-time-to-buy-and-sell-stock-with-cooldown
public class LeetCode309_BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        Solution solution = new LeetCode309_BestTimeToBuyAndSellStockWithCooldown().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            // 1 <= prices.length <= 5000
            // 0 <= prices[i] <= 1000
            int n = prices.length;
            // dp[i]表示第i天结束后累计最大收益
            // dp[i][0]表示第i天结束后，持有股票累计最大收益
            // dp[i][1]表示第i天结束后，今天刚卖出股票累计最大收益
            // dp[i][2]表示第i天结束后，未持有股票并且今天没有任何操作累计最大收益
            int[][] dp = new int[n][3];
            dp[0][0] = -prices[0];
            dp[0][1] = 0;
            dp[0][2] = 0;
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
                dp[i][1] = dp[i - 1][0] + prices[i];
                dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
            }
            return Math.max(dp[n - 1][1], dp[n - 1][2]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}