/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Initialize result array: [minDistance, maxDistance]
        // Use a large initial value for minDistance to find minimum
        int[] result = {Integer.MAX_VALUE, 0};
      
        // Track the index of first and last critical points found
        int firstCriticalIndex = -1;
        int lastCriticalIndex = -1;
      
        // Traverse the linked list starting from index 0
        // We need at least 3 nodes to check for critical points
        int currentIndex = 0;
        while (head.next != null && head.next.next != null) {
            // Get values of three consecutive nodes
            int previousValue = head.val;
            int currentValue = head.next.val;
            int nextValue = head.next.next.val;
          
            // Check if current node is a critical point
            // A critical point is either a local minimum or local maximum
            boolean isLocalMinimum = currentValue < previousValue && currentValue < nextValue;
            boolean isLocalMaximum = currentValue > previousValue && currentValue > nextValue;
          
            if (isLocalMinimum || isLocalMaximum) {
                // If this is the first critical point found
                if (lastCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                    lastCriticalIndex = currentIndex;
                } else {
                    // Update minimum distance between consecutive critical points
                    result[0] = Math.min(result[0], currentIndex - lastCriticalIndex);
                  
                    // Update the last critical point index
                    lastCriticalIndex = currentIndex;
                  
                    // Update maximum distance (between first and last critical points)
                    result[1] = Math.max(result[1], lastCriticalIndex - firstCriticalIndex);
                }
            }
          
            // Move to the next node
            head = head.next;
            currentIndex++;
        }
      
        // If less than 2 critical points were found, return [-1, -1]
        if (firstCriticalIndex == lastCriticalIndex) {
            return new int[] {-1, -1};
        }
      
        return result;
    }
}
