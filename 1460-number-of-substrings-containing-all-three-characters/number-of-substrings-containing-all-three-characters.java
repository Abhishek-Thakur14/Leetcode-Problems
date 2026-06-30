class Solution {
    public int numberOfSubstrings(String s) {
        // Track the most recent position of each character 'a', 'b', 'c'
        // Initialize to -1 to indicate character hasn't been seen yet
        int[] lastPositions = new int[] {-1, -1, -1};
      
        // Count of valid substrings
        int totalCount = 0;
      
        // Iterate through each character in the string
        for (int currentIndex = 0; currentIndex < s.length(); currentIndex++) {
            // Get the current character
            char currentChar = s.charAt(currentIndex);
          
            // Update the last seen position for this character
            // 'a' -> index 0, 'b' -> index 1, 'c' -> index 2
            lastPositions[currentChar - 'a'] = currentIndex;
          
            // Find the leftmost position among all three characters
            // This gives us the starting point where all three characters are present
            int leftmostPosition = Math.min(lastPositions[0], 
                                           Math.min(lastPositions[1], lastPositions[2]));
          
            // Add the number of valid substrings ending at current position
            // All substrings from index 0 to leftmostPosition (inclusive) 
            // that end at currentIndex will contain all three characters
            totalCount += leftmostPosition + 1;
        }
      
        return totalCount;
    }
}