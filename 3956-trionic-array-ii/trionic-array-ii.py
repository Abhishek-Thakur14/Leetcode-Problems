class Solution:
    def maxSumTrionic(self, nums: List[int]) -> int:
        n = len(nums)

        # dp1[i] = max increasing sum starting at i
        dp1 = [-math.inf for _ in range(n)]

        for i in range(n - 2, -1, -1):
            if nums[i + 1] > nums[i]:
                dp1[i] = max(nums[i] + nums[i + 1], nums[i] + dp1[i + 1])

        # dp2[i] = max increasing sum ending at i
        dp2 = [-math.inf for _ in range(n)]

        for i in range(1, n):
            if nums[i] > nums[i - 1]:
                dp2[i] = max(nums[i] + nums[i - 1], nums[i] + dp2[i - 1])

        ans = -math.inf

        i, j = 1, 2

        while j < n - 1:

            if nums[j] >= nums[i]:
                i, j = j, j + 1
                continue

            s = nums[i]

            while j < n - 1 and nums[j] < nums[j - 1]:
                s += nums[j]
                j += 1

            j -= 1

            ans = max(ans, s + dp1[j] + dp2[i] - nums[i] - nums[j])

            i, j = j, j + 1

        return ans
