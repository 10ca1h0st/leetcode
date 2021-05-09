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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
       Queue<Integer> queue = new ArrayDeque<>();
       dfs1(root1, queue);
       boolean ans = dfs2(root2, queue);
       return ans && queue.isEmpty();
    }

    public void dfs1(TreeNode root, Queue<Integer> queue) {
        if(root.left == null && root.right == null) {
            queue.offer(root.val);
        }
        else {
            if(root.left != null) {
                dfs1(root.left, queue);
            }
            if(root.right != null) {
                dfs1(root.right, queue);
            }
        }
    }

    public boolean dfs2(TreeNode root, Queue<Integer> queue) {
        if(root.left == null && root.right == null) {
            if(queue.isEmpty()) {
                return false;
            }
            if(queue.poll() != root.val) {
                return false;
            }
            return true;
        }
        else {
            boolean res = true;
            if(root.left != null) {
                res = dfs2(root.left, queue);
            }
            if(!res) {
                return false;
            }
            if(root.right != null) {
                res = dfs2(root.right, queue);
            }
            return res;
        }
    }
}