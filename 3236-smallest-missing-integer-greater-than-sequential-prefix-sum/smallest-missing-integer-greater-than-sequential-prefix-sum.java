class Solution {
    public int missingInteger(int[] nums) {
        // Calculate the sum of the longest consecutive sequence starting from index 0
        int sum = nums[0];
      
        // Add consecutive elements to the sum (elements that are exactly 1 more than the previous)
        for (int i = 1; i < nums.length && nums[i] == nums[i - 1] + 1; i++) {
            sum += nums[i];
        }
      
        // Create a boolean array to mark which numbers exist in the input array
        // Size 51 assumes the constraint that array elements are <= 50
        boolean[] isPresent = new boolean[51];
      
        // Mark all numbers that appear in the input array
        for (int num : nums) {
            isPresent[num] = true;
        }
      
        // Find the smallest integer >= sum that is not present in the array
        for (int candidate = sum; ; candidate++) {
            // Return the candidate if it's outside the bounds or not present in the array
            if (candidate >= isPresent.length || !isPresent[candidate]) {
                return candidate;
            }
        }
    }
}