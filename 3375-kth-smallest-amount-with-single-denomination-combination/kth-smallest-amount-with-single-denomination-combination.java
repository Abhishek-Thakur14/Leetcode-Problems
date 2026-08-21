class Solution {
    private int[] coins;
    private int k;

    /**
     * Finds the k-th smallest positive integer that is divisible by at least one coin value.
     * Uses binary search on the answer combined with inclusion-exclusion principle.
     */
    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;
        this.k = k;

        // Binary search using the standard template
        long left = 1;
        long right = (long) 1e11;
        long firstTrueIndex = -1;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (feasible(mid)) {
                firstTrueIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return firstTrueIndex;
    }

    /**
     * Check if there are at least k valid amounts <= maxValue.
     */
    private boolean feasible(long maxValue) {
        return countMultiples(maxValue) >= k;
    }

    /**
     * Counts how many positive integers up to maxValue are divisible by at least one coin.
     * Uses inclusion-exclusion principle with bitmask.
     */
    private long countMultiples(long maxValue) {
        long count = 0;
        int n = coins.length;

        for (int bitmask = 1; bitmask < (1 << n); ++bitmask) {
            long lcmValue = 1;

            for (int j = 0; j < n; ++j) {
                if ((bitmask >> j & 1) == 1) {
                    lcmValue = lcm(lcmValue, coins[j]);
                    if (lcmValue > maxValue) {
                        break;
                    }
                }
            }

            int subsetSize = Integer.bitCount(bitmask);
            if (subsetSize % 2 == 1) {
                count += maxValue / lcmValue;
            } else {
                count -= maxValue / lcmValue;
            }
        }

        return count;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
