class Solution {
    public int minimumDeletions(int[] nums) {
        // Find indices of minimum and maximum elements
        int minIndex = 0;
        int maxIndex = 0;
        int arrayLength = nums.length;
      
        // Iterate through array to find min and max element positions
        for (int i = 0; i < arrayLength; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
      
        // Ensure minIndex is always less than or equal to maxIndex for easier calculation
        if (minIndex > maxIndex) {
            int temp = maxIndex;
            maxIndex = minIndex;
            minIndex = temp;
        }
      
        // Calculate minimum deletions using three strategies:
        // Strategy 1: Delete from left side to cover both elements (maxIndex + 1)
        // Strategy 2: Delete from right side to cover both elements (arrayLength - minIndex)
        // Strategy 3: Delete from left to cover minIndex, and from right to cover maxIndex
        //            (minIndex + 1) + (arrayLength - maxIndex)
        return Math.min(Math.min(maxIndex + 1, arrayLength - minIndex), 
                       minIndex + 1 + arrayLength - maxIndex);
    }
}
