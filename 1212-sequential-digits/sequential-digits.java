class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        // List to store all valid sequential digit numbers
        List<Integer> result = new ArrayList<>();
      
        // Iterate through possible starting digits (1 to 8)
        // Starting digit cannot be 9 since we need at least one more sequential digit
        for (int startDigit = 1; startDigit < 9; ++startDigit) {
            // Initialize current number with the starting digit
            int currentNumber = startDigit;
          
            // Build sequential numbers by appending consecutive digits
            // Next digit starts from (startDigit + 1) and goes up to 9
            for (int nextDigit = startDigit + 1; nextDigit < 10; ++nextDigit) {
                // Append the next sequential digit to current number
                currentNumber = currentNumber * 10 + nextDigit;
              
                // Check if the current number falls within the given range
                if (currentNumber >= low && currentNumber <= high) {
                    result.add(currentNumber);
                }
            }
        }
      
        // Sort the result list in ascending order
        Collections.sort(result);
      
        return result;
    }
}