class Solution {
    // Memoization array to store computed results for dynamic programming
    private Integer[] memo;
    // Prefix sum array to store cumulative sums of stones
    private int[] prefixSum;
    // Total number of stones
    private int n;

    public int stoneGameVIII(int[] stones) {
        // Initialize the number of stones
        n = stones.length;
      
        // Initialize memoization array for dynamic programming
        memo = new Integer[n];
      
        // Convert stones array to prefix sum array
        // After this, stones[i] represents sum of all stones from index 0 to i
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
      
        // Store the prefix sum array for use in recursion
        prefixSum = stones;
      
        // Start the game from index 1 (Alice's first move must take at least 2 stones)
        return dfs(1);
    }

    /**
     * Dynamic programming function to find the maximum score difference
     * @param currentIndex - the current index where a player can make a move
     * @return the maximum score difference the current player can achieve
     */
    private int dfs(int currentIndex) {
        // Base case: if we're at the last stone or beyond, 
        // the player must take all remaining stones
        if (currentIndex >= n - 1) {
            return prefixSum[currentIndex];
        }
      
        // Check if we've already computed this state
        if (memo[currentIndex] == null) {
            // The current player has two choices:
            // 1. Skip this position and let the game continue from the next index
            // 2. Take all stones from 0 to currentIndex and give turn to opponent
          
            // Choice 1: Skip current position
            int skipCurrent = dfs(currentIndex + 1);
          
            // Choice 2: Take stones up to current index
            // Score gained is prefixSum[currentIndex] minus opponent's optimal score
            int takeCurrent = prefixSum[currentIndex] - dfs(currentIndex + 1);
          
            // Store the maximum of both choices
            memo[currentIndex] = Math.max(skipCurrent, takeCurrent);
        }
      
        return memo[currentIndex];
    }
}
