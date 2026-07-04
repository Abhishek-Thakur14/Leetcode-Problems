class Solution {
    // Adjacency list representation of the graph
    private List<int[]>[] adjacencyList;
    // Visited array to track which nodes have been explored
    private boolean[] visited;
    // Minimum edge weight found in the connected component
    private int minimumEdgeWeight = 1 << 30; // Initialize to a large value (2^30)

    public int minScore(int n, int[][] roads) {
        // Initialize the graph with n nodes (0-indexed)
        adjacencyList = new List[n];
        visited = new boolean[n];
      
        // Create an empty list for each node
        Arrays.setAll(adjacencyList, index -> new ArrayList<>());
      
        // Build the undirected graph from the roads
        for (int[] road : roads) {
            // Convert to 0-indexed (roads are 1-indexed)
            int nodeA = road[0] - 1;
            int nodeB = road[1] - 1;
            int edgeWeight = road[2];
          
            // Add edge in both directions (undirected graph)
            adjacencyList[nodeA].add(new int[] {nodeB, edgeWeight});
            adjacencyList[nodeB].add(new int[] {nodeA, edgeWeight});
        }
      
        // Start DFS from node 0 (which is node 1 in the problem)
        depthFirstSearch(0);
      
        return minimumEdgeWeight;
    }

    private void depthFirstSearch(int currentNode) {
        // Explore all edges from the current node
        for (int[] edge : adjacencyList[currentNode]) {
            int neighborNode = edge[0];
            int edgeWeight = edge[1];
          
            // Update the minimum edge weight found so far
            minimumEdgeWeight = Math.min(minimumEdgeWeight, edgeWeight);
          
            // If the neighbor hasn't been visited, mark it and continue DFS
            if (!visited[neighborNode]) {
                visited[neighborNode] = true;
                depthFirstSearch(neighborNode);
            }
        }
    }
}