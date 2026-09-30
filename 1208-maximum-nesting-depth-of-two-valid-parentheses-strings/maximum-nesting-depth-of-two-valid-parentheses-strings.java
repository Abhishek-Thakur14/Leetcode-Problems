class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int sequenceLength = seq.length();
        int[] groupAssignment = new int[sequenceLength];
      
        // Track the current depth/nesting level of parentheses
        int currentDepth = 0;
      
        for (int index = 0; index < sequenceLength; index++) {
            if (seq.charAt(index) == '(') {
                // For opening parenthesis: assign group based on current depth parity
                // then increment depth
                groupAssignment[index] = currentDepth & 1;  // 0 if depth is even, 1 if odd
                currentDepth++;
            } else {
                // For closing parenthesis: decrement depth first
                // then assign group based on new depth parity
                currentDepth--;
                groupAssignment[index] = currentDepth & 1;  // 0 if depth is even, 1 if odd
            }
        }
      
        return groupAssignment;
    }
}
