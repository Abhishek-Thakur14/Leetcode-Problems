class Solution {
    private boolean[] isSuspicious;
    private boolean[] isVisited;
    private List<Integer>[] undirectedGraph;
    private List<Integer>[] directedGraph;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Initialize arrays and adjacency lists
        isSuspicious = new boolean[n];
        isVisited = new boolean[n];
        undirectedGraph = new List[n];
        directedGraph = new List[n];
      
        // Create empty lists for each node
        Arrays.setAll(undirectedGraph, i -> new ArrayList<>());
        Arrays.setAll(directedGraph, i -> new ArrayList<>());
      
        // Build both directed and undirected graphs from invocations
        for (int[] invocation : invocations) {
            int caller = invocation[0];
            int callee = invocation[1];
          
            // Undirected graph: add edges in both directions
            undirectedGraph[caller].add(callee);
            undirectedGraph[callee].add(caller);
          
            // Directed graph: add edge from caller to callee only
            directedGraph[caller].add(callee);
        }
      
        // Mark all methods reachable from the initial suspicious method k
        markSuspiciousFromSource(k);
      
        // Check if any non-suspicious method can reach suspicious methods
        // If so, mark those suspicious methods as safe (not removable)
        for (int method = 0; method < n; ++method) {
            if (!isSuspicious[method] && !isVisited[method]) {
                markConnectedAsSafe(method);
            }
        }
      
        // Collect all non-suspicious methods as the result
        List<Integer> remainingMethodsList = new ArrayList<>();
        for (int method = 0; method < n; ++method) {
            if (!isSuspicious[method]) {
                remainingMethodsList.add(method);
            }
        }
      
        return remainingMethodsList;
    }

    /**
     * DFS to mark all methods reachable from a suspicious source
     * Uses directed graph to follow call chain
     */
    private void markSuspiciousFromSource(int currentMethod) {
        isSuspicious[currentMethod] = true;
      
        // Traverse all methods called by the current suspicious method
        for (int calledMethod : directedGraph[currentMethod]) {
            if (!isSuspicious[calledMethod]) {
                markSuspiciousFromSource(calledMethod);
            }
        }
    }

    /**
     * DFS to mark methods as safe if they're connected to non-suspicious methods
     * Uses undirected graph to check connectivity
     */
    private void markConnectedAsSafe(int currentMethod) {
        isVisited[currentMethod] = true;
      
        // Check all connected methods (both directions)
        for (int connectedMethod : undirectedGraph[currentMethod]) {
            if (!isVisited[connectedMethod]) {
                // If connected to a non-suspicious method, mark as safe
                isSuspicious[connectedMethod] = false;
                markConnectedAsSafe(connectedMethod);
            }
        }
    }
}