class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Base edge cases for arrays of small sizes
        if (n <= 2) {
            return n;
        }
        
        // Find the number of bits needed to represent n
        int bits = 32 - Integer.numberOfLeadingZeros(n);
        
        // Return 2 raised to the power of bits (1 << bits)
        return 1 << bits;
    }
}
