class Solution {
    public int countCommas(int n) {
        int total = 0;

        // Numbers 1,000 - 9,999 have 1 comma each
        // Numbers 10,000 - 99,999 have 1 comma each
        // Numbers 100,000+ have 2 commas (but n <= 10^5 so max is 1 comma range here)

        // Count how many numbers from 1 to n have at least 1 comma (>= 1000)
        if (n >= 1000) {
            total += n - 999;  // all numbers from 1000 to n have 1 comma
        }

        // Count how many numbers from 1 to n have at least 2 commas (>= 1,000,000)
        // Not needed here since n <= 100,000

        return total;
    }
}