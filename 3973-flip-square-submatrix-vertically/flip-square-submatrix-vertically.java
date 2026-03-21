class Solution {
    /**
     * Reverses a k×k submatrix vertically (flips rows top to bottom)
     * starting from position (x, y) in the given grid.
     * 
     * @param grid The 2D integer array to modify
     * @param x The starting row index of the submatrix
     * @param y The starting column index of the submatrix
     * @param k The size of the square submatrix to reverse
     * @return The modified grid with the submatrix reversed
     */
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // Iterate through the first half of the rows in the submatrix
        for (int row = x; row < x + k / 2; row++) {
            // Calculate the corresponding row from the bottom to swap with
            int mirrorRow = x + k - 1 - (row - x);
          
            // Swap all elements in the current row with the mirror row
            for (int col = y; col < y + k; col++) {
                // Perform the swap using a temporary variable
                int temp = grid[row][col];
                grid[row][col] = grid[mirrorRow][col];
                grid[mirrorRow][col] = temp;
            }
        }
      
        return grid;
    }
}