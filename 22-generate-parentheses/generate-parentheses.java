class Solution {
    // List to store all valid parentheses combinations
    private List<String> result = new ArrayList<>();
    // Number of pairs of parentheses to generate
    private int pairCount;

    /**
     * Generates all combinations of well-formed parentheses.
     * @param n Number of pairs of parentheses
     * @return List of all valid parentheses combinations
     */
    public List<String> generateParenthesis(int n) {
        this.pairCount = n;
        // Start DFS with 0 open and 0 close parentheses
        backtrack(0, 0, "");
        return result;
    }

    /**
     * Recursive helper method to generate valid parentheses using backtracking.
     * @param openCount Number of opening parentheses used so far
     * @param closeCount Number of closing parentheses used so far
     * @param currentString Current parentheses string being built
     */
    private void backtrack(int openCount, int closeCount, String currentString) {
        // Pruning conditions:
        // 1. Cannot use more than n opening or closing parentheses
        // 2. Cannot have more closing than opening parentheses at any point
        if (openCount > pairCount || closeCount > pairCount || openCount < closeCount) {
            return;
        }
      
        // Base case: Found a valid combination when we've used n pairs
        if (openCount == pairCount && closeCount == pairCount) {
            result.add(currentString);
            return;
        }
      
        // Try adding an opening parenthesis
        backtrack(openCount + 1, closeCount, currentString + "(");
      
        // Try adding a closing parenthesis
        backtrack(openCount, closeCount + 1, currentString + ")");
    }
}
