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
    int sum;

    public int rangeSumBST(TreeNode root, int low, int high) {
        sum = 0;
        traverse(root, low, high);
        return sum;
    }

    public void traverse(TreeNode root, int low, int high) {
        if(root == null) {
            return;
        }
        if(root.val < low) {
            traverse(root.right, low, high);
        }
        else if(root.val > high) {
            traverse(root.left, low, high);
        }
        else {
            sum += root.val;
            traverse(root.left, low, high);
            traverse(root.right, low, high);
        }
    }
}