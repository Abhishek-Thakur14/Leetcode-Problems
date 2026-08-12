class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        // HashMap to store the frequency of each element in the current window
        Map<Integer, Integer> frequencyMap = new HashMap<>();
      
        // Variable to store the maximum length of valid subarray
        int maxLength = 0;
      
        // Sliding window approach: left pointer starts at 0
        int left = 0;
      
        // Iterate through the array with right pointer
        for (int right = 0; right < nums.length; right++) {
            // Add current element to the window and increment its frequency
            frequencyMap.merge(nums[right], 1, Integer::sum);
          
            // Shrink window from left while current element's frequency exceeds k
            while (frequencyMap.get(nums[right]) > k) {
                // Decrement frequency of element at left pointer
                frequencyMap.merge(nums[left], -1, Integer::sum);
                // Move left pointer to the right
                left++;
            }
          
            // Update maximum length with current valid window size
            maxLength = Math.max(maxLength, right - left + 1);
        }
      
        return maxLength;
    }
}