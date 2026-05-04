class Solution {
    /**
     * Rotates the matrix 90 degrees clockwise in-place.
     * The rotation is achieved through two steps:
     * 1. Flip the matrix horizontally (reverse rows)
     * 2. Transpose the matrix (swap elements across diagonal)
     * 
     * @param matrix The n x n 2D matrix to be rotated
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
      
        // Step 1: Flip the matrix horizontally (reverse rows)
        // Swap rows from top and bottom moving towards center
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                // Swap element at row i with element at row (n - i - 1)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
      
        // Step 2: Transpose the matrix
        // Swap elements across the main diagonal
        for (int i = 0; i < n; i++) {
            // Only iterate through lower triangle to avoid double swapping
            for (int j = 0; j < i; j++) {
                // Swap element at (i, j) with element at (j, i)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}