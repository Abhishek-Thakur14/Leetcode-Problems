class Solution {
    // Cache for memoization to store computed results
    private int[] stoneValue;
    private Integer[] memo;
    private int n;

    /**
     * Determines the winner of the stone game.
     * Alice and Bob take turns, with Alice going first.
     * Each player can take 1, 2, or 3 stones from the beginning of the array.
     * Both players play optimally to maximize their score difference.
     * 
     * @param stoneValue Array of stone values
     * @return "Alice" if Alice wins, "Bob" if Bob wins, "Tie" if it's a draw
     */
    public String stoneGameIII(int[] stoneValue) {
        // Initialize instance variables
        this.n = stoneValue.length;
        this.memo = new Integer[n];
        this.stoneValue = stoneValue;
      
        // Calculate the maximum score difference Alice can achieve
        int scoreDifference = dfs(0);
      
        // Determine the winner based on score difference
        if (scoreDifference == 0) {
            return "Tie";
        }
        return scoreDifference > 0 ? "Alice" : "Bob";
    }

    /**
     * Recursively calculates the maximum score difference the current player 
     * can achieve starting from index i.
     * 
     * The score difference is: (current player's score) - (opponent's score)
     * 
     * @param i Starting index for the current turn
     * @return Maximum score difference achievable from position i
     */
    private int dfs(int i) {
        // Base case: no stones left to take
        if (i >= n) {
            return 0;
        }
      
        // Return cached result if already computed
        if (memo[i] != null) {
            return memo[i];
        }
      
        // Initialize with minimum possible value
        int maxScoreDifference = Integer.MIN_VALUE;
        int currentSum = 0;
      
        // Try taking 1, 2, or 3 stones
        for (int j = 0; j < 3 && i + j < n; j++) {
            // Add the value of the current stone to our sum
            currentSum += stoneValue[i + j];
          
            // Calculate score difference:
            // Our score (currentSum) minus the best the opponent can do from the next position
            maxScoreDifference = Math.max(maxScoreDifference, currentSum - dfs(i + j + 1));
        }
      
        // Cache and return the result
        memo[i] = maxScoreDifference;
        return maxScoreDifference;
    }
}