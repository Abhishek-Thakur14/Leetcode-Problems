class Solution {
    /**
     * Checks if a number is divisible by the sum of (digit sum + digit product)
     * @param n the number to check
     * @return true if n is divisible by (sum of digits + product of digits), false otherwise
     */
    public boolean checkDivisibility(int n) {
        int digitSum = 0;      // Sum of all digits
        int digitProduct = 1;  // Product of all digits
        int number = n;        // Copy of n for digit extraction
      
        // Extract each digit and calculate sum and product
        while (number != 0) {
            int currentDigit = number % 10;  // Get the last digit
            number /= 10;                     // Remove the last digit
          
            digitSum += currentDigit;        // Add digit to sum
            digitProduct *= currentDigit;    // Multiply digit to product
        }
      
        // Check if n is divisible by (digitSum + digitProduct)
        return n % (digitSum + digitProduct) == 0;
    }
}
