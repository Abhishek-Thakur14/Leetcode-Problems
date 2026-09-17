class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        // Map to store prefix sum -> index mapping
        // Key: prefix sum, Value: index (1-based)
        Map<Integer, Integer> prefixSumToIndex = new HashMap<>();
        prefixSumToIndex.put(0, 0); // Initialize with sum 0 at index 0

        int n = arr.length;

        // dp[i] stores the minimum length of a subarray with sum = target
        // ending at or before index i (1-based indexing)
        int[] minLengthUpToIndex = new int[n + 1];

        // Large value representing infinity for comparison
        final int INFINITY = 1 << 30;
        minLengthUpToIndex[0] = INFINITY; // No valid subarray before index 0

        int prefixSum = 0;
        int minTotalLength = INFINITY;

        // Iterate through the array (using 1-based indexing)
        for (int i = 1; i <= n; ++i) {
            int currentValue = arr[i - 1]; // Convert to 0-based array index
            prefixSum += currentValue;

            // Initially, inherit the minimum length from previous position
            minLengthUpToIndex[i] = minLengthUpToIndex[i - 1];

            // Check if we can form a subarray ending at current position with sum = target
            // We need prefixSum[j] = prefixSum[i] - target, where j < i
            if (prefixSumToIndex.containsKey(prefixSum - target)) {
                int startIndex = prefixSumToIndex.get(prefixSum - target);
                int currentSubarrayLength = i - startIndex;

                // Update the minimum length of subarray ending at or before index i
                minLengthUpToIndex[i] = Math.min(minLengthUpToIndex[i], currentSubarrayLength);

                // Try to combine with the best subarray before startIndex
                // to form two non-overlapping subarrays
                minTotalLength = Math.min(minTotalLength, minLengthUpToIndex[startIndex] + currentSubarrayLength);
            }

            // Store current prefix sum and its index
            prefixSumToIndex.put(prefixSum, i);
        }

        // If no valid pair of subarrays found, return -1
        return minTotalLength > n ? -1 : minTotalLength;
    }
}
