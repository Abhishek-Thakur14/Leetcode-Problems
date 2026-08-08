class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[] ans = new int[m];
        // last[j] := index i of the last occurrence in word1 where word1[i] == word2[j]
        int[] last = new int[m];
        Arrays.fill(last, -1);

        int i = n - 1, j = m - 1;
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j))
                last[j--] = i;
            i--;
        }

        boolean canSkip = true;
        j = 0;
        for (i = 0; i < n; i++) {
            if (j == m) break;
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            } else if (canSkip && (j == m - 1 || i < last[j + 1])) {
                canSkip = false;
                ans[j++] = i;
            }
        }

        return j == m ? ans : new int[0];
    }
}