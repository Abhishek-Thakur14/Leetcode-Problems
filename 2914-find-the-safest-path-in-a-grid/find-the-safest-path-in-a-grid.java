class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        // If start or end position has a thief, safeness factor is 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }

        // BFS to calculate minimum distance from each cell to nearest thief
        Deque<int[]> queue = new ArrayDeque<>();
        int[][] distanceToThief = new int[n][n];
        final int INFINITY = 1 << 30;

        // Initialize all distances to infinity
        for (int[] row : distanceToThief) {
            Arrays.fill(row, INFINITY);
        }

        // Find all thieves and add them to queue as starting points
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    distanceToThief[i][j] = 0;
                    queue.offer(new int[] {i, j});
                }
            }
        }

        // Direction vectors for moving up, right, down, left
        int[] directions = {-1, 0, 1, 0, -1};

        // Multi-source BFS to calculate distances
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            // Check all 4 adjacent cells
            for (int k = 0; k < 4; k++) {
                int nextRow = currentRow + directions[k];
                int nextCol = currentCol + directions[k + 1];

                // If valid cell and not yet visited
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n
                    && distanceToThief[nextRow][nextCol] == INFINITY) {
                    distanceToThief[nextRow][nextCol] = distanceToThief[currentRow][currentCol] + 1;
                    queue.offer(new int[] {nextRow, nextCol});
                }
            }
        }

        // Create list of cells sorted by their distance to thieves (descending)
        List<int[]> cellsByDistance = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cellsByDistance.add(new int[] {distanceToThief[i][j], i, j});
            }
        }
        cellsByDistance.sort((a, b) -> Integer.compare(b[0], a[0]));

        // Use Union-Find to connect cells with safeness >= current distance
        UnionFind unionFind = new UnionFind(n * n);

        // Process cells from highest to lowest safeness
        for (int[] cell : cellsByDistance) {
            int currentDistance = cell[0];
            int row = cell[1];
            int col = cell[2];

            // Connect with adjacent cells that have safeness >= current distance
            for (int k = 0; k < 4; k++) {
                int adjacentRow = row + directions[k];
                int adjacentCol = col + directions[k + 1];

                if (adjacentRow >= 0 && adjacentRow < n && adjacentCol >= 0 && adjacentCol < n
                    && distanceToThief[adjacentRow][adjacentCol] >= currentDistance) {
                    // Convert 2D coordinates to 1D index for Union-Find
                    unionFind.union(row * n + col, adjacentRow * n + adjacentCol);
                }
            }

            // Check if start (0,0) and end (n-1,n-1) are connected
            if (unionFind.find(0) == unionFind.find(n * n - 1)) {
                return currentDistance;
            }
        }

        return 0;
    }
}

class UnionFind {
    private int[] parent;
    private int componentCount;

    public UnionFind(int size) {
        parent = new int[size];
        // Initially, each element is its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        this.componentCount = size;
    }

    // Union two elements, returns true if they were in different components
    public boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return false;  // Already in same component
        }

        parent[rootA] = rootB;  // Connect component A to component B
        componentCount--;
        return true;
    }

    // Find root of element with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }
}