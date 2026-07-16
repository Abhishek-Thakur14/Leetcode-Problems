import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int mx = 0;
        
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }
        
        Arrays.sort(prefixGcd);
        
        long total = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            total += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return total;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}