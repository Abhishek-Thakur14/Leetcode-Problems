class Solution {
    // Mapping array: index represents original digit, value represents rotated digit
    // -1 means the digit cannot be rotated (invalid)
    // 0->0, 1->1, 2->5, 3->invalid, 4->invalid, 5->2, 6->9, 7->invalid, 8->8, 9->6
    private int[] rotationMap = new int[] {0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

    public int rotatedDigits(int n) {
        int goodNumberCount = 0;
      
        // Check each number from 1 to n
        for (int number = 1; number <= n; ++number) {
            if (isGoodNumber(number)) {
                ++goodNumberCount;
            }
        }
      
        return goodNumberCount;
    }

    /**
     * Checks if a number is "good" after rotation
     * A number is good if:
     * 1. All its digits can be rotated (no 3, 4, or 7)
     * 2. The rotated number is different from the original
     */
    private boolean isGoodNumber(int originalNumber) {
        int rotatedNumber = 0;
        int tempNumber = originalNumber;
        int placeValue = 1;  // Represents the position multiplier (1, 10, 100, ...)
      
        // Process each digit from right to left
        while (tempNumber > 0) {
            int currentDigit = tempNumber % 10;
          
            // Check if current digit can be rotated
            if (rotationMap[currentDigit] == -1) {
                return false;  // Contains invalid digit (3, 4, or 7)
            }
          
            // Build the rotated number digit by digit
            rotatedNumber = rotationMap[currentDigit] * placeValue + rotatedNumber;
            placeValue *= 10;
            tempNumber /= 10;
        }
      
        // Number is good only if it changes after rotation
        return originalNumber != rotatedNumber;
    }
}