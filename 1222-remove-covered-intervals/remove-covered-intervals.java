class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort intervals by start point ascending, if start points are equal then by end point descending
        // This ensures that for intervals with same start, the larger interval comes first
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];  // Sort by end point descending
            }
            return a[0] - b[0];  // Sort by start point ascending
        });
      
        // Count of non-covered intervals
        int nonCoveredCount = 0;
      
        // Track the rightmost end point of previous non-covered interval
        int previousEnd = Integer.MIN_VALUE;
      
        // Iterate through sorted intervals
        for (int[] currentInterval : intervals) {
            int currentEnd = currentInterval[1];
          
            // If current interval's end extends beyond previous end,
            // it's not covered by any previous interval
            if (currentEnd > previousEnd) {
                nonCoveredCount++;
                previousEnd = currentEnd;
            }
            // If currentEnd <= previousEnd, the current interval is covered
            // by a previous interval (due to our sorting strategy)
        }
      
        return nonCoveredCount;
    }
}