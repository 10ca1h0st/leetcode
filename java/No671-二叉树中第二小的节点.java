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
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        Collections.sort(list);
        int ans = -1;
        for (int i = 1; i < list.size(); i++) {
            // list.get(i) != list.get(i-1)会出错，因为是Integer对象进行==判断，所以是比较对象
            if (!list.get(i).equals(list.get(i-1))) {
                ans = list.get(i);
                break;
            }
        }
        return ans;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        } else {
            list.add(root.val);
        }
    }
}