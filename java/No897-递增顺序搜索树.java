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
    TreeNode ans;
    TreeNode parent;
    public TreeNode increasingBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        inorder(root);
        return ans;
    }

    public void inorder(TreeNode node) {
        if(node.left != null) {
            inorder(node.left);
        }
        if(ans == null) {
            ans = node;
            ans.left = null;
            parent = ans;
        }
        else {
            parent.right = node;
            parent = node;
            parent.left = null;
        }
        if(node.right != null) {
            inorder(node.right);
        }
        return;
    }
}