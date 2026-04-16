class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int arrayLength = nums.length;
        int doubledLength = arrayLength * 2;

        // Array to store minimum distance to nearest duplicate for each position
        // Initialize with maximum possible value (doubledLength)
        int[] minDistances = new int[doubledLength];
        Arrays.fill(minDistances, doubledLength);

        // First pass: Calculate distances looking backward (from left)
        // Map to store the most recent position of each element
        Map<Integer, Integer> lastSeenPositions = new HashMap<>();
        for (int i = 0; i < doubledLength; i++) {
            int currentElement = nums[i % arrayLength];

            // If we've seen this element before, calculate distance
            if (lastSeenPositions.containsKey(currentElement)) {
                int distanceToLeft = i - lastSeenPositions.get(currentElement);
                minDistances[i] = Math.min(minDistances[i], distanceToLeft);
            }

            // Update the last seen position for this element
            lastSeenPositions.put(currentElement, i);
        }

        // Second pass: Calculate distances looking forward (from right)
        // Map to store the nearest future position of each element
        Map<Integer, Integer> nextPositions = new HashMap<>();
        for (int i = doubledLength - 1; i >= 0; i--) {
            int currentElement = nums[i % arrayLength];

            // If this element appears again to the right, calculate distance
            if (nextPositions.containsKey(currentElement)) {
                int distanceToRight = nextPositions.get(currentElement) - i;
                minDistances[i] = Math.min(minDistances[i], distanceToRight);
            }

            // Update the next position for this element
            nextPositions.put(currentElement, i);
        }

        // Combine results from doubled array back to original array
        // Take minimum of position i and position i+n to handle circular nature
        for (int i = 0; i < arrayLength; i++) {
            minDistances[i] = Math.min(minDistances[i], minDistances[i + arrayLength]);
        }

        // Process queries and build result list
        List<Integer> results = new ArrayList<>();
        for (int queryIndex : queries) {
            // If minimum distance is >= array length, no duplicate exists
            int distance = minDistances[queryIndex];
            results.add(distance >= arrayLength ? -1 : distance);
        }

        return results;
    }
}