import java.util.*;

class Solution {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> nums = new ArrayList<>();

        // Step 1: Inorder traversal to get sorted values
        inorder(root, nums);

        // Step 2: Build balanced BST
        return build(nums, 0, nums.size() - 1);
    }

    // Inorder traversal
    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;

        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    // Build balanced BST from sorted list
    private TreeNode build(List<Integer> nums, int l, int r) {
        if (l > r) return null;

        int m = l + (r - l) / 2;

        TreeNode root = new TreeNode(nums.get(m));
        root.left = build(nums, l, m - 1);
        root.right = build(nums, m + 1, r);

        return root;
    }
}
