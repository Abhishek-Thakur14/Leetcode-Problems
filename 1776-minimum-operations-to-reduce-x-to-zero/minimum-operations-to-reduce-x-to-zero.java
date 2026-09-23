class Solution {
    public int minOperations(int[] nums, int x) {
        // Calculate the target sum for the middle subarray
        // We need to find the longest subarray with sum = totalSum - x
        int targetSum = -x;
        for (int num : nums) {
            targetSum += num;
        }

        // Map to store prefix sum and its first occurrence index
        Map<Integer, Integer> prefixSumToIndex = new HashMap<>();
        prefixSumToIndex.put(0, -1); // Base case: empty prefix has sum 0 at index -1

        // Variables to track the maximum length of valid subarray
        int maxLength = -1;
        int currentPrefixSum = 0;
        int n = nums.length;

        // Iterate through the array to find the longest subarray with target sum
        for (int i = 0; i < n; i++) {
            currentPrefixSum += nums[i];

            // Store the first occurrence of this prefix sum
            prefixSumToIndex.putIfAbsent(currentPrefixSum, i);

            // Check if we can form a subarray with the target sum
            // currentPrefixSum - (currentPrefixSum - targetSum) = targetSum
            if (prefixSumToIndex.containsKey(currentPrefixSum - targetSum)) {
                int subarrayLength = i - prefixSumToIndex.get(currentPrefixSum - targetSum);
                maxLength = Math.max(maxLength, subarrayLength);
            }
        }

        // If no valid subarray found, return -1
        // Otherwise, return the minimum operations (elements to remove from both ends)
        return maxLength == -1 ? -1 : n - maxLength;
    }
}
