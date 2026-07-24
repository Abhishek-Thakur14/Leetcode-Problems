class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAXV = 2048; // since nums[i] <= 1500 < 2^11

        // Step 1: find unique values
        boolean[] present = new boolean[MAXV];
        for (int x : nums) present[x] = true;

        int uniqueCount = 0;
        for (boolean p : present) if (p) uniqueCount++;

        int[] uniq = new int[uniqueCount];
        int idx = 0;
        for (int v = 0; v < MAXV; v++) {
            if (present[v]) uniq[idx++] = v;
        }

        // Step 2: all pairwise XORs (a ^ b)
        boolean[] pairXor = new boolean[MAXV];
        for (int a : uniq) {
            for (int b : uniq) {
                pairXor[a ^ b] = true;
            }
        }

        // Step 3: XOR each achievable pair value with every unique element
        boolean[] tripleXor = new boolean[MAXV];
        for (int s = 0; s < MAXV; s++) {
            if (!pairXor[s]) continue;
            for (int c : uniq) {
                tripleXor[s ^ c] = true;
            }
        }

        // Step 4: count distinct achievable values
        int count = 0;
        for (boolean b : tripleXor) if (b) count++;
        return count;
    }
}