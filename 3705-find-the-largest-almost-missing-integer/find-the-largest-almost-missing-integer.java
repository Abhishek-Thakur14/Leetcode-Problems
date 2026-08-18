class Solution {
    private int[] numbers;

    /**
     * Finds the largest integer based on specific conditions related to parameter k
     * @param nums The input array of integers
     * @param k The parameter that determines the search strategy
     * @return The largest integer that meets the criteria
     */
    public int largestInteger(int[] nums, int k) {
        this.numbers = nums;
      
        // Case 1: k equals 1 - find the largest number that appears exactly once
        if (k == 1) {
            // Count frequency of each number
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int number : nums) {
                frequencyMap.merge(number, 1, Integer::sum);
            }
          
            // Find the maximum number that appears exactly once
            int maxUniqueNumber = -1;
            for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() == 1) {
                    maxUniqueNumber = Math.max(maxUniqueNumber, entry.getKey());
                }
            }
            return maxUniqueNumber;
        }
      
        // Case 2: k equals array length - return the maximum element
        if (k == nums.length) {
            return Arrays.stream(nums).max().getAsInt();
        }
      
        // Case 3: Check if first or last element is unique and return the larger one
        return Math.max(checkIfUnique(0), checkIfUnique(nums.length - 1));
    }

    /**
     * Checks if the element at the given index is unique in the array
     * @param targetIndex The index of the element to check
     * @return The element value if it's unique, -1 otherwise
     */
    private int checkIfUnique(int targetIndex) {
        // Check if the element at targetIndex appears elsewhere in the array
        for (int i = 0; i < numbers.length; i++) {
            if (i != targetIndex && numbers[i] == numbers[targetIndex]) {
                return -1;  // Element is not unique
            }
        }
        return numbers[targetIndex];  // Element is unique
    }
}
