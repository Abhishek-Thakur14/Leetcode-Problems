class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        // Get grid dimensions
        int rows = grid.size();
        int cols = grid.get(0).size();
      
        // Initialize distance array to track minimum health cost to reach each cell
        int[][] minCost = new int[rows][cols];
        for (int[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
      
        // Set starting position cost (health lost at starting cell)
        minCost[0][0] = grid.get(0).get(0);
      
        // Initialize BFS queue with starting position
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});
      
        // Direction vectors for moving up, right, down, left
        final int[] directions = {-1, 0, 1, 0, -1};
      
        // BFS to find minimum cost path
        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int currentRow = currentPosition[0];
            int currentCol = currentPosition[1];
          
            // Explore all 4 adjacent cells
            for (int i = 0; i < 4; i++) {
                int nextRow = currentRow + directions[i];
                int nextCol = currentCol + directions[i + 1];
              
                // Check if next position is valid and if we found a better path
                if (nextRow >= 0 && nextRow < rows && 
                    nextCol >= 0 && nextCol < cols &&
                    minCost[nextRow][nextCol] > minCost[currentRow][currentCol] + grid.get(nextRow).get(nextCol)) {
                  
                    // Update minimum cost for the next cell
                    minCost[nextRow][nextCol] = minCost[currentRow][currentCol] + grid.get(nextRow).get(nextCol);
                  
                    // Add next position to queue for further exploration
                    queue.offer(new int[] {nextRow, nextCol});
                }
            }
        }
      
        // Check if we can reach destination with health remaining
        return minCost[rows - 1][cols - 1] < health;
    }
}