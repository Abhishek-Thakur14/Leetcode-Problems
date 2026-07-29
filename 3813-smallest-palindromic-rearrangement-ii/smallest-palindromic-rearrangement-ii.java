class Solution {
    private static final long K_CAP = 2_000_001L;

    // Count prime factors of n! using Legendre's formula
    private void addFactorialPrimes(int n, int[] primes, int[] count, boolean add) {
        for (int pi = 0; pi < primes.length; pi++) {
            int p = primes[pi];
            if (p > n) break;
            long pk = p;
            while (pk <= n) {
                if (add) count[pi] += (int)(n / pk);
                else     count[pi] -= (int)(n / pk);
                pk *= p;
            }
        }
    }

    private long countPerms(int[] freq, int remaining, int[] primes) {
        int[] exp = new int[primes.length];
        addFactorialPrimes(remaining, primes, exp, true);
        for (int i = 0; i < 26; i++) {
            if (freq[i] >= 2) addFactorialPrimes(freq[i], primes, exp, false);
        }
        // Multiply out remaining prime factors, cap at K_CAP
        long result = 1;
        for (int pi = 0; pi < primes.length; pi++) {
            for (int e = 0; e < exp[pi]; e++) {
                result *= primes[pi];
                if (result >= K_CAP) return K_CAP;
            }
        }
        return result;
    }

    private int[] sieve(int n) {
        boolean[] composite = new boolean[n + 1];
        java.util.List<Integer> ps = new java.util.ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!composite[i]) {
                ps.add(i);
                for (long j = (long)i*i; j <= n; j += i)
                    composite[(int)j] = true;
            }
        }
        return ps.stream().mapToInt(Integer::intValue).toArray();
    }

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        int[] halfFreq = new int[26];
        char mid = 0;
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) mid = (char) ('a' + i);
            halfFreq[i] = freq[i] / 2;
            halfLen += halfFreq[i];
        }

        int[] primes = sieve(halfLen > 1 ? halfLen : 2);

        long total = countPerms(halfFreq, halfLen, primes);
        if ((long) k > total) return "";

        StringBuilder half = new StringBuilder();
        int[] cur = halfFreq.clone();
        int remaining = halfLen;

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (cur[c] == 0) continue;
                cur[c]--;
                remaining--;
                long perms = countPerms(cur, remaining, primes);
                if ((long) k <= perms) {
                    half.append((char) ('a' + c));
                    break;
                } else {
                    k -= perms;
                    cur[c]++;
                    remaining++;
                }
            }
        }

        StringBuilder result = new StringBuilder(half);
        if (mid != 0) result.append(mid);
        result.append(half.reverse());

        return result.toString();
    }
}