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
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode top = null;
        TreeNode lastPop = root;
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        while (!stack.isEmpty()) {
            top = stack.peek();
            if (top.left != null && top.left != lastPop && top.right != lastPop) {
                stack.push(top.left);
                ans.add(top.left.val);
            } else if (top.right != null && top.right != lastPop) {
                stack.push(top.right);
                ans.add(top.right.val);
            } else {
                lastPop = top;
                stack.pop();
            }
        }
        return ans;
    }
}