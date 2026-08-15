class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        for (int n : nums) xor ^= n;

        // XOR of whole array is non-zero → take all
        if (xor != 0) return nums.length;

        // All elements are 0 → no valid subsequence
        boolean allZero = true;
        for (int n : nums) {
            if (n != 0) { allZero = false; break; }
        }
        if (allZero) return 0;

        // Remove one element → XOR becomes non-zero
        return nums.length - 1;
    }
}