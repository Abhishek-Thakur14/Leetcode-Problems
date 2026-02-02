class Solution {

    PriorityQueue<Integer> small = new PriorityQueue<>((a, b) -> b - a); // max heap
    PriorityQueue<Integer> large = new PriorityQueue<>();               // min heap
    Map<Integer, Integer> delayed = new HashMap<>();

    int smallSize = 0;
    long smallSum = 0;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        for (int i = 1; i <= dist + 1; i++) {
            add(nums[i]);
        }
        makeBalance(k);

        long ans = smallSum;

        for (int i = dist + 2; i < n; i++) {
            remove(nums[i - dist - 1]);
            add(nums[i]);
            makeBalance(k);
            ans = Math.min(ans, smallSum);
        }

        return ans + nums[0];
    }

    private void add(int x) {
        if (small.isEmpty() || x <= small.peek()) {
            small.offer(x);
            smallSum += x;
            smallSize++;
        } else {
            large.offer(x);
        }
    }

    private void remove(int x) {
        delayed.put(x, delayed.getOrDefault(x, 0) + 1);

        if (!small.isEmpty() && x <= small.peek()) {
            smallSum -= x;
            smallSize--;
        }
        prune(small);
        prune(large);
    }

    private void makeBalance(int k) {
        while (smallSize > k - 1) {
            int x = small.poll();
            smallSum -= x;
            smallSize--;
            large.offer(x);
            prune(small);
        }

        while (smallSize < k - 1 && !large.isEmpty()) {
            int x = large.poll();
            smallSum += x;
            smallSize++;
            small.offer(x);
            prune(large);
        }
    }

    private void prune(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty()) {
            int x = pq.peek();
            if (!delayed.containsKey(x)) break;
            delayed.put(x, delayed.get(x) - 1);
            if (delayed.get(x) == 0) delayed.remove(x);
            pq.poll();
        }
    }
}
