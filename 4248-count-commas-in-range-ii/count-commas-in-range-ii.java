class Solution {
    public long countCommas(long n) {
        long total = 0;

        // 1 comma  → numbers >= 1,000
        // 2 commas → numbers >= 1,000,000
        // 3 commas → numbers >= 1,000,000,000
        // 4 commas → numbers >= 1,000,000,000,000
        // 5 commas → numbers >= 1,000,000,000,000,000

        long threshold = 1000L;

        while (threshold <= n) {
            total += n - threshold + 1;  // count of numbers >= threshold
            threshold *= 1000L;          // next comma level
        }

        return total;
    }
}