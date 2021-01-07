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
	//leetcode No.144
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = null;
        TreeNode lastPop = null;

        ret.add(root.val);
        stack.push(root);
        lastPop = root;
        while(!stack.isEmpty()) {
            cur = stack.peek();
            if(cur.left != null && cur.left != lastPop && cur.right != lastPop) {
                stack.push(cur.left);
                ret.add(cur.left.val);
            }
            else if(cur.right != null && cur.right != lastPop) {
                stack.push(cur.right);
                ret.add(cur.right.val);
            }
            else {
                lastPop = stack.peek();
                stack.pop();
            }
        }
        return ret;
    }
	
	// leetcode No.94
	public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = null;
        TreeNode lastPop = null;

        stack.push(root);
        lastPop = root;
        while(!stack.isEmpty()) {
            cur = stack.peek();
            if(cur.left != null && cur.left != lastPop && cur.right != lastPop) {
                stack.push(cur.left);
            }
            else if(cur.right != null && cur.right != lastPop) {
                stack.push(cur.right);
                ret.add(cur.val);
            }
            else {
                lastPop = stack.peek();
                stack.pop();
                if(lastPop.right == null) {
                    ret.add(lastPop.val);
                }
            }
        }
        return ret;
    }
	
	// leetcode No.145
	public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = null;
        TreeNode lastPop = null;

        stack.push(root);
        lastPop = root;
        while(!stack.isEmpty()) {
            cur = stack.peek();
            if(cur.left != null && cur.left != lastPop && cur.right != lastPop) {
                stack.push(cur.left);
            }
            else if(cur.right != null && cur.right != lastPop) {
                stack.push(cur.right);
            }
            else {
                lastPop = stack.peek();
                stack.pop();
                ret.add(lastPop.val);
            }
        }
        return ret;
    }
}