class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfSets(int n, int k) {
        // dp[i][j] = number of ways to form j segments using first i points
        // where point i is NOT the end of a segment
        int[][] dpNotEndingAtPoint = new int[n + 1][k + 1];
      
        // dpEndingAtPoint[i][j] = number of ways to form j segments using first i points
        // where point i IS the end of a segment
        int[][] dpEndingAtPoint = new int[n + 1][k + 1];
      
        // Base case: with 1 point and 0 segments, there's exactly 1 way
        dpNotEndingAtPoint[1][0] = 1;
      
        // Fill the DP tables for each point position
        for (int currentPoint = 2; currentPoint <= n; ++currentPoint) {
            for (int segmentCount = 0; segmentCount <= k; ++segmentCount) {
                // Case 1: Current point is not the end of a segment
                // We can transition from previous point being either end or not end
                dpNotEndingAtPoint[currentPoint][segmentCount] = 
                    (dpNotEndingAtPoint[currentPoint - 1][segmentCount] + 
                     dpEndingAtPoint[currentPoint - 1][segmentCount]) % MOD;
              
                // Case 2: Current point is the end of a segment
                // First, extend existing segments that end at previous point
                dpEndingAtPoint[currentPoint][segmentCount] = 
                    dpEndingAtPoint[currentPoint - 1][segmentCount];
              
                // If we have segments to form, we can create a new segment ending here
                if (segmentCount > 0) {
                    // Start a new segment from previous point (not ending)
                    dpEndingAtPoint[currentPoint][segmentCount] = 
                        (dpEndingAtPoint[currentPoint][segmentCount] + 
                         dpNotEndingAtPoint[currentPoint - 1][segmentCount - 1]) % MOD;
                  
                    // Extend a segment that was ending at previous point
                    dpEndingAtPoint[currentPoint][segmentCount] = 
                        (dpEndingAtPoint[currentPoint][segmentCount] + 
                         dpEndingAtPoint[currentPoint - 1][segmentCount - 1]) % MOD;
                }
            }
        }
      
        // Return total ways: sum of both cases at point n with k segments
        return (dpNotEndingAtPoint[n][k] + dpEndingAtPoint[n][k]) % MOD;
    }
}
