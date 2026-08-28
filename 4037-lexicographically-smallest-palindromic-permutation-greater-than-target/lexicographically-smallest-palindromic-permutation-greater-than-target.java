class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) counts[c - 'a']++;

        int oddCount = 0, oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (counts[i] % 2 == 1) { oddCount++; oddChar = i; }
        }
        if (n % 2 == 0) {
            if (oddCount != 0) return "";
        } else {
            if (oddCount != 1) return "";
        }

        int half = n / 2;
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) halfCounts[i] = counts[i] / 2;
        char mid = (n % 2 == 1) ? (char) ('a' + oddChar) : '\0';

        String targetLeft = target.substring(0, half);

        // Case (b): try L == targetLeft exactly
        int[] tlCounts = new int[26];
        for (char c : targetLeft.toCharArray()) tlCounts[c - 'a']++;
        boolean matches = true;
        for (int i = 0; i < 26; i++) {
            if (tlCounts[i] != halfCounts[i]) { matches = false; break; }
        }
        if (matches) {
            StringBuilder sb = new StringBuilder();
            sb.append(targetLeft);
            if (n % 2 == 1) sb.append(mid);
            sb.append(new StringBuilder(targetLeft).reverse());
            String candidate = sb.toString();
            if (candidate.compareTo(target) > 0) return candidate;
        }

        // Case (a): smallest L > targetLeft as an array, using halfCounts multiset
        int[] available = halfCounts.clone();
        int bestI = -1;
        int[] bestAvailable = null;
        char[] tlArr = targetLeft.toCharArray();

        for (int i = 0; i < half; i++) {
            int xi = tlArr[i] - 'a';
            boolean found = false;
            for (int c = xi + 1; c < 26; c++) {
                if (available[c] > 0) { found = true; break; }
            }
            if (found) {
                bestI = i;
                bestAvailable = available.clone();
            }
            if (available[xi] > 0) {
                available[xi]--;
            } else {
                break;
            }
        }

        if (bestI == -1) return "";

        char[] L = new char[half];
        for (int i = 0; i < bestI; i++) L[i] = tlArr[i];
        int xi = tlArr[bestI] - 'a';
        int chosen = -1;
        for (int c = xi + 1; c < 26; c++) {
            if (bestAvailable[c] > 0) { chosen = c; break; }
        }
        L[bestI] = (char) ('a' + chosen);
        bestAvailable[chosen]--;

        int idx = bestI + 1;
        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < bestAvailable[c]; k++) L[idx++] = (char) ('a' + c);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(new String(L));
        if (n % 2 == 1) sb.append(mid);
        sb.append(new StringBuilder(new String(L)).reverse());

        return sb.toString();
    }
}