class Solution {
    public int minMoves(String[] classroom, int energy) {
        int rows = classroom.length;
        int cols = classroom[0].length();
      
        // Store the index of each light in the grid
        int[][] lightIndex = new int[rows][cols];
        int startRow = 0, startCol = 0;
        int lightCount = 0;
      
        // Find start position and assign indices to lights
        for (int i = 0; i < rows; i++) {
            String row = classroom[i];
            for (int j = 0; j < cols; j++) {
                char cell = row.charAt(j);
                if (cell == 'S') {
                    startRow = i;
                    startCol = j;
                } else if (cell == 'L') {
                    lightIndex[i][j] = lightCount;
                    lightCount++;
                }
            }
        }
      
        // If no lights to turn off, return 0
        if (lightCount == 0) {
            return 0;
        }
      
        // 4D visited array: [row][col][energy][light_state_mask]
        boolean[][][][] visited = new boolean[rows][cols][energy + 1][1 << lightCount];
      
        // BFS queue storing states: [row, col, current_energy, lights_mask]
        List<int[]> queue = new ArrayList<>();
        int initialMask = (1 << lightCount) - 1; // All lights initially on
        queue.add(new int[] {startRow, startCol, energy, initialMask});
        visited[startRow][startCol][energy][initialMask] = true;
      
        // Direction vectors for moving up, right, down, left
        int[] directions = {-1, 0, 1, 0, -1};
        int steps = 0;
      
        // BFS to find minimum steps
        while (!queue.isEmpty()) {
            List<int[]> currentLevel = queue;
            queue = new ArrayList<>();
          
            for (int[] state : currentLevel) {
                int currentRow = state[0];
                int currentCol = state[1];
                int currentEnergy = state[2];
                int lightsMask = state[3];
              
                // All lights turned off, return steps
                if (lightsMask == 0) {
                    return steps;
                }
              
                // No energy left, skip this state
                if (currentEnergy <= 0) {
                    continue;
                }
              
                // Try all 4 directions
                for (int k = 0; k < 4; k++) {
                    int nextRow = currentRow + directions[k];
                    int nextCol = currentCol + directions[k + 1];
                  
                    // Check if next position is valid and not a wall
                    if (nextRow >= 0 && nextRow < rows && 
                        nextCol >= 0 && nextCol < cols && 
                        classroom[nextRow].charAt(nextCol) != 'X') {
                      
                        // Calculate next energy (recharge at 'R', otherwise decrease by 1)
                        int nextEnergy = classroom[nextRow].charAt(nextCol) == 'R' 
                                        ? energy 
                                        : currentEnergy - 1;
                      
                        // Update lights mask if stepping on a light
                        int nextMask = lightsMask;
                        if (classroom[nextRow].charAt(nextCol) == 'L') {
                            // Turn off the light at this position
                            nextMask &= ~(1 << lightIndex[nextRow][nextCol]);
                        }
                      
                        // Add to queue if this state hasn't been visited
                        if (!visited[nextRow][nextCol][nextEnergy][nextMask]) {
                            visited[nextRow][nextCol][nextEnergy][nextMask] = true;
                            queue.add(new int[] {nextRow, nextCol, nextEnergy, nextMask});
                        }
                    }
                }
            }
            steps++;
        }
      
        // No solution found
        return -1;
    }
}
