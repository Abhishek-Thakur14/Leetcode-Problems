class Solution {
    // Grid dimensions
    private int rows, cols;
    // The input grid containing parentheses
    private char[][] grid;
    // 3D visited array: [row][col][balance]
    // Tracks if we've visited position (row, col) with a specific balance value
    private boolean[][][] visited;

    /**
     * Determines if there's a valid path from top-left to bottom-right
     * where parentheses are balanced.
     * 
     * @param grid 2D array containing '(' and ')' characters
     * @return true if a valid balanced path exists, false otherwise
     */
    public boolean hasValidPath(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
      
        // Early termination checks:
        // 1. Path length must be even for balanced parentheses
        // 2. Must start with '(' and end with ')'
        if ((rows + cols - 1) % 2 == 1 || 
            grid[0][0] == ')' || 
            grid[rows - 1][cols - 1] == '(') {
            return false;
        }
      
        this.grid = grid;
        // Initialize visited array with maximum possible balance value
        visited = new boolean[rows][cols][rows + cols];
      
        // Start DFS from top-left corner with initial balance 0
        return dfs(0, 0, 0);
    }

    /**
     * Performs depth-first search to find a valid balanced path.
     * 
     * @param row Current row position
     * @param col Current column position
     * @param balance Current balance of parentheses (incremented for '(', decremented for ')')
     * @return true if a valid path exists from current position, false otherwise
     */
    private boolean dfs(int row, int col, int balance) {
        // Check if this state has been visited before
        if (visited[row][col][balance]) {
            return false;
        }
      
        // Mark current state as visited
        visited[row][col][balance] = true;
      
        // Update balance based on current cell's parenthesis
        balance += grid[row][col] == '(' ? 1 : -1;
      
        // Pruning conditions:
        // 1. Balance cannot be negative (more closing than opening parentheses)
        // 2. Balance cannot exceed remaining cells (impossible to balance)
        if (balance < 0 || balance > rows - row + cols - col) {
            return false;
        }
      
        // Check if we reached the destination
        if (row == rows - 1 && col == cols - 1) {
            // Valid path only if parentheses are balanced
            return balance == 0;
        }
      
        // Direction vectors for moving right and down
        // dirs[0]=1, dirs[1]=0 represents moving down (row+1, col+0)
        // dirs[1]=0, dirs[2]=1 represents moving right (row+0, col+1)
        final int[] dirs = {1, 0, 1};
      
        // Try both possible moves: down and right
        for (int d = 0; d < 2; d++) {
            int nextRow = row + dirs[d];
            int nextCol = col + dirs[d + 1];
          
            // Check if next position is within bounds and recursively explore
            if (nextRow >= 0 && nextRow < rows && 
                nextCol >= 0 && nextCol < cols && 
                dfs(nextRow, nextCol, balance)) {
                return true;
            }
        }
      
        // No valid path found from current position
        return false;
    }
}
