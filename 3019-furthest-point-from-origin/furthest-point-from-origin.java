class Solution {
    /**
     * Calculates the furthest distance from the origin after executing all moves.
     * 'L' moves left (-1), 'R' moves right (+1), '_' can be either left or right.
     * To maximize distance, all '_' moves should go in the same direction as the majority move.
     * 
     * @param moves String containing 'L', 'R', and '_' characters representing moves
     * @return The maximum possible distance from the origin
     */
    public int furthestDistanceFromOrigin(String moves) {
        // Calculate the net distance from L and R moves
        int leftCount = countCharacter(moves, 'L');
        int rightCount = countCharacter(moves, 'R');
        int wildcardCount = countCharacter(moves, '_');
      
        // The furthest distance is achieved by adding all wildcards to the majority direction
        // Math.abs(leftCount - rightCount) gives the net displacement from L and R moves
        // Adding wildcardCount maximizes the distance in the dominant direction
        return Math.abs(leftCount - rightCount) + wildcardCount;
    }

    /**
     * Counts the occurrences of a specific character in a string.
     * 
     * @param str The input string to search
     * @param targetChar The character to count
     * @return The number of occurrences of targetChar in str
     */
    private int countCharacter(String str, char targetChar) {
        int count = 0;
      
        // Iterate through each character in the string
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == targetChar) {
                count++;
            }
        }
      
        return count;
    }
}