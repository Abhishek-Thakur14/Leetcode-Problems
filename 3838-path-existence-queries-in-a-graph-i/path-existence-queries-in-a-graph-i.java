class Solution {
    /**
     * Determines whether paths exist between pairs of nodes based on a maximum difference constraint.
     * Two nodes can be in the same connected component if all consecutive differences in the path
     * between them do not exceed maxDiff.
     *
     * @param n       The number of nodes
     * @param nums    Array of values associated with each node (assumed to be sorted)
     * @param maxDiff Maximum allowed difference between consecutive nodes in a valid path
     * @param queries Array of query pairs [u, v] to check if path exists between nodes u and v
     * @return Array of boolean values indicating whether path exists for each query
     */
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Array to store the component ID for each node
        // Nodes belong to the same component if they can reach each other
        int[] componentIds = new int[n];
        int currentComponentId = 0;

        // Build component IDs by checking consecutive differences
        // When difference exceeds maxDiff, we start a new component
        for (int i = 1; i < n; ++i) {
            // Check if the difference between consecutive elements exceeds the threshold
            if (nums[i] - nums[i - 1] > maxDiff) {
                // Start a new component when gap is too large
                currentComponentId++;
            }
            // Assign current component ID to this node
            componentIds[i] = currentComponentId;
        }

        // Process each query
        int numberOfQueries = queries.length;
        boolean[] results = new boolean[numberOfQueries];

        for (int i = 0; i < numberOfQueries; ++i) {
            int sourceNode = queries[i][0];
            int targetNode = queries[i][1];

            // Path exists if both nodes belong to the same component
            results[i] = componentIds[sourceNode] == componentIds[targetNode];
        }

        return results;
    }
}