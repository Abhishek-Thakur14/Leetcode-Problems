/**
 * Node class representing a segment in the segment tree
 * Each node stores information about the longest repeating substring in its range
 */
class Node {
    int left, right;           // Range boundaries [left, right]
    int leftMax, rightMax;      // Maximum repeating length starting from left/ending at right
    int maxLength;              // Maximum repeating substring length in this range
  
    Node(int left, int right) {
        this.left = left;
        this.right = right;
        this.leftMax = 1;      // Initially, each position has at least 1 character
        this.rightMax = 1;
        this.maxLength = 1;
    }
}

/**
 * Segment Tree for efficiently querying and updating longest repeating substrings
 */
class SegmentTree {
    private char[] characters;  // The character array (1-indexed internally)
    private Node[] tree;        // Segment tree nodes
  
    /**
     * Constructor initializes the segment tree with the given string
     * @param s Character array to build the tree from
     */
    public SegmentTree(char[] s) {
        int n = s.length;
        this.characters = s;
        this.tree = new Node[n << 2];  // Allocate 4n space for the tree
        build(1, 1, n);                // Build tree with 1-based indexing
    }
  
    /**
     * Recursively builds the segment tree
     * @param nodeIndex Current node index in the tree
     * @param rangeLeft Left boundary of current range
     * @param rangeRight Right boundary of current range
     */
    public void build(int nodeIndex, int rangeLeft, int rangeRight) {
        tree[nodeIndex] = new Node(rangeLeft, rangeRight);
      
        // Base case: leaf node
        if (rangeLeft == rangeRight) {
            return;
        }
      
        // Recursive case: build left and right subtrees
        int mid = (rangeLeft + rangeRight) >> 1;
        int leftChild = nodeIndex << 1;
        int rightChild = nodeIndex << 1 | 1;
      
        build(leftChild, rangeLeft, mid);
        build(rightChild, mid + 1, rangeRight);
      
        // Update current node based on children
        pushup(nodeIndex);
    }
  
    /**
     * Modifies a character at the given position
     * @param nodeIndex Current node index in the tree
     * @param position Position to modify (1-indexed)
     * @param newChar New character value
     */
    public void modify(int nodeIndex, int position, char newChar) {
        Node currentNode = tree[nodeIndex];
      
        // Base case: reached the target leaf node
        if (currentNode.left == position && currentNode.right == position) {
            characters[position - 1] = newChar;  // Convert to 0-indexed
            return;
        }
      
        // Recursive case: traverse to the appropriate child
        int mid = (currentNode.left + currentNode.right) >> 1;
        int leftChild = nodeIndex << 1;
        int rightChild = nodeIndex << 1 | 1;
      
        if (position <= mid) {
            modify(leftChild, position, newChar);
        } else {
            modify(rightChild, position, newChar);
        }
      
        // Update current node after modification
        pushup(nodeIndex);
    }
  
    /**
     * Queries the maximum repeating substring length in the given range
     * @param nodeIndex Current node index in the tree
     * @param queryLeft Left boundary of query range
     * @param queryRight Right boundary of query range
     * @return Maximum repeating substring length in the range
     */
    public int query(int nodeIndex, int queryLeft, int queryRight) {
        Node currentNode = tree[nodeIndex];
      
        // Current node's range is completely within query range
        if (currentNode.left >= queryLeft && currentNode.right <= queryRight) {
            return currentNode.maxLength;
        }
      
        int mid = (currentNode.left + currentNode.right) >> 1;
        int result = 0;
        int leftChild = nodeIndex << 1;
        int rightChild = nodeIndex << 1 | 1;
      
        // Query left subtree if needed
        if (queryRight <= mid) {
            result = query(leftChild, queryLeft, queryRight);
        }
      
        // Query right subtree if needed
        if (queryLeft > mid) {
            result = Math.max(result, query(rightChild, queryLeft, queryRight));
        }
      
        return result;
    }
  
    /**
     * Updates parent node based on its children nodes
     * Merges information from left and right children
     * @param nodeIndex Index of the node to update
     */
    private void pushup(int nodeIndex) {
        Node parent = tree[nodeIndex];
        Node leftChild = tree[nodeIndex << 1];
        Node rightChild = tree[nodeIndex << 1 | 1];
      
        // Initial max is the maximum of both children
        parent.maxLength = Math.max(leftChild.maxLength, rightChild.maxLength);
      
        // Initialize parent's left and right max from children
        parent.leftMax = leftChild.leftMax;
        parent.rightMax = rightChild.rightMax;
      
        // Calculate the full length of left and right ranges
        int leftRangeLength = leftChild.right - leftChild.left + 1;
        int rightRangeLength = rightChild.right - rightChild.left + 1;
      
        // Check if we can merge across the boundary
        // If the last character of left child equals first character of right child
        if (characters[leftChild.right - 1] == characters[rightChild.left - 1]) {
            // If left child is all same characters, extend parent's leftMax
            if (leftChild.leftMax == leftRangeLength) {
                parent.leftMax += rightChild.leftMax;
            }
          
            // If right child is all same characters, extend parent's rightMax
            if (rightChild.rightMax == rightRangeLength) {
                parent.rightMax += leftChild.rightMax;
            }
          
            // Update max length considering the merge at boundary
            parent.maxLength = Math.max(parent.maxLength, 
                                       leftChild.rightMax + rightChild.leftMax);
        }
    }
}

/**
 * Solution class for the longest repeating substring problem with updates
 */
class Solution {
    /**
     * Processes queries to find longest repeating substring after each character update
     * @param s Original string
     * @param queryCharacters String of characters to update
     * @param queryIndices Array of indices where updates occur
     * @return Array of maximum repeating substring lengths after each update
     */
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        // Initialize segment tree with the string
        SegmentTree segmentTree = new SegmentTree(s.toCharArray());
      
        int numQueries = queryIndices.length;
        int[] results = new int[numQueries];
        int stringLength = s.length();
      
        // Process each query
        for (int i = 0; i < numQueries; i++) {
            // Convert to 1-indexed position for the segment tree
            int updatePosition = queryIndices[i] + 1;
            char newCharacter = queryCharacters.charAt(i);
          
            // Update the character at the specified position
            segmentTree.modify(1, updatePosition, newCharacter);
          
            // Query the entire string for the maximum repeating substring
            results[i] = segmentTree.query(1, 1, stringLength);
        }
      
        return results;
    }
}
