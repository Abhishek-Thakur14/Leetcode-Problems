class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            left[c] = Math.min(left[c], i);
            right[c] = Math.max(right[c], i);
        }

        // Build valid windows
        int[][] windows = new int[26][2];
        for (int c = 0; c < 26; c++) {
            windows[c][0] = -1;
            windows[c][1] = -1;
        }

        for (int c = 0; c < 26; c++) {
            if (left[c] == Integer.MAX_VALUE) continue;
            int end = getRight(c, left, right, s);
            if (end != -1) {
                windows[c][0] = left[c];
                windows[c][1] = end;
            }
        }

        // Sort valid windows by right boundary
        List<int[]> valid = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (windows[c][0] != -1) {
                valid.add(new int[]{windows[c][0], windows[c][1]});
            }
        }
        valid.sort((a, b) -> a[1] - b[1]);

        // Greedy pick
        List<String> result = new ArrayList<>();
        int prevRight = -1;
        for (int[] w : valid) {
            if (w[0] > prevRight) {
                result.add(s.substring(w[0], w[1] + 1));
                prevRight = w[1];
            }
        }
        return result;
    }

    private int getRight(int c, int[] left, int[] right, String s) {
        int end = right[c];
        int start = left[c];
        for (int i = start; i <= end; i++) {
            int curr = s.charAt(i) - 'a';
            if (left[curr] < start) return -1;
            end = Math.max(end, right[curr]);
        }
        return end;
    }
}