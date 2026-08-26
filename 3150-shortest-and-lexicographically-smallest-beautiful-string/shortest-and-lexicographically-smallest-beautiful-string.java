class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int stringLength = s.length();
        String result = "";
      
        // Iterate through all possible starting positions
        for (int startIndex = 0; startIndex < stringLength; ++startIndex) {
            // Try all possible ending positions (minimum length k to contain k ones)
            for (int endIndex = startIndex + k; endIndex <= stringLength; ++endIndex) {
                // Extract the current substring
                String currentSubstring = s.substring(startIndex, endIndex);
              
                // Count the number of '1's in the current substring
                int onesCount = 0;
                for (char character : currentSubstring.toCharArray()) {
                    onesCount += character - '0';  // Convert '1' to 1, '0' to 0
                }
              
                // Check if this substring has exactly k ones and is better than current result
                if (onesCount == k && 
                    (result.isEmpty() ||                                    // First valid substring found
                     endIndex - startIndex < result.length() ||             // Shorter than current result
                     (endIndex - startIndex == result.length() &&          // Same length but
                      currentSubstring.compareTo(result) < 0))) {           // lexicographically smaller
                    result = currentSubstring;
                }
            }
        }
      
        return result;
    }
}
