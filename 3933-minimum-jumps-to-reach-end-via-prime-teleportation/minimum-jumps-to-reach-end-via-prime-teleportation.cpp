class Solution {
public:

    int minJumps(vector<int>& nums) {

        int n = nums.size();

        if (n == 1)
            return 0;

        int mx = *max_element(nums.begin(), nums.end());

        // SPF
        vector<int> spf(mx + 1);

        for (int i = 0; i <= mx; i++)
            spf[i] = i;

        for (long long i = 2; i * i <= mx; i++) {

            if (spf[i] == i) {

                for (long long j = i * i; j <= mx; j += i) {

                    if (spf[j] == j)
                        spf[j] = i;
                }
            }
        }

        // prime -> indices divisible by prime
        unordered_map<int, vector<int>> mp;

        for (int i = 0; i < n; i++) {

            int x = nums[i];

            while (x > 1) {

                int p = spf[x];

                mp[p].push_back(i);

                while (x % p == 0)
                    x /= p;
            }
        }

        vector<int> vis(n, 0);

        queue<int> q;

        q.push(0);

        vis[0] = 1;

        int steps = 0;

        while (!q.empty()) {

            int sz = q.size();

            while (sz--) {

                int i = q.front();
                q.pop();

                if (i == n - 1)
                    return steps;

                // left
                if (i - 1 >= 0 && !vis[i - 1]) {

                    vis[i - 1] = 1;
                    q.push(i - 1);
                }

                // right
                if (i + 1 < n && !vis[i + 1]) {

                    vis[i + 1] = 1;
                    q.push(i + 1);
                }

                int val = nums[i];

                // teleport if prime
                if (val > 1 && spf[val] == val) {

                    auto it = mp.find(val);

                    if (it != mp.end()) {

                        for (int idx : it->second) {

                            if (!vis[idx]) {

                                vis[idx] = 1;
                                q.push(idx);
                            }
                        }

                        // important optimization
                        mp.erase(it);
                    }
                }
            }

            steps++;
        }

        return -1;
    }
};