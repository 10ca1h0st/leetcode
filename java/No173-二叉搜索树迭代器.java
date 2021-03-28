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

// 中序遍历使用栈的写法
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
 
class BSTIterator {
    private Queue<Integer> inOrder;

    public BSTIterator(TreeNode root) {
        inOrder = new ArrayDeque<>();
        dfs(root);
    }

    private void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.left);
        inOrder.offer(root.val);
        dfs(root.right);
    }
    
    public int next() {
        return inOrder.poll();
    }
    
    public boolean hasNext() {
        if(inOrder.isEmpty()) {
            return false;
        }
        return true;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */