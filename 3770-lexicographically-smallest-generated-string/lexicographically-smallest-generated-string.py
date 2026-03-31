# Source - https://stackoverflow.com/q/79535178
# Posted by eatfood, modified by community. See post 'Timeline' for change history
# Retrieved 2026-03-31, License - CC BY-SA 4.0

class Solution:
    def generateString(self, req: str, pattern: str) -> str:
        n = len(req)
        m = len(pattern)
        ans = ['?'] * (n + m - 1)
        fixed = [False] * (n + m - 1)
        for i in range(n):
            if req[i] == 'T':
                # Fill this location with the pattern
                for j in range(m):
                    if fixed[i + j] and ans[i + j] != pattern[j]:
                        # There is a contradiction already
                        return ""
                    ans[i + j] = pattern[j]
                    fixed[i + j] = True

        # Check for unsatisfiable 'F' criteria
        for i in range(n):
            if req[i] == 'F' and ''.join(ans[i:i + m]) == pattern:
                return ""

        # Greedily fill '?' with 'a'
        for i in range(len(ans)):
            if ans[i] == '?':
                ans[i] = 'a'
        
        # Check for unsatisfied 'F' criteria.
        for i in range(n):
            if req[i] == 'F':
                # We must make sure it does not match
                same = (pattern == "".join(ans[i:i + m]))
                if not same:
                    continue
                # We have an unsatisfied 'F' criterion here
                # Fix it by flipping the rightmost flippable position to 'b'.
                else:
                    # flip the rightmost char
                    for j in range(m - 1, -1, -1):
                        if not fixed[i + j]:
                            ans[i + j] = 'b'
                            break

        return "".join(ans)
