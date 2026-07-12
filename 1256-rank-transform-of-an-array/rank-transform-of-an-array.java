class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int arrayLength = arr.length;
      
        // Create a copy of the original array for sorting
        int[] sortedArray = arr.clone();
        Arrays.sort(sortedArray);
      
        // Remove duplicates from sorted array to get unique values
        // uniqueCount will track the number of unique elements
        int uniqueCount = 0;
        for (int i = 0; i < arrayLength; ++i) {
            // Keep first occurrence or when current element differs from previous
            if (i == 0 || sortedArray[i] != sortedArray[i - 1]) {
                sortedArray[uniqueCount++] = sortedArray[i];
            }
        }
      
        // Create result array to store ranks
        int[] result = new int[arrayLength];
      
        // For each element in original array, find its rank
        for (int i = 0; i < arrayLength; ++i) {
            // Binary search returns index of element in unique sorted array
            // Add 1 because ranks start from 1, not 0
            result[i] = Arrays.binarySearch(sortedArray, 0, uniqueCount, arr[i]) + 1;
        }
      
        return result;
    }
}