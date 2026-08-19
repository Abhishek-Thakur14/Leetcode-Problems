class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map to store reserved seats for each row
        // Key: row number, Value: bitmask of reserved seats
        Map<Integer, Integer> rowReservations = new HashMap<>();
      
        // Build bitmask for each row with reserved seats
        for (int[] reservation : reservedSeats) {
            int row = reservation[0];
            int seatNumber = reservation[1];
            // Convert seat number to bit position (seat 10 -> bit 0, seat 1 -> bit 9)
            // Merge with existing reservations using bitwise OR
            rowReservations.merge(row, 1 << (10 - seatNumber), (existing, newBit) -> existing | newBit);
        }
      
        // Define masks for three possible 4-seat group positions
        // Seats 2-5: 0111100000 (bits for seats 2,3,4,5)
        int leftGroupMask = 0b0111100000;
        // Seats 6-9: 0000011110 (bits for seats 6,7,8,9)  
        int rightGroupMask = 0b0000011110;
        // Seats 4-7: 0001111000 (bits for seats 4,5,6,7)
        int middleGroupMask = 0b0001111000;
        int[] groupMasks = {leftGroupMask, rightGroupMask, middleGroupMask};
      
        // Rows without any reservations can fit 2 families (left and right groups)
        int totalFamilies = (n - rowReservations.size()) * 2;
      
        // Check each row with reservations
        for (int reservedSeatsBitmask : rowReservations.values()) {
            // Try to place families in available positions
            for (int groupMask : groupMasks) {
                // Check if this group position has no conflicts with reserved seats
                if ((reservedSeatsBitmask & groupMask) == 0) {
                    // Mark these seats as occupied to avoid double counting
                    reservedSeatsBitmask |= groupMask;
                    totalFamilies++;
                }
            }
        }
      
        return totalFamilies;
    }
}
