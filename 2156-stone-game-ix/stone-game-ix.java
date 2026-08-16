class Solution {
    public boolean stoneGameIX(int[] stones) {
        // Count stones by their remainder when divided by 3
        int[] countByRemainder = new int[3];
        for (int stone : stones) {
            countByRemainder[stone % 3]++;
        }
      
        // Try two scenarios: Alice starts with remainder 1 or remainder 2
        // Scenario 1: Start with remainder 1 (original count distribution)
        int[] scenario1 = {countByRemainder[0], countByRemainder[1], countByRemainder[2]};
      
        // Scenario 2: Start with remainder 2 (swap counts of remainder 1 and 2)
        int[] scenario2 = {countByRemainder[0], countByRemainder[2], countByRemainder[1]};
      
        // Alice wins if she can win in either scenario
        return check(scenario1) || check(scenario2);
    }

    private boolean check(int[] remainderCounts) {
        // Alice must pick a stone with remainder 1 to start this scenario
        // If no such stone exists, this scenario is invalid
        remainderCounts[1]--;
        if (remainderCounts[1] < 0) {
            return false;
        }
      
        // Calculate total turns in the game
        // Start with 1 (Alice's first move)
        // Add pairs of remainder 1 and 2 stones (each pair adds 2 turns)
        // Add all remainder 0 stones (they don't affect the sum modulo 3)
        int totalTurns = 1 + Math.min(remainderCounts[1], remainderCounts[2]) * 2 + remainderCounts[0];
      
        // If there are more remainder 1 stones than remainder 2 stones,
        // one more remainder 1 stone can be played
        if (remainderCounts[1] > remainderCounts[2]) {
            remainderCounts[1]--;
            totalTurns++;
        }
      
        // Alice wins if:
        // 1. Total turns is odd (Bob runs out of valid moves)
        // 2. The remaining counts are unequal (prevents Bob from winning by exhausting stones)
        return totalTurns % 2 == 1 && remainderCounts[1] != remainderCounts[2];
    }
}
