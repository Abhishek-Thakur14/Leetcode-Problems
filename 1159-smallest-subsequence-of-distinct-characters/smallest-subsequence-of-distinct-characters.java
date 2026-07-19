class Solution {
    public String smallestSubsequence(String text) {
        // Count frequency of each character in the string
        int[] charFrequency = new int[26];
        for (char c : text.toCharArray()) {
            charFrequency[c - 'a']++;
        }
      
        // Track which characters are already in the result stack
        boolean[] isInStack = new boolean[26];
      
        // Stack to build the result (using array for efficiency)
        char[] stack = new char[text.length()];
        int stackTop = -1;
      
        // Process each character in the string
        for (char currentChar : text.toCharArray()) {
            // Decrease remaining count for current character
            charFrequency[currentChar - 'a']--;
          
            // Skip if character is already in the result stack
            if (!isInStack[currentChar - 'a']) {
                // Remove characters from stack that are:
                // 1. Greater than current character (not lexicographically smallest)
                // 2. Will appear again later (have remaining count > 0)
                while (stackTop >= 0 && 
                       currentChar < stack[stackTop] && 
                       charFrequency[stack[stackTop] - 'a'] > 0) {
                    // Mark the removed character as not in stack
                    isInStack[stack[stackTop] - 'a'] = false;
                    stackTop--;
                }
              
                // Add current character to stack
                stack[++stackTop] = currentChar;
                // Mark current character as in stack
                isInStack[currentChar - 'a'] = true;
            }
        }
      
        // Convert stack to string and return
        return String.valueOf(stack, 0, stackTop + 1);
    }
}