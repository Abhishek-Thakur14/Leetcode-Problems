class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // suffixMin[i] holds the minimum value among nums[i..n-1].
        // This lets us query the smallest element to the right of (and including) index i in O(1).
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        // Build the suffix minimum array from right to left.
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        // prefixMax tracks the maximum value among nums[0..i] as we scan left to right.
        int prefixMax = 0;
        for (int i = 0; i < n; i++) {
            // Update the running maximum of the prefix ending at index i.
            prefixMax = Math.max(prefixMax, nums[i]);

            // If the spread between the prefix maximum and the suffix minimum
            // (from i onward) is within the allowed threshold k, index i is stable.
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }

        // No index satisfies the stability condition.
        return -1;
    }
}
