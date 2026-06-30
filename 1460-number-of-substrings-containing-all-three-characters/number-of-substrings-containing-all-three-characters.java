class Solution {
    public int numberOfSubstrings(String s) {
        int[] lastPositions = new int[] {-1, -1, -1};
        int totalCount = 0;
        for (int currentIndex = 0; currentIndex < s.length(); currentIndex++) {
            char currentChar = s.charAt(currentIndex);
            lastPositions[currentChar - 'a'] = currentIndex;
            int leftmostPosition = Math.min(lastPositions[0], 
                                           Math.min(lastPositions[1], lastPositions[2]));
                                           
            totalCount += leftmostPosition + 1;
        }
      
        return totalCount;
    }
}