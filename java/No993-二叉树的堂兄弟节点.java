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
    int xParent;
    int xDepth;
    int yParent;
    int yDepth;
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root.val == x || root.val == y) {
            return false;
        }
        xParent = 0;
        yParent = 0;
        xDepth = 0;
        yDepth = 0;
        boolean res = false;
        if(root.left != null) {
            res = dfsx(root.left, x, root.val, 1);
        }
        if(!res) {
            dfsx(root.right, x, root.val ,1);
        }
        if(root.left != null) {
            res = dfsy(root.left, y, root.val, 1);
        }
        if(!res) {
            dfsy(root.right, y, root.val ,1);
        }
        if(xDepth == yDepth && xParent != yParent) {
            return true;
        }
        return false;
    }

    public boolean dfsx(TreeNode node, int x, int from, int depth) {
        if(node.val == x) {
            xParent = from;
            xDepth = depth;
            return true;
        }
        if(node.left != null) {
            if(dfsx(node.left, x, node.val, depth+1)) {
                return true;
            }
        }
        if(node.right != null) {
            if(dfsx(node.right, x, node.val, depth+1)) {
                return true;
            }
        }
        return false;
    }

    public boolean dfsy(TreeNode node, int y, int from, int depth) {
        if(node.val == y) {
            yParent = from;
            yDepth = depth;
            return true;
        }
        if(node.left != null) {
            if(dfsy(node.left, y, node.val, depth+1)) {
                return true;
            }
        }
        if(node.right != null) {
            if(dfsy(node.right, y, node.val, depth+1)) {
                return true;
            }
        }
        return false;
    }
}