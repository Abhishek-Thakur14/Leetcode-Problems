import java.util.*;

class Solution {

    private static final Map<Integer, Map<Integer, Integer>> FACTOR_COUNTS = new HashMap<>();
    static {
        FACTOR_COUNTS.put(0, new HashMap<>());
        FACTOR_COUNTS.put(1, new HashMap<>());
        FACTOR_COUNTS.put(2, Map.of(2, 1));
        FACTOR_COUNTS.put(3, Map.of(3, 1));
        FACTOR_COUNTS.put(4, Map.of(2, 2));
        FACTOR_COUNTS.put(5, Map.of(5, 1));
        FACTOR_COUNTS.put(6, Map.of(2, 1, 3, 1));
        FACTOR_COUNTS.put(7, Map.of(7, 1));
        FACTOR_COUNTS.put(8, Map.of(2, 3));
        FACTOR_COUNTS.put(9, Map.of(3, 2));
    }

    public String smallestNumber(String num, long t) {
        long[] primeCount = getPrimeCount(t);
        if (primeCount == null) return "-1";

        int[] factorCount = getFactorCount(primeCount);
        int digitsNeeded = sumValues(factorCount);

        if (digitsNeeded > num.length()) {
            return construct(factorCount);
        }

        long[] prefixPrimes = getPrimeCountFromString(num);
        int firstZeroIndex = num.indexOf('0');

        if (firstZeroIndex == -1) {
            firstZeroIndex = num.length();
            if (isSubset(primeCount, prefixPrimes)) {
                return num;
            }
        }

        long[] runningPrefix = getPrimeCountFromString(num);

        for (int i = num.length() - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            runningPrefix = subtract(runningPrefix, toPrimeCounts(FACTOR_COUNTS.get(d)));
            int spaceAfter = num.length() - 1 - i;

            if (i > firstZeroIndex) continue;

            for (int bigger = d + 1; bigger <= 9; bigger++) {
                long[] remaining = subtract(
                    subtract(primeCount, runningPrefix),
                    toPrimeCounts(FACTOR_COUNTS.get(bigger))
                );
                int[] factorsNeeded = getFactorCount(remaining);
                int digitsForRest = sumValues(factorsNeeded);

                if (digitsForRest <= spaceAfter) {
                    int fillOnes = spaceAfter - digitsForRest;
                    return num.substring(0, i)
                         + bigger
                         + "1".repeat(fillOnes)
                         + construct(factorsNeeded);
                }
            }
        }

        int[] extendedFactors = getFactorCount(primeCount);
        int ones = num.length() + 1 - sumValues(extendedFactors);
        return "1".repeat(ones) + construct(extendedFactors);
    }

    private long[] getPrimeCount(long t) {
        long[] count = new long[4];
        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < primes.length; i++) {
            while (t % primes[i] == 0) {
                t /= primes[i];
                count[i]++;
            }
        }
        return t == 1 ? count : null;
    }

    private long[] getPrimeCountFromString(String num) {
        long[] count = new long[4];
        for (char ch : num.toCharArray()) {
            int d = ch - '0';
            for (Map.Entry<Integer, Integer> e : FACTOR_COUNTS.get(d).entrySet()) {
                count[primeIndex(e.getKey())] += e.getValue();
            }
        }
        return count;
    }

    private long[] toPrimeCounts(Map<Integer, Integer> map) {
        long[] count = new long[4];
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            count[primeIndex(e.getKey())] += e.getValue();
        }
        return count;
    }

    private int primeIndex(int prime) {
        return switch (prime) {
            case 2 -> 0; case 3 -> 1; case 5 -> 2; default -> 3;
        };
    }

    private int[] getFactorCount(long[] count) {
        int[] res = new int[10];
        long twos = count[0], threes = count[1];

        res[8] = (int)(twos / 3);   twos %= 3;
        res[9] = (int)(threes / 2); threes %= 2;
        res[4] = (int)(twos / 2);   twos %= 2;
        res[5] = (int) count[2];
        res[7] = (int) count[3];

        if (twos == 1 && threes == 1) { res[6]++; twos = 0; threes = 0; }
        if (threes == 1 && res[4] >= 1) { res[2]++; res[6]++; res[4]--; threes = 0; }

        res[2] += (int) twos;
        res[3] += (int) threes;
        return res;
    }

    private String construct(int[] factors) {
        StringBuilder sb = new StringBuilder();
        for (int d = 2; d <= 9; d++)
            sb.append(String.valueOf(d).repeat(factors[d]));
        return sb.toString();
    }

    private boolean isSubset(long[] a, long[] b) {
        for (int i = 0; i < 4; i++)
            if (b[i] < a[i]) return false;
        return true;
    }

    private long[] subtract(long[] a, long[] b) {
        long[] res = new long[4];
        for (int i = 0; i < 4; i++)
            res[i] = Math.max(0, a[i] - b[i]);
        return res;
    }

    private int sumValues(int[] factors) {
        int sum = 0;
        for (int d = 2; d <= 9; d++) sum += factors[d];
        return sum;
    }
}