class Solution {
    /**
     * Finds the smallest number greater than or equal to n whose digit product is divisible by t
     * @param n the minimum number to start searching from
     * @param t the target divisor for the digit product
     * @return the smallest number >= n whose digit product is divisible by t
     */
    public int smallestNumber(int n, int t) {
        // Iterate from n onwards until we find a valid number
        for (int currentNumber = n; ; currentNumber++) {
            // Calculate the product of all digits in currentNumber
            int digitProduct = 1;
            int tempNumber = currentNumber;
          
            // Extract each digit and multiply them together
            while (tempNumber > 0) {
                int lastDigit = tempNumber % 10;  // Get the last digit
                digitProduct *= lastDigit;         // Multiply to the product
                tempNumber /= 10;                   // Remove the last digit
            }
          
            // Check if the digit product is divisible by t
            if (digitProduct % t == 0) {
                return currentNumber;
            }
        }
    }
}