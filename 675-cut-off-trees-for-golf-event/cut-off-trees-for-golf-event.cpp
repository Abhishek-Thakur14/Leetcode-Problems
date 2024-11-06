class Solution {
private:
    // Check if the cell (i, j) is within the bounds of the grid.
    bool isSafe(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    // BFS function to find the shortest path from (currI, currJ) to (nextI, nextJ).
    int BFS(vector<vector<int>>& forest, int currI, int currJ, int nextI, int nextJ) {
        int m = forest.size();
        int n = forest[0].size();
        int step = 0;
        vector<vector<bool>> visited(m, vector<bool>(n, false));

        // Start BFS from the current position.
        visited[currI][currJ] = true;
        vector<vector<int>> directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        queue<pair<int, int>> q;
        q.push({currI, currJ});

        // BFS loop
        while (!q.empty()) {
            int size = q.size();
            while (size--) {
                int i = q.front().first;
                int j = q.front().second;
                q.pop();

                // If we've reached the target tree, return the number of steps.
                if (i == nextI && j == nextJ) return step;

                // Explore all four directions.
                for (auto& dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];

                    // If it's a valid and unvisited cell, and it's not a blocked cell (forest[x][y] != 0).
                    if (isSafe(x, y, m, n) && visited[x][y] == false && forest[x][y] != 0) {
                        visited[x][y] = true;
                        q.push({x, y});
                    }
                }
            }
            step++;  // Increment step count after processing all current level nodes.
        }

        // If the tree is unreachable, return a large value.
        return INT_MAX;
    }

public:
    int cutOffTree(vector<vector<int>>& forest) {
        int m = forest.size();
        int n = forest[0].size();

        // Priority queue to store trees in increasing order of height.
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq;

        // Insert all trees (value >= 2) into the priority queue with their coordinates.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = forest[i][j];
                if (num >= 2) {
                    pq.push({num, i, j});
                }
            }
        }

        int lastI = 0, lastJ = 0;  // Start position at (0,0)
        int step = 0;

        // Process each tree in increasing order of height.
        while (!pq.empty()) {
            auto vec = pq.top();
            pq.pop();

            // Perform BFS to find the shortest path from the last position to the next tree.
            int pathFinder = BFS(forest, lastI, lastJ, vec[1], vec[2]);

            // If a tree is unreachable, return -1.
            if (pathFinder == INT_MAX) return -1;

            // Add the steps to the total and update the last position.
            step += pathFinder;
            lastI = vec[1];
            lastJ = vec[2];
        }

        return step;  // Return the total number of steps.
    }
};