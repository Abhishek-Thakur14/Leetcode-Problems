class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
      
        // Initialize two arrays to hold the split elements
        int[] firstArray = new int[n];
        int[] secondArray = new int[n];
      
        // Place first element in firstArray and second element in secondArray
        firstArray[0] = nums[0];
        secondArray[0] = nums[1];
      
        // Track the last index of valid elements in each array
        int lastIndexFirst = 0;
        int lastIndexSecond = 0;
      
        // Process remaining elements starting from index 2
        for (int currentIndex = 2; currentIndex < n; currentIndex++) {
            // Compare the last elements of both arrays
            if (firstArray[lastIndexFirst] > secondArray[lastIndexSecond]) {
                // Add current element to firstArray
                lastIndexFirst++;
                firstArray[lastIndexFirst] = nums[currentIndex];
            } else {
                // Add current element to secondArray (when less than or equal)
                lastIndexSecond++;
                secondArray[lastIndexSecond] = nums[currentIndex];
            }
        }
      
        // Merge secondArray into firstArray after all firstArray elements
        for (int index = 0; index <= lastIndexSecond; index++) {
            lastIndexFirst++;
            firstArray[lastIndexFirst] = secondArray[index];
        }
      
        // Return the merged result
        return firstArray;
    }
}
