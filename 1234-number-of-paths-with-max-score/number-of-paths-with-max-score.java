class Solution {
    // Board reference and size
    private List<String> board;
    private int boardSize;
  
    // Dynamic programming arrays
    private int[][] maxScore;    // maxScore[i][j]: maximum score from (i,j) to end
    private int[][] pathCount;   // pathCount[i][j]: number of paths achieving max score
  
    // Modulo constant for path counting
    private final int MOD = (int) 1e9 + 7;

    public int[] pathsWithMaxScore(List<String> board) {
        // Initialize board and size
        this.boardSize = board.size();
        this.board = board;
      
        // Initialize DP arrays
        maxScore = new int[boardSize][boardSize];
        pathCount = new int[boardSize][boardSize];
      
        // Fill maxScore with -1 to indicate unvisited cells
        for (int[] row : maxScore) {
            Arrays.fill(row, -1);
        }
      
        // Base case: end position 'S' has score 0 and 1 path
        maxScore[boardSize - 1][boardSize - 1] = 0;
        pathCount[boardSize - 1][boardSize - 1] = 1;
      
        // Process cells from bottom-right to top-left
        for (int row = boardSize - 1; row >= 0; row--) {
            for (int col = boardSize - 1; col >= 0; col--) {
                // Try to update current cell from three possible next positions
                updateFromNextCell(row, col, row + 1, col);      // Move down
                updateFromNextCell(row, col, row, col + 1);      // Move right
                updateFromNextCell(row, col, row + 1, col + 1);  // Move diagonal
              
                // Add current cell's value to the score if it's a digit
                if (maxScore[row][col] != -1) {
                    char currentChar = board.get(row).charAt(col);
                    if (currentChar >= '0' && currentChar <= '9') {
                        maxScore[row][col] += (currentChar - '0');
                    }
                }
            }
        }
      
        // Prepare result array
        int[] result = new int[2];
      
        // If start position is reachable, set the max score and path count
        if (maxScore[0][0] != -1) {
            result[0] = maxScore[0][0];
            result[1] = pathCount[0][0];
        }
      
        return result;
    }

    /**
     * Updates the DP values at position (currentRow, currentCol) based on 
     * the values at position (nextRow, nextCol)
     */
    private void updateFromNextCell(int currentRow, int currentCol, 
                                   int nextRow, int nextCol) {
        // Check boundaries and validity
        if (nextRow >= boardSize || nextCol >= boardSize) {
            return;  // Out of bounds
        }
      
        if (maxScore[nextRow][nextCol] == -1) {
            return;  // Next cell is unreachable
        }
      
        char currentChar = board.get(currentRow).charAt(currentCol);
        if (currentChar == 'X' || currentChar == 'S') {
            return;  // Current cell is obstacle or end position
        }
      
        // Update DP values based on next cell
        if (maxScore[nextRow][nextCol] > maxScore[currentRow][currentCol]) {
            // Found a better path - update both score and count
            maxScore[currentRow][currentCol] = maxScore[nextRow][nextCol];
            pathCount[currentRow][currentCol] = pathCount[nextRow][nextCol];
        } else if (maxScore[nextRow][nextCol] == maxScore[currentRow][currentCol]) {
            // Found an equally good path - add to path count
            pathCount[currentRow][currentCol] = 
                (pathCount[currentRow][currentCol] + pathCount[nextRow][nextCol]) % MOD;
        }
    }
}