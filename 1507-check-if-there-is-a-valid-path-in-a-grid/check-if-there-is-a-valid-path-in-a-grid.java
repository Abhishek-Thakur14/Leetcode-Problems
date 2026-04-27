class Solution {
    // Union-Find parent array to track connected components
    private int[] parent;
    // The input grid representing street types
    private int[][] grid;
    // Number of rows in the grid
    private int rows;
    // Number of columns in the grid
    private int cols;

    /**
     * Checks if there is a valid path from top-left to bottom-right corner.
     * Each cell contains a street type (1-6) with different connection patterns:
     * 1: horizontal street connecting left and right
     * 2: vertical street connecting up and down
     * 3: street connecting left and down
     * 4: street connecting right and down
     * 5: street connecting left and up
     * 6: street connecting right and up
     * 
     * @param grid 2D array where each cell contains a street type (1-6)
     * @return true if there's a valid path from (0,0) to (m-1,n-1)
     */
    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
      
        // Initialize Union-Find structure
        // Each cell is initially its own parent
        parent = new int[rows * cols];
        for (int i = 0; i < parent.length; ++i) {
            parent[i] = i;
        }
      
        // Process each cell in the grid
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int streetType = grid[i][j];
              
                // Connect current cell with adjacent cells based on street type
                if (streetType == 1) {
                    // Horizontal street: connects left and right
                    connectLeft(i, j);
                    connectRight(i, j);
                } else if (streetType == 2) {
                    // Vertical street: connects up and down
                    connectUp(i, j);
                    connectDown(i, j);
                } else if (streetType == 3) {
                    // L-shaped street: connects left and down
                    connectLeft(i, j);
                    connectDown(i, j);
                } else if (streetType == 4) {
                    // L-shaped street: connects right and down
                    connectRight(i, j);
                    connectDown(i, j);
                } else if (streetType == 5) {
                    // L-shaped street: connects left and up
                    connectLeft(i, j);
                    connectUp(i, j);
                } else {
                    // streetType == 6
                    // L-shaped street: connects right and up
                    connectRight(i, j);
                    connectUp(i, j);
                }
            }
        }
      
        // Check if start and end cells are in the same connected component
        return find(0) == find(rows * cols - 1);
    }

    /**
     * Find operation with path compression for Union-Find.
     * 
     * @param x the element to find the root of
     * @return the root of the set containing x
     */
    private int find(int x) {
        if (parent[x] != x) {
            // Path compression: make x point directly to root
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * Attempts to connect current cell with the cell to its left.
     * Connection is valid if left cell exists and has a street that connects to the right.
     * 
     * @param i row index of current cell
     * @param j column index of current cell
     */
    private void connectLeft(int i, int j) {
        // Check if left cell exists and has compatible street type
        // Street types 1, 4, 6 have connections to the right
        if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] == 4 || grid[i][j - 1] == 6)) {
            // Union the two cells
            parent[find(i * cols + j)] = find(i * cols + j - 1);
        }
    }

    /**
     * Attempts to connect current cell with the cell to its right.
     * Connection is valid if right cell exists and has a street that connects to the left.
     * 
     * @param i row index of current cell
     * @param j column index of current cell
     */
    private void connectRight(int i, int j) {
        // Check if right cell exists and has compatible street type
        // Street types 1, 3, 5 have connections to the left
        if (j < cols - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] == 3 || grid[i][j + 1] == 5)) {
            // Union the two cells
            parent[find(i * cols + j)] = find(i * cols + j + 1);
        }
    }

    /**
     * Attempts to connect current cell with the cell above it.
     * Connection is valid if upper cell exists and has a street that connects downward.
     * 
     * @param i row index of current cell
     * @param j column index of current cell
     */
    private void connectUp(int i, int j) {
        // Check if upper cell exists and has compatible street type
        // Street types 2, 3, 4 have connections downward
        if (i > 0 && (grid[i - 1][j] == 2 || grid[i - 1][j] == 3 || grid[i - 1][j] == 4)) {
            // Union the two cells
            parent[find(i * cols + j)] = find((i - 1) * cols + j);
        }
    }

    /**
     * Attempts to connect current cell with the cell below it.
     * Connection is valid if lower cell exists and has a street that connects upward.
     * 
     * @param i row index of current cell
     * @param j column index of current cell
     */
    private void connectDown(int i, int j) {
        // Check if lower cell exists and has compatible street type
        // Street types 2, 5, 6 have connections upward
        if (i < rows - 1 && (grid[i + 1][j] == 2 || grid[i + 1][j] == 5 || grid[i + 1][j] == 6)) {
            // Union the two cells
            parent[find(i * cols + j)] = find((i + 1) * cols + j);
        }
    }
}