class Solution {
    private int n;                    // Total number of stones
    private int[] prefixSum;          // Prefix sum array for quick range sum calculation
    private int[] stoneValues;        // Original stone values array
    private Integer[][] memo;          // Memoization table for dynamic programming
  
    public int stoneGameV(int[] stoneValue) {
        // Initialize variables
        n = stoneValue.length;
        prefixSum = new int[n + 1];
        stoneValues = stoneValue;
        memo = new Integer[n][n];
      
        // Build prefix sum array for O(1) range sum queries
        // prefixSum[i] represents sum of elements from index 0 to i-1
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stoneValues[i - 1];
        }
      
        // Start the recursive solution with memoization
        return dfs(0, n - 1);
    }
  
    private int dfs(int left, int right) {
        // Base case: if range is invalid or contains only one element
        if (left >= right) {
            return 0;
        }
      
        // Check if result is already computed
        if (memo[left][right] != null) {
            return memo[left][right];
        }
      
        int maxScore = 0;
        int leftSum = 0;  // Sum of left partition
        int rightSum = prefixSum[right + 1] - prefixSum[left];  // Sum of right partition
      
        // Try all possible split points
        for (int splitPoint = left; splitPoint < right; splitPoint++) {
            // Update partition sums
            leftSum += stoneValues[splitPoint];
            rightSum -= stoneValues[splitPoint];
          
            if (leftSum < rightSum) {
                // Left partition has smaller sum, Alice chooses left
                // Pruning: if current answer is already greater than maximum possible score
                if (maxScore > leftSum * 2) {
                    continue;
                }
                maxScore = Math.max(maxScore, leftSum + dfs(left, splitPoint));
            } else if (leftSum > rightSum) {
                // Right partition has smaller sum, Alice chooses right
                // Pruning: if current answer is already greater than maximum possible score
                if (maxScore > rightSum * 2) {
                    break;
                }
                maxScore = Math.max(maxScore, rightSum + dfs(splitPoint + 1, right));
            } else {
                // Both partitions have equal sum, Alice can choose either
                maxScore = Math.max(maxScore, 
                    Math.max(leftSum + dfs(left, splitPoint), 
                            rightSum + dfs(splitPoint + 1, right)));
            }
        }
      
        // Store result in memo table and return
        memo[left][right] = maxScore;
        return maxScore;
    }
}
