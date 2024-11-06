#include <queue>

class Solution {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& a) {
        int m = a.size();
        int n = a[0].size();
        
        // Initialize the result matrix with a large number (infinity equivalent)
        vector<vector<int>> ans(m, vector<int>(n, 1e9));
        
        // Queue to perform BFS, storing coordinates (i, j)
        queue<pair<int, int>> q;
        
        // Add all '0' cells to the queue and set their distances to 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 0) {
                    ans[i][j] = 0;
                    q.push({i, j});  // Push all '0' positions into the queue
                }
            }
        }
        
        // BFS to update distances
        while (!q.empty()) {
            int i = q.front().first;
            int j = q.front().second;
            q.pop();
            
            // Move down
            if (i + 1 < m && ans[i + 1][j] > ans[i][j] + 1) {
                ans[i + 1][j] = ans[i][j] + 1;
                q.push({i + 1, j});
            }
            
            // Move up
            if (i - 1 >= 0 && ans[i - 1][j] > ans[i][j] + 1) {
                ans[i - 1][j] = ans[i][j] + 1;
                q.push({i - 1, j});
            }
            
            // Move right
            if (j + 1 < n && ans[i][j + 1] > ans[i][j] + 1) {
                ans[i][j + 1] = ans[i][j] + 1;
                q.push({i, j + 1});
            }
            
            // Move left
            if (j - 1 >= 0 && ans[i][j - 1] > ans[i][j] + 1) {
                ans[i][j - 1] = ans[i][j] + 1;
                q.push({i, j - 1});
            }
        }
        
        return ans;
    }
};