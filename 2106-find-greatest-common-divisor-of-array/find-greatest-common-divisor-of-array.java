class Solution {
    /**
     * Finds the greatest common divisor (GCD) of the largest and smallest elements in the array.
     * 
     * @param nums the input array of positive integers
     * @return the GCD of the maximum and minimum elements
     */
    public int findGCD(int[] nums) {
        // Initialize max to smallest possible value and min to largest possible value
        int maxElement = 1;
        int minElement = 1000;
      
        // Iterate through array to find maximum and minimum elements
        for (int num : nums) {
            maxElement = Math.max(maxElement, num);
            minElement = Math.min(minElement, num);
        }
      
        // Calculate and return the GCD of max and min elements
        return gcd(maxElement, minElement);
    }

    /**
     * Calculates the greatest common divisor using Euclidean algorithm.
     * 
     * @param a the first number
     * @param b the second number
     * @return the GCD of a and b
     */
    private int gcd(int a, int b) {
        // Base case: when b becomes 0, a is the GCD
        // Recursive case: GCD(a, b) = GCD(b, a % b)
        return b == 0 ? a : gcd(b, a % b);
    }
}
