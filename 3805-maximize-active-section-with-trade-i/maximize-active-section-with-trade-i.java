class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int totalOnes = 0;  // Total count of '1's in the string
        int currentIndex = 0;
        int previousZeroSegmentLength = Integer.MIN_VALUE;  // Length of previous segment of '0's
        int maxZeroSegmentSum = 0;  // Maximum sum of two adjacent zero segments

        // Process the string by segments of consecutive identical characters
        while (currentIndex < n) {
            // Find the end of current segment with same character
            int segmentEnd = currentIndex + 1;
            while (segmentEnd < n && s.charAt(segmentEnd) == s.charAt(currentIndex)) {
                segmentEnd++;
            }
          
            // Calculate current segment length
            int currentSegmentLength = segmentEnd - currentIndex;
          
            if (s.charAt(currentIndex) == '1') {
                // If current segment contains '1's, add to total count
                totalOnes += currentSegmentLength;
            } else {
                // If current segment contains '0's, update max possible trade value
                // We can potentially trade this segment with a '1' segment
                // Track the maximum sum of two adjacent '0' segments (separated by '1's)
                maxZeroSegmentSum = Math.max(maxZeroSegmentSum, 
                                            previousZeroSegmentLength + currentSegmentLength);
                previousZeroSegmentLength = currentSegmentLength;
            }
          
            // Move to next segment
            currentIndex = segmentEnd;
        }

        // Final answer: original '1's count plus the best possible trade
        totalOnes += maxZeroSegmentSum;
        return totalOnes;
    }
}