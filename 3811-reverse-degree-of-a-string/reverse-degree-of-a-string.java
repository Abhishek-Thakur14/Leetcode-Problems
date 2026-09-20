class Solution {
    public int reverseDegree(String s) {
        int stringLength = s.length();
        int totalReverseDegree = 0;
      
        // Iterate through each character position (1-based indexing)
        for (int position = 1; position <= stringLength; position++) {
            // Get the character at current position (convert to 0-based index)
            char currentChar = s.charAt(position - 1);
          
            // Calculate reverse alphabetical value (distance from 'z')
            // 'a' = 26, 'b' = 25, ..., 'z' = 1
            int reverseAlphabeticalValue = 26 - (currentChar - 'a');
          
            // Add weighted value (position * reverse value) to total
            totalReverseDegree += position * reverseAlphabeticalValue;
        }
      
        return totalReverseDegree;
    }
}
