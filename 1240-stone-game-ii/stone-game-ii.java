class Solution {
    // Prefix sum array to calculate sum of piles from index i to j efficiently
    private int[] prefixSum;
    // Memoization table: dp[i][m] = maximum stones the current player can get 
    // starting from index i with M = m
    private Integer[][] dp;
    // Total number of piles
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
      
        // Build prefix sum array for O(1) range sum queries
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + piles[i];
        }
      
        // Initialize memoization table
        dp = new Integer[n][n + 1];
      
        // Start the game from index 0 with M = 1
        return dfs(0, 1);
    }

    /**
     * Calculate the maximum stones the current player can get
     * @param index Current starting index in piles array
     * @param M Current value of M (player can take 1 to 2*M piles)
     * @return Maximum stones the current player can collect from this state
     */
    private int dfs(int index, int M) {
        // Base case: if we can take all remaining piles, take them all
        if (2 * M >= n - index) {
            return prefixSum[n] - prefixSum[index];
        }
      
        // Check if this state has been calculated before
        if (dp[index][M] != null) {
            return dp[index][M];
        }
      
        int maxStones = 0;
      
        // Try taking X piles where X is from 1 to 2*M
        for (int X = 1; X <= 2 * M; X++) {
            // Calculate maximum stones we can get by taking X piles
            // Total remaining stones - opponent's best result = our result
            int currentPlayerStones = prefixSum[n] - prefixSum[index] - 
                                      dfs(index + X, Math.max(M, X));
            maxStones = Math.max(maxStones, currentPlayerStones);
        }
      
        // Store result in memoization table and return
        dp[index][M] = maxStones;
        return maxStones;
    }
}