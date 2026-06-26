class Solution {
    private int[] bit;
    
    private void update(int i) {
        for (i++; i < bit.length; i += i & -i) bit[i]++;
    }
    
    private int query(int i) {
        int s = 0;
        for (i++; i > 0; i -= i & -i) s += bit[i];
        return s;
    }
    
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length, OFFSET = n;
        bit = new int[2 * n + 2];
        int bal = 0;
        long ans = 0;
        update(bal + OFFSET);
        for (int x : nums) {
            bal += (x == target) ? 1 : -1;
            if (bal - 1 + OFFSET >= 0)
                ans += query(bal - 1 + OFFSET);
            update(bal + OFFSET);
        }
        return ans;
    }
}