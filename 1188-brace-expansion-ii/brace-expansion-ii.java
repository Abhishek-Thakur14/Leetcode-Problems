class Solution {
    // TreeSet to store unique results in sorted order
    private TreeSet<String> resultSet = new TreeSet<>();

    /**
     * Expands the brace expression and returns all possible combinations.
     * @param expression - the brace expression to expand
     * @return List of all possible strings after expansion, sorted lexicographically
     */
    public List<String> braceExpansionII(String expression) {
        // Start recursive expansion
        expandExpression(expression);
        // Convert TreeSet to ArrayList and return
        return new ArrayList<>(resultSet);
    }

    /**
     * Recursively expands the expression by processing innermost braces first.
     * @param expression - current expression to process
     */
    private void expandExpression(String expression) {
        // Find the first closing brace
        int closingBraceIndex = expression.indexOf('}');
      
        // Base case: no braces left, add the final string to result set
        if (closingBraceIndex == -1) {
            resultSet.add(expression);
            return;
        }
      
        // Find the matching opening brace (the last '{' before the closing brace)
        int openingBraceIndex = expression.lastIndexOf('{', closingBraceIndex);
      
        // Extract parts: prefix before '{', content between braces, suffix after '}'
        String prefix = expression.substring(0, openingBraceIndex);
        String suffix = expression.substring(closingBraceIndex + 1);
        String braceContent = expression.substring(openingBraceIndex + 1, closingBraceIndex);
      
        // Split the content by comma and recursively process each option
        for (String option : braceContent.split(",")) {
            // Construct new expression by replacing {content} with one option
            String newExpression = prefix + option + suffix;
            // Recursively expand the new expression
            expandExpression(newExpression);
        }
    }
}
