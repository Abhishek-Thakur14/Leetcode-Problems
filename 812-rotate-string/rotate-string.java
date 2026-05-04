class Solution {
    /**
     * Determines if string 'goal' can be obtained by rotating string 's'.
     * A rotation means moving some number of characters from the beginning to the end.
     * 
     * Algorithm approach:
     * - If we concatenate 's' with itself (s + s), all possible rotations of 's' 
     *   will appear as substrings in the concatenated string
     * - For example: if s = "abcde", then s + s = "abcdeabcde"
     *   All rotations like "bcdea", "cdeab", "deabc", "eabcd" exist as substrings
     * 
     * @param s The original string to be rotated
     * @param goal The target string we want to achieve through rotation
     * @return true if 'goal' is a rotation of 's', false otherwise
     */
    public boolean rotateString(String s, String goal) {
        // First check: strings must have equal length for rotation to be possible
        // Second check: if goal is a rotation of s, it must appear in (s + s)
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}