class Solution {
    // Store the input array globally for DFS access
    private int[] nums;
    // Memoization table to store calculated results for subproblems
    // dp[i][j] represents the maximum score difference the current player can achieve
    // when choosing from nums[i] to nums[j]
    private int[][] dp;

    /**
     * Determines if Player 1 can win or tie the game
     * @param nums Array of scores to choose from
     * @return true if Player 1 can win or tie, false otherwise
     */
    public boolean predictTheWinner(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        dp = new int[n][n];
      
        // Player 1 wins or ties if their score difference is non-negative
        return dfs(0, n - 1) >= 0;
    }

    /**
     * Recursively calculates the maximum score difference the current player can achieve
     * @param left Left boundary index of the current subarray
     * @param right Right boundary index of the current subarray
     * @return Maximum score difference (current player's score - opponent's score)
     */
    private int dfs(int left, int right) {
        // Base case: no elements left to choose
        if (left > right) {
            return 0;
        }
      
        // Return memoized result if already calculated
        if (dp[left][right] != 0) {
            return dp[left][right];
        }
      
        // Current player chooses between:
        // 1. Taking nums[left]: gain nums[left], opponent plays optimally on [left+1, right]
        // 2. Taking nums[right]: gain nums[right], opponent plays optimally on [left, right-1]
        // Since opponent also plays optimally, we subtract their best score
        int chooseLeft = nums[left] - dfs(left + 1, right);
        int chooseRight = nums[right] - dfs(left, right - 1);
      
        // Store and return the maximum score difference
        dp[left][right] = Math.max(chooseLeft, chooseRight);
        return dp[left][right];
    }
}