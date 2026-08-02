class Solution {
    public int minimumPushes(String word) {
        // Get the length of the input word
        int wordLength = word.length();
      
        // Initialize total pushes counter
        int totalPushes = 0;
      
        // Initialize the push count per character (starts at 1, increments for each layer)
        int pushesPerChar = 1;
      
        // Process complete groups of 8 characters
        // Each group represents one layer of characters on 8 buttons
        int completeGroups = wordLength / 8;
        for (int groupIndex = 0; groupIndex < completeGroups; groupIndex++) {
            // Add pushes for 8 characters in this layer
            // Each character requires 'pushesPerChar' pushes
            totalPushes += pushesPerChar * 8;
          
            // Move to next layer (requires one more push per character)
            pushesPerChar++;
        }
      
        // Process remaining characters (less than 8)
        // These go on the next layer with current push count
        int remainingChars = wordLength % 8;
        totalPushes += pushesPerChar * remainingChars;
      
        return totalPushes;
    }
}