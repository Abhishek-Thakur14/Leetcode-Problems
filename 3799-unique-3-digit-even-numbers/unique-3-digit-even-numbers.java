class Solution {
    public int totalNumbers(int[] digits) {
        // Use a HashSet to store unique 3-digit numbers
        Set<Integer> uniqueNumbers = new HashSet<>();
        int arrayLength = digits.length;
      
        // Iterate through all possible positions for the ones digit (must be even)
        for (int onesIndex = 0; onesIndex < arrayLength; ++onesIndex) {
            // Skip if the digit at this position is odd
            if (digits[onesIndex] % 2 == 1) {
                continue;
            }
          
            // Iterate through all possible positions for the tens digit
            for (int tensIndex = 0; tensIndex < arrayLength; ++tensIndex) {
                // Skip if using the same index as the ones digit
                if (onesIndex == tensIndex) {
                    continue;
                }
              
                // Iterate through all possible positions for the hundreds digit
                for (int hundredsIndex = 0; hundredsIndex < arrayLength; ++hundredsIndex) {
                    // Skip if:
                    // 1. The digit is 0 (cannot be in hundreds place)
                    // 2. The index is already used for ones or tens digit
                    if (digits[hundredsIndex] == 0 || 
                        hundredsIndex == onesIndex || 
                        hundredsIndex == tensIndex) {
                        continue;
                    }
                  
                    // Form the 3-digit number and add to set
                    int threeDigitNumber = digits[hundredsIndex] * 100 + 
                                         digits[tensIndex] * 10 + 
                                         digits[onesIndex];
                    uniqueNumbers.add(threeDigitNumber);
                }
            }
        }
      
        // Return the count of unique 3-digit numbers formed
        return uniqueNumbers.size();
    }
}
