class Solution {
    // Modulo value for preventing integer overflow
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Counts the number of distinct non-empty subsequences in string s.
     * Uses dynamic programming where dp[i] represents the count of distinct
     * subsequences ending with character ('a' + i).
     * 
     * @param s the input string
     * @return the number of distinct subsequences modulo 10^9 + 7
     */
    public int distinctSubseqII(String s) {
        // dp[i] stores count of distinct subsequences ending with character ('a' + i)
        int[] dp = new int[26];
      
        // Process each character in the string
        for (int i = 0; i < s.length(); ++i) {
            // Get the character index (0-25 for 'a'-'z')
            int charIndex = s.charAt(i) - 'a';
          
            // Update count for subsequences ending with current character
            // New count = sum of all previous subsequences + 1 (for single character)
            dp[charIndex] = sum(dp) + 1;
        }
      
        // Return total count of all distinct subsequences
        return sum(dp);
    }

    /**
     * Calculates the sum of all elements in the array with modulo operation.
     * 
     * @param arr the input array
     * @return the sum of all elements modulo 10^9 + 7
     */
    private int sum(int[] arr) {
        int total = 0;
      
        // Add each element to the total with modulo to prevent overflow
        for (int value : arr) {
            total = (total + value) % MOD;
        }
      
        return total;
    }
}
