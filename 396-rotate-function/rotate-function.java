class Solution {
    public int maxRotateFunction(int[] nums) {
        // Initialize the value of F(0) where F(k) = 0*nums[k] + 1*nums[k+1] + ... + (n-1)*nums[k+n-1]
        int currentFunctionValue = 0;
      
        // Sum of all elements in the array
        int totalSum = 0;
      
        // Length of the array
        int arrayLength = nums.length;
      
        // Calculate F(0) and the total sum of all elements
        for (int i = 0; i < arrayLength; ++i) {
            currentFunctionValue += i * nums[i];  // F(0) = 0*nums[0] + 1*nums[1] + ... + (n-1)*nums[n-1]
            totalSum += nums[i];
        }
      
        // Initialize the maximum value with F(0)
        int maxValue = currentFunctionValue;
      
        // Calculate F(1), F(2), ..., F(n-1) using the relationship:
        // F(k) = F(k-1) + sum - n * nums[n-k]
        // This formula works because when rotating, all elements shift position by 1,
        // except the last element which moves to position 0
        for (int rotation = 1; rotation < arrayLength; ++rotation) {
            currentFunctionValue = currentFunctionValue + totalSum - arrayLength * nums[arrayLength - rotation];
            maxValue = Math.max(maxValue, currentFunctionValue);
        }
      
        return maxValue;
    }
}