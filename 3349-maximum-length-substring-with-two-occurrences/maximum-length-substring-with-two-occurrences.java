class Solution {
    public int maximumLengthSubstring(String s) {
        // Array to track frequency of each character (a-z)
        int[] charFrequency = new int[26];
        int maxLength = 0;
      
        // Use sliding window technique with two pointers
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            // Get the index of current character (0-25 for a-z)
            int currentCharIndex = s.charAt(right) - 'a';
          
            // Increment frequency of current character
            charFrequency[currentCharIndex]++;
          
            // If any character appears more than 2 times, shrink window from left
            while (charFrequency[currentCharIndex] > 2) {
                int leftCharIndex = s.charAt(left) - 'a';
                charFrequency[leftCharIndex]--;
                left++;
            }
          
            // Update maximum length found so far
            // Window size is (right - left + 1)
            maxLength = Math.max(maxLength, right - left + 1);
        }
      
        return maxLength;
    }
}