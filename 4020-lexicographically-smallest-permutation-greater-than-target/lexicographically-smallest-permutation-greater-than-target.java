class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        // Step 1: count frequency of each letter in s
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // Step 2: find the longest prefix of target we can build using s's letters
        int[] avail = cnt.clone();
        int L = 0;
        while (L < n && avail[target.charAt(L) - 'a'] > 0) {
            avail[target.charAt(L) - 'a']--;
            L++;
        }

        // Step 3: try deviating at position i, starting from L and moving backward
        int i = L;
        while (i >= 0) {
            if (i < n) {
                int targetChar = target.charAt(i) - 'a';

                for (int c = targetChar + 1; c < 26; c++) {
                    if (avail[c] > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(target, 0, i);
                        sb.append((char) ('a' + c));
                        avail[c]--;

                        for (int k = 0; k < 26; k++) {
                            for (int t = 0; t < avail[k]; t++) {
                                sb.append((char) ('a' + k));
                            }
                        }
                        return sb.toString();
                    }
                }
            }

            if (i == 0) break;

            avail[target.charAt(i - 1) - 'a']++;
            i--;
        }

        return "";
    }
}