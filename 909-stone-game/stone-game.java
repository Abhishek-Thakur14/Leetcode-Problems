class Solution {
    // Array to store the stone piles
    private int[] piles;
    // Memoization table for dynamic programming
    // dp[i][j] represents the maximum score difference the current player can achieve
    // when choosing from piles[i] to piles[j]
    private int[][] dp;

    /**
     * Determines if the first player can win the stone game.
     * 
     * @param piles Array of stone piles where each element represents the number of stones
     * @return true if the first player can win, false otherwise
     */
    public boolean stoneGame(int[] piles) {
        this.piles = piles;
        int n = piles.length;
        dp = new int[n][n];
      
        // First player wins if their score difference is positive
        return dfs(0, n - 1) > 0;
    }

    /**
     * Recursive function with memoization to calculate the maximum score difference
     * the current player can achieve.
     * 
     * @param left Left boundary index of remaining piles
     * @param right Right boundary index of remaining piles
     * @return Maximum score difference the current player can achieve
     */
    private int dfs(int left, int right) {
        // Base case: no piles left to choose from
        if (left > right) {
            return 0;
        }
      
        // Return memoized result if already calculated
        if (dp[left][right] != 0) {
            return dp[left][right];
        }
      
        // Current player chooses the pile that maximizes their score difference
        // If choosing left pile: gain piles[left] and opponent plays optimally on remaining piles
        // If choosing right pile: gain piles[right] and opponent plays optimally on remaining piles
        int chooseLeft = piles[left] - dfs(left + 1, right);
        int chooseRight = piles[right] - dfs(left, right - 1);
      
        // Store and return the maximum score difference
        dp[left][right] = Math.max(chooseLeft, chooseRight);
        return dp[left][right];
    }
}