class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // suffixMin[i] holds the minimum value in nums from index i to n - 1
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        // Build the suffix-minimum array by scanning from right to left
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        // prefixMax tracks the maximum value in nums from index 0 up to the current index
        int prefixMax = 0;
        for (int i = 0; i < n; i++) {
            // Update the running prefix maximum to include nums[i]
            prefixMax = Math.max(prefixMax, nums[i]);

            // If the gap between the prefix max and suffix min is within k,
            // this is the first stable index
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }

        // No index satisfies the condition
        return -1;
    }
}