class Solution {
    /**
     * Finds all query strings that can be transformed into at least one dictionary word
     * with at most 2 character edits (substitutions).
     * 
     * @param queries Array of query strings to check
     * @param dictionary Array of dictionary words to compare against
     * @return List of query strings that match the criteria
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        // Result list to store valid query strings
        List<String> result = new ArrayList<>();
      
        // Get the length of strings (assuming all strings have the same length)
        int stringLength = queries[0].length();
      
        // Iterate through each query string
        for (String queryString : queries) {
            // Check the current query against each dictionary word
            for (String dictionaryWord : dictionary) {
                // Count the number of differing characters
                int differenceCount = 0;
              
                // Compare each character position
                for (int i = 0; i < stringLength; ++i) {
                    if (queryString.charAt(i) != dictionaryWord.charAt(i)) {
                        differenceCount++;
                    }
                }
              
                // If the difference is at most 2, add to result and move to next query
                if (differenceCount < 3) {
                    result.add(queryString);
                    break; // Found a match, no need to check other dictionary words
                }
            }
        }
      
        return result;
    }
}
