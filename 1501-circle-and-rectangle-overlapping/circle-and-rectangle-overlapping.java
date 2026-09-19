class Solution {
    /**
     * Checks if a circle overlaps with an axis-aligned rectangle.
     * 
     * @param radius  The radius of the circle
     * @param xCenter The x-coordinate of the circle's center
     * @param yCenter The y-coordinate of the circle's center
     * @param x1      The x-coordinate of the rectangle's bottom-left corner
     * @param y1      The y-coordinate of the rectangle's bottom-left corner
     * @param x2      The x-coordinate of the rectangle's top-right corner
     * @param y2      The y-coordinate of the rectangle's top-right corner
     * @return true if the circle and rectangle overlap, false otherwise
     */
    public boolean checkOverlap(
        int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
      
        // Calculate the closest distance from circle center to rectangle along x-axis
        int closestXDistance = calculateDistanceToRange(x1, x2, xCenter);
      
        // Calculate the closest distance from circle center to rectangle along y-axis
        int closestYDistance = calculateDistanceToRange(y1, y2, yCenter);
      
        // Check if the squared distance from circle center to closest point on rectangle
        // is less than or equal to the squared radius (avoids square root calculation)
        return closestXDistance * closestXDistance + closestYDistance * closestYDistance 
               <= radius * radius;
    }

    /**
     * Calculates the minimum distance from a point to a range [rangeStart, rangeEnd].
     * 
     * @param rangeStart The start of the range (inclusive)
     * @param rangeEnd   The end of the range (inclusive)
     * @param point      The point to measure distance from
     * @return 0 if point is within the range, otherwise the distance to the nearest endpoint
     */
    private int calculateDistanceToRange(int rangeStart, int rangeEnd, int point) {
        // If point is within the range, distance is 0
        if (rangeStart <= point && point <= rangeEnd) {
            return 0;
        }
      
        // If point is before the range, return distance to range start
        // If point is after the range, return distance to range end
        return point < rangeStart ? rangeStart - point : point - rangeEnd;
    }
}
