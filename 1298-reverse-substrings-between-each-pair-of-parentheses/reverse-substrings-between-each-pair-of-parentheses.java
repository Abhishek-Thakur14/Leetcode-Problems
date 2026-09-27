class Solution {
    public String reverseParentheses(String s) {
        // Use StringBuilder as a stack to process characters
        StringBuilder stack = new StringBuilder();
      
        // Iterate through each character in the input string
        for (char currentChar : s.toCharArray()) {
            if (currentChar == ')') {
                // When encountering a closing parenthesis, extract and reverse the substring
                StringBuilder reversedSegment = new StringBuilder();
              
                // Pop characters from stack until we find the matching opening parenthesis
                while (stack.charAt(stack.length() - 1) != '(') {
                    // Append character to reversedSegment (this reverses the order)
                    reversedSegment.append(stack.charAt(stack.length() - 1));
                    // Remove the last character from stack
                    stack.deleteCharAt(stack.length() - 1);
                }
              
                // Remove the opening parenthesis '(' from stack
                stack.deleteCharAt(stack.length() - 1);
              
                // Append the reversed segment back to the stack
                stack.append(reversedSegment);
            } else {
                // For any other character (including '('), push it onto the stack
                stack.append(currentChar);
            }
        }
      
        // Convert the final result to string and return
        return stack.toString();
    }
}
 