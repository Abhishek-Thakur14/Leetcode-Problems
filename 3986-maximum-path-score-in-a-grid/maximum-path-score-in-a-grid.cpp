class Solution {
public:

    int maxPathScore(vector<vector<int>>& grid, int k) {
        int n = grid.size(), m = grid[0].size();
        // dp[i][j][c]: the maximum score that can be achieved 
        // when reaching cell (i, j) with a total cost of exactly c
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(m, vector<int>(k+1, -1)));
        int cost[3] = {0,1,1};

        dp[0][0][0] = 0;
       
        for(int i=0 ; i<n ; ++i){
            for(int j=0 ; j<m ; ++j){
                for(int c=0 ; c<=k ; ++c){
                    if(dp[i][j][c] != -1){
                        int tot_cost;
                        //go down
                        if((i+1 < n) && (tot_cost = c + cost[grid[i+1][j]]) <= k)
                            dp[i+1][j][tot_cost] = max(dp[i+1][j][tot_cost], dp[i][j][c] + grid[i+1][j]);
                        //go right
                        if((j+1 < m) && (tot_cost = c + cost[grid[i][j+1]]) <= k)
                            dp[i][j+1][tot_cost] = max(dp[i][j+1][tot_cost], dp[i][j][c] + grid[i][j+1]);   
                    }
                }
            }
        }
        return *max_element(dp[n-1][m-1].begin(), dp[n-1][m-1].end());
    }
};