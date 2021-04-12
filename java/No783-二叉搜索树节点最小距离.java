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
    public static int MAX = 100001;

    public int minDiffInBST(TreeNode root) {
        if(root == null) {
            return MAX;
        }
        int ans = MAX;
        int maxInleft = getMaxInLeft(root);
        if(maxInleft != -1) {
            ans = Math.min(ans, root.val - maxInleft);
        }
        int minInRight = getMinInRight(root);
        if(minInRight != -1) {
            ans = Math.min(ans, minInRight - root.val);
        }
        int ans1 = minDiffInBST(root.left);
        int ans2 = minDiffInBST(root.right);
        ans = Math.min(ans, ans1);
        ans = Math.min(ans, ans2);
        return ans;
    }

    // 左子树中的最右节点
    public int getMaxInLeft(TreeNode root) {
        if(root.left == null) {
            return -1;
        }
        TreeNode node = root.left;
        int ans = node.val;
        while(node != null) {
            ans = node.val;
            node = node.right;
        }
        return ans;
    }

    public int getMinInRight(TreeNode root) {
        if(root.right == null) {
            return -1;
        }
        TreeNode node = root.right;
        int ans = node.val;
        while(node != null) {
            ans = node.val;
            node = node.left;
        }
        return ans;
    }
}