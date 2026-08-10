class Solution {
    // Memoization array to store computed results for each state
    private Boolean[] memo;

    /**
     * Determines if the current player can win the square game.
     * In this game, players take turns removing square number of stones.
     * The player who removes the last stone wins.
     * 
     * @param n The initial number of stones
     * @return true if the current player can guarantee a win, false otherwise
     */
    public boolean winnerSquareGame(int n) {
        // Initialize memoization array with size n+1 to handle states from 0 to n
        memo = new Boolean[n + 1];
      
        // Start the recursive search from initial state n
        return canWin(n);
    }

    /**
     * Recursively determines if the current player can win from state i.
     * Uses game theory: current player wins if there exists at least one move
     * that leaves the opponent in a losing position.
     * 
     * @param remainingStones The number of stones remaining in current state
     * @return true if current player can win from this state, false otherwise
     */
    private boolean canWin(int remainingStones) {
        // Base case: no stones left means previous player took the last stone and won
        if (remainingStones <= 0) {
            return false;
        }
      
        // Check if we've already computed the result for this state
        if (memo[remainingStones] != null) {
            return memo[remainingStones];
        }
      
        // Try all possible square number moves
        // j * j represents the square number of stones to remove
        for (int j = 1; j * j <= remainingStones; j++) {
            // If any move leads to opponent losing, current player wins
            if (!canWin(remainingStones - j * j)) {
                memo[remainingStones] = true;
                return true;
            }
        }
      
        // If no winning move exists, current player loses
        memo[remainingStones] = false;
        return false;
    }
}