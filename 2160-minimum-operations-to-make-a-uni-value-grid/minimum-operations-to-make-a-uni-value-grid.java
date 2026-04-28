class Solution {
    public int minOperations(int[][] grid, int x) {
        // Get dimensions of the grid
        int rows = grid.length;
        int cols = grid[0].length;
      
        // Flatten the 2D grid into a 1D array for easier processing
        int[] flattenedArray = new int[rows * cols];
      
        // Check if all elements have the same remainder when divided by x
        // This is necessary because we can only add/subtract multiples of x
        int firstRemainder = grid[0][0] % x;
      
        // Iterate through the grid to validate and flatten
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If any element has a different remainder, it's impossible to make all equal
                if (grid[i][j] % x != firstRemainder) {
                    return -1;
                }
                // Store the element in the flattened array
                flattenedArray[i * cols + j] = grid[i][j];
            }
        }
      
        // Sort the array to find the median
        Arrays.sort(flattenedArray);
      
        // Find the median value (optimal target for minimum operations)
        // Using bit shift for division by 2
        int medianValue = flattenedArray[flattenedArray.length >> 1];
      
        // Calculate total operations needed to transform all elements to the median
        int totalOperations = 0;
        for (int value : flattenedArray) {
            // Calculate number of operations (additions/subtractions of x) needed
            totalOperations += Math.abs(value - medianValue) / x;
        }
      
        return totalOperations;
    }
}
