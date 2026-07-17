class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Find the maximum value in the array
        int maxValue = Arrays.stream(nums).max().getAsInt();

        // Count frequency of each number
        int[] frequency = new int[maxValue + 1];
        for (int num : nums) {
            frequency[num]++;
        }

        // Calculate the count of pairs with GCD equal to each value
        long[] gcdPairCount = new long[maxValue + 1];

        // Process from largest to smallest to apply inclusion-exclusion principle
        for (int gcd = maxValue; gcd > 0; gcd--) {
            // Count elements that are multiples of current gcd
            int multiplesCount = 0;
            for (int multiple = gcd; multiple <= maxValue; multiple += gcd) {
                multiplesCount += frequency[multiple];
                // Subtract pairs already counted with larger GCDs (inclusion-exclusion)
                gcdPairCount[gcd] -= gcdPairCount[multiple];
            }
            // Add all possible pairs from elements that are multiples of gcd
            // C(multiplesCount, 2) = multiplesCount * (multiplesCount - 1) / 2
            gcdPairCount[gcd] += (long) multiplesCount * (multiplesCount - 1) / 2;
        }

        // Convert to prefix sum array for binary search
        for (int i = 2; i <= maxValue; i++) {
            gcdPairCount[i] += gcdPairCount[i - 1];
        }

        // Process each query using binary search
        int queryCount = queries.length;
        int[] result = new int[queryCount];
        for (int i = 0; i < queryCount; i++) {
            result[i] = search(gcdPairCount, queries[i]);
        }

        return result;
    }

    /**
     * Binary search to find the smallest index where cumulative count > target.
     * Uses standard template: feasible = cumulativeCounts[mid] > target
     * @param cumulativeCounts prefix sum array of GCD pair counts
     * @param target the query value to search for
     * @return the GCD value corresponding to the target-th pair
     */
    private int search(long[] cumulativeCounts, long target) {
        int left = 0;
        int right = cumulativeCounts.length - 1;
        int firstTrueIndex = cumulativeCounts.length;  // Default: not found

        // Binary search for first position where cumulativeCounts[mid] > target
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (cumulativeCounts[mid] > target) {
                firstTrueIndex = mid;
                right = mid - 1;  // Found one, look for earlier
            } else {
                left = mid + 1;
            }
        }

        return firstTrueIndex;
    }
}