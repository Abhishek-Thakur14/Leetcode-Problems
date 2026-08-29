class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
      
        // Create an array of indices to track original positions
        Integer[] indices = new Integer[n];
        Arrays.setAll(indices, i -> i);
      
        // Sort indices based on the values in nums array (ascending order)
        Arrays.sort(indices, (i, j) -> nums[i] - nums[j]);
      
        // Initialize result array
        int[] result = new int[n];
      
        // Process groups of elements that can be swapped with each other
        int i = 0;
        while (i < n) {
            // Find the end of current group where consecutive elements differ by at most 'limit'
            int groupEnd = i + 1;
            while (groupEnd < n && nums[indices[groupEnd]] - nums[indices[groupEnd - 1]] <= limit) {
                groupEnd++;
            }
          
            // Extract indices of current group
            Integer[] groupIndices = Arrays.copyOfRange(indices, i, groupEnd);
          
            // Sort group indices by their original position (ascending order)
            Arrays.sort(groupIndices, (x, y) -> x - y);
          
            // Assign the sorted values to their lexicographically smallest positions
            // Elements in the group get the smallest available values in order
            for (int k = i; k < groupEnd; k++) {
                result[groupIndices[k - i]] = nums[indices[k]];
            }
          
            // Move to the next group
            i = groupEnd;
        }
      
        return result;
    }
}
