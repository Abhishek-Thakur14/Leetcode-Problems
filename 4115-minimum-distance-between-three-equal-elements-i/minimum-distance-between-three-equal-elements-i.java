class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int[] first = new int[n + 1];
        int[] second = new int[n + 1];
        int[] count = new int[n + 1];

        for (int value = 0; value <= n; value++) {
            first[value] = -1;
            second[value] = -1;
        }

        int answer = Integer.MAX_VALUE;

        for (int index = 0; index < n; index++) {
            int value = nums[index];
            count[value]++;

            if (count[value] == 1) {
                first[value] = index;
            } else if (count[value] == 2) {
                second[value] = index;
            } else {
                answer = Math.min(answer, (index - first[value]) * 2);
                first[value] = second[value];
                second[value] = index;
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}