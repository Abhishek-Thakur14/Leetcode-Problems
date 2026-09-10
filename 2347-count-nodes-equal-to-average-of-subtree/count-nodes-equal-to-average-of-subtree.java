/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Counter for nodes whose value equals the average of their subtree
    private int nodeCount;

    /**
     * Counts the number of nodes where the node's value equals 
     * the average value of all nodes in its subtree (including itself).
     * 
     * @param root The root of the binary tree
     * @return The count of nodes meeting the criteria
     */
    public int averageOfSubtree(TreeNode root) {
        nodeCount = 0;
        dfs(root);
        return nodeCount;
    }

    /**
     * Performs depth-first search to calculate sum and count of nodes in each subtree.
     * 
     * @param root The current node being processed
     * @return An array where index 0 contains the sum of values in the subtree,
     *         and index 1 contains the count of nodes in the subtree
     */
    private int[] dfs(TreeNode root) {
        // Base case: null node contributes 0 sum and 0 count
        if (root == null) {
            return new int[]{0, 0};
        }
      
        // Recursively process left subtree
        int[] leftSubtree = dfs(root.left);
      
        // Recursively process right subtree
        int[] rightSubtree = dfs(root.right);
      
        // Calculate total sum of current subtree (left + right + current node)
        int subtreeSum = leftSubtree[0] + rightSubtree[0] + root.val;
      
        // Calculate total count of nodes in current subtree
        int subtreeNodeCount = leftSubtree[1] + rightSubtree[1] + 1;
      
        // Check if average of subtree equals current node's value
        // Using integer division as per problem requirements
        if (subtreeSum / subtreeNodeCount == root.val) {
            nodeCount++;
        }
      
        // Return sum and count for parent node's calculation
        return new int[]{subtreeSum, subtreeNodeCount};
    }
}
