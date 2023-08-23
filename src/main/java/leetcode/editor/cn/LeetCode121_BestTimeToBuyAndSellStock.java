//You are given an array prices where prices[i] is the price of a given stock 
//on the iᵗʰ day. 
//
// You want to maximize your profit by choosing a single day to buy one stock 
//and choosing a different day in the future to sell that stock. 
//
// Return the maximum profit you can achieve from this transaction. If you 
//cannot achieve any profit, return 0. 
//
// 
// Example 1: 
//
// 
//Input: prices = [7,1,5,3,6,4]
//Output: 5
//Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 
//6-1 = 5.
//Note that buying on day 2 and selling on day 1 is not allowed because you 
//must buy before you sell.
// 
//
// Example 2: 
//
// 
//Input: prices = [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transactions are done and the max profit = 0.
// 
//
// 
// Constraints: 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁴ 
// 
//
// Related Topics 数组 动态规划 👍 3100 👎 0


package leetcode.editor.cn;

// [LeetCode][121]best-time-to-buy-and-sell-stock
public class LeetCode121_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new LeetCode121_BestTimeToBuyAndSellStock().new Solution();
        // TODO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            // 1 <= prices.length <= 10⁵
            // 0 <= prices[i] <= 10⁴
            int n = prices.length;
            // [0,i]范围内买入并卖出股票的最大收益
            int maxProfit = 0;
            // 单调递减栈
            int minPrice = prices[0];
            for (int i = 1; i < n; i++) {
                minPrice = Math.min(minPrice, prices[i]);
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
            return maxProfit;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}