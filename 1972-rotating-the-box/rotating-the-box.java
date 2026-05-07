class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;
      
        // Create result matrix with dimensions swapped (rotated 90 degrees clockwise)
        char[][] result = new char[cols][rows];
      
        // Step 1: Rotate the box 90 degrees clockwise
        // Original position [i][j] maps to [j][rows - 1 - i] after rotation
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = box[i][j];
            }
        }
      
        // Step 2: Apply gravity to make stones fall down in each column
        for (int col = 0; col < rows; col++) {
            // Queue to track available empty positions where stones can fall
            Deque<Integer> emptyPositions = new ArrayDeque<>();
          
            // Process from bottom to top of each column
            for (int row = cols - 1; row >= 0; row--) {
                if (result[row][col] == '*') {
                    // Obstacle found - clear all empty positions above it
                    emptyPositions.clear();
                } else if (result[row][col] == '.') {
                    // Empty space found - add to available positions
                    emptyPositions.offer(row);
                } else if (result[row][col] == '#' && !emptyPositions.isEmpty()) {
                    // Stone found with empty space below - make it fall
                    int lowestEmptyPosition = emptyPositions.pollFirst();
                    result[lowestEmptyPosition][col] = '#';
                    result[row][col] = '.';
                    // Current position becomes empty after stone falls
                    emptyPositions.offer(row);
                }
            }
        }
      
        return result;
    }
}