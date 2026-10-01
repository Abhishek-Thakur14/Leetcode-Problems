class Solution {
    /**
     * Validates if the input string has valid matching brackets.
     * Valid brackets must be closed in the correct order.
     *
     * @param s the input string containing bracket characters
     * @return true if all brackets are properly matched and closed, false otherwise
     */
    public boolean isValid(String s) {
        // Stack to keep track of opening brackets
        Deque<Character> stack = new ArrayDeque<>();

        // Iterate through each character in the string
        for (char currentChar : s.toCharArray()) {
            // If it's an opening bracket, push it onto the stack
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                stack.push(currentChar);
            }
            // If it's a closing bracket
            else {
                // Return false if stack is empty (no matching opening bracket)
                // or if the top opening bracket doesn't match the current closing bracket
                if (stack.isEmpty() || !isMatchingPair(stack.peek(), currentChar)) {
                    return false;
                }

                // Valid matching pair found, remove the opening bracket from stack
                stack.pop();
            }
        }

        // All brackets are matched if stack is empty
        return stack.isEmpty();
    }

    /**
     * Checks if the opening and closing brackets form a matching pair.
     *
     * @param openingBracket the opening bracket character
     * @param closingBracket the closing bracket character
     * @return true if the brackets match, false otherwise
     */
    private boolean isMatchingPair(char openingBracket, char closingBracket) {
        return (openingBracket == '(' && closingBracket == ')') ||
               (openingBracket == '{' && closingBracket == '}') ||
               (openingBracket == '[' && closingBracket == ']');
    }
}
