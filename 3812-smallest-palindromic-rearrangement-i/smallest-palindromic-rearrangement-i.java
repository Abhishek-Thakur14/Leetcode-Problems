class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        StringBuilder half = new StringBuilder();
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) mid = (char) ('a' + i); // at most one odd
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) ('a' + i));
            }
        }

        // half is already sorted smallest to largest (we iterated a→z)
        StringBuilder result = new StringBuilder(half);
        if (mid != 0) result.append(mid);
        result.append(half.reverse());

        return result.toString();
    }
}