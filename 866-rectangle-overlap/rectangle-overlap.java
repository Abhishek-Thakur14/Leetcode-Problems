class Solution {
    /**
     * Determines if two rectangles overlap.
     * Each rectangle is represented by its bottom-left and top-right corners.
     * 
     * @param rec1 First rectangle [x1, y1, x2, y2] where (x1, y1) is bottom-left and (x2, y2) is top-right
     * @param rec2 Second rectangle [x3, y3, x4, y4] where (x3, y3) is bottom-left and (x4, y4) is top-right
     * @return true if the rectangles overlap, false otherwise
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Extract coordinates for first rectangle
        int leftX1 = rec1[0];    // Left x-coordinate of rectangle 1
        int bottomY1 = rec1[1];  // Bottom y-coordinate of rectangle 1
        int rightX1 = rec1[2];   // Right x-coordinate of rectangle 1
        int topY1 = rec1[3];     // Top y-coordinate of rectangle 1
      
        // Extract coordinates for second rectangle
        int leftX2 = rec2[0];    // Left x-coordinate of rectangle 2
        int bottomY2 = rec2[1];  // Bottom y-coordinate of rectangle 2
        int rightX2 = rec2[2];   // Right x-coordinate of rectangle 2
        int topY2 = rec2[3];     // Top y-coordinate of rectangle 2
      
        // Check for non-overlapping conditions:
        // 1. Rectangle 2's bottom is at or above rectangle 1's top (bottomY2 >= topY1)
        // 2. Rectangle 2's top is at or below rectangle 1's bottom (topY2 <= bottomY1)
        // 3. Rectangle 2's left is at or to the right of rectangle 1's right (leftX2 >= rightX1)
        // 4. Rectangle 2's right is at or to the left of rectangle 1's left (rightX2 <= leftX1)
        // If any of these conditions is true, rectangles don't overlap
        return !(bottomY2 >= topY1 || topY2 <= bottomY1 || leftX2 >= rightX1 || rightX2 <= leftX1);
    }
}
