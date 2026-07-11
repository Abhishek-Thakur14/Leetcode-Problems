class Solution {
    // Adjacency list representation of the graph
    private List<Integer>[] adjacencyList;
    // Visited array to track which nodes have been processed
    private boolean[] visited;

    public int countCompleteComponents(int n, int[][] edges) {
        // Initialize the adjacency list and visited array
        adjacencyList = new List[n];
        visited = new boolean[n];
      
        // Create an empty list for each node
        Arrays.setAll(adjacencyList, index -> new ArrayList<>());
      
        // Build the undirected graph by adding edges in both directions
        for (int[] edge : edges) {
            int nodeA = edge[0];
            int nodeB = edge[1];
            adjacencyList[nodeA].add(nodeB);
            adjacencyList[nodeB].add(nodeA);
        }
      
        int completeComponentCount = 0;
      
        // Iterate through all nodes to find connected components
        for (int node = 0; node < n; ++node) {
            if (!visited[node]) {
                // Perform DFS to get component statistics
                int[] componentStats = dfs(node);
                int nodeCount = componentStats[0];
                int edgeCount = componentStats[1];
              
                // Check if this component is complete
                // In a complete graph with n nodes, there are n*(n-1) directed edges
                // (or n*(n-1)/2 undirected edges, but we count each edge twice)
                if (nodeCount * (nodeCount - 1) == edgeCount) {
                    ++completeComponentCount;
                }
            }
        }
      
        return completeComponentCount;
    }

    /**
     * Performs DFS to explore a connected component
     * @param currentNode The starting node for DFS
     * @return An array where [0] is the number of nodes and [1] is the total degree sum
     */
    private int[] dfs(int currentNode) {
        // Mark the current node as visited
        visited[currentNode] = true;
      
        // Initialize counters: nodeCount starts at 1 (current node)
        // degreeSum starts with the degree of current node
        int nodeCount = 1;
        int degreeSum = adjacencyList[currentNode].size();
      
        // Explore all neighbors
        for (int neighbor : adjacencyList[currentNode]) {
            if (!visited[neighbor]) {
                // Recursively explore unvisited neighbors
                int[] neighborStats = dfs(neighbor);
                nodeCount += neighborStats[0];
                degreeSum += neighborStats[1];
            }
        }
      
        // Return the accumulated statistics for this component
        return new int[] {nodeCount, degreeSum};
    }
}