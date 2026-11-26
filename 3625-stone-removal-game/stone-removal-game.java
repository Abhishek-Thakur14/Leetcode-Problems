class Solution {
    public boolean canAliceWin(int n) {
        // Initial number of stones that must be removed in the first turn
        int stonesPerTurn = 10;
      
        // Counter to track the number of turns played
        int turnCount = 0;
      
        // Continue playing while there are enough stones remaining for the current turn
        while (n >= stonesPerTurn) {
            // Remove the required stones for this turn
            n -= stonesPerTurn;
          
            // Decrease the number of stones required for the next turn
            stonesPerTurn--;
          
            // Increment the turn counter
            turnCount++;
        }
      
        // Alice wins if the total number of turns is odd (she plays on odd turns: 1st, 3rd, 5th...)
        // The last player who could make a valid move wins
        return turnCount % 2 == 1;
    }
}
