class Solution {
    public String removeDuplicateLetters(String s) {
        int length = s.length();
      
        // Store the last occurrence index of each character
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < length; i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }
      
        // Stack to build the result string
        Deque<Character> stack = new ArrayDeque<>();
      
        // Bitmask to track which characters are already in the stack
        // If bit i is set, it means character ('a' + i) is in the stack
        int inStackMask = 0;
      
        // Process each character in the string
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
          
            // Skip if current character is already in the stack
            if (((inStackMask >> (currentChar - 'a')) & 1) == 1) {
                continue;
            }
          
            // Pop characters from stack if they are:
            // 1. Lexicographically greater than current character
            // 2. Will appear again later in the string
            while (!stack.isEmpty() && 
                   stack.peek() > currentChar && 
                   lastOccurrence[stack.peek() - 'a'] > i) {
                // Remove the popped character from the bitmask
                inStackMask ^= 1 << (stack.pop() - 'a');
            }
          
            // Add current character to stack
            stack.push(currentChar);
          
            // Mark current character as present in stack using bitmask
            inStackMask |= 1 << (currentChar - 'a');
        }
      
        // Build the result string from stack
        StringBuilder result = new StringBuilder();
        for (char character : stack) {
            result.append(character);
        }
      
        // Stack stores characters in reverse order, so reverse the result
        return result.reverse().toString();
    }
}