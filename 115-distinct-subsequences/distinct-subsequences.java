class Solution {
    /**
     * Count the number of distinct subsequences of s that equal t.
     * Uses dynamic programming to build up the solution.
     * 
     * @param s The source string to find subsequences in
     * @param t The target string to match
     * @return The number of distinct subsequences of s that equal t
     */
    public int numDistinct(String s, String t) {
        int sourceLength = s.length();
        int targetLength = t.length();
      
        // dp[i][j] represents the number of distinct subsequences 
        // of s[0...i-1] that equal t[0...j-1]
        int[][] dp = new int[sourceLength + 1][targetLength + 1];
      
        // Initialize base case: empty target string can be formed 
        // in exactly one way from any source string (by selecting nothing)
        for (int i = 0; i <= sourceLength; i++) {
            dp[i][0] = 1;
        }
      
        // Fill the dp table
        for (int i = 1; i <= sourceLength; i++) {
            for (int j = 1; j <= targetLength; j++) {
                // Case 1: Don't use the current character from source string
                // The count remains the same as without this character
                dp[i][j] = dp[i - 1][j];
              
                // Case 2: If characters match, we can also use the current character
                // Add the count of subsequences formed by matching both characters
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
      
        // Return the final result: number of subsequences of entire s that equal entire t
        return dp[sourceLength][targetLength];
    }
}
