class Solution {
    /**
     * Counts the number of pattern strings that are substrings of the given word.
     * 
     * @param patterns Array of pattern strings to search for
     * @param word The word to search within
     * @return The count of patterns that appear as substrings in word
     */
    public int numOfStrings(String[] patterns, String word) {
        // Initialize counter for matching patterns
        int matchCount = 0;
      
        // Iterate through each pattern in the patterns array
        for (String pattern : patterns) {
            // Check if the current pattern is a substring of word
            if (word.contains(pattern)) {
                // Increment counter if pattern is found in word
                matchCount++;
            }
        }
      
        // Return the total count of matching patterns
        return matchCount;
    }
}