class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        // Map to store translation vectors and their occurrence counts
        // Key: translation vector [deltaRow, deltaCol], Value: count of overlapping 1s
        Map<List<Integer>, Integer> translationCount = new HashMap<>();
        int maxOverlap = 0;
      
        // Iterate through all positions with 1s in img1
        for (int row1 = 0; row1 < n; row1++) {
            for (int col1 = 0; col1 < n; col1++) {
                if (img1[row1][col1] == 1) {
                    // For each 1 in img1, check all 1s in img2
                    for (int row2 = 0; row2 < n; row2++) {
                        for (int col2 = 0; col2 < n; col2++) {
                            if (img2[row2][col2] == 1) {
                                // Calculate the translation vector needed to align 
                                // current 1 in img1 with current 1 in img2
                                List<Integer> translationVector = List.of(row1 - row2, col1 - col2);
                              
                                // Increment count for this translation vector and update max
                                // merge() adds 1 to existing count or initializes to 1 if new
                                int currentCount = translationCount.merge(translationVector, 1, Integer::sum);
                                maxOverlap = Math.max(maxOverlap, currentCount);
                            }
                        }
                    }
                }
            }
        }
      
        return maxOverlap;
    }
}
