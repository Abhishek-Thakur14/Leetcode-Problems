class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int i = 0;

        // Phase 1: strictly increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) i++;
        if (i == 0) return false;

        // Phase 2: strictly decreasing
        int mid = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) i++;
        if (i == mid || i == n - 1) return false;

        // Phase 3: strictly increasing
        while (i + 1 < n && nums[i] < nums[i + 1]) i++;

        return i == n - 1;
    }
}
