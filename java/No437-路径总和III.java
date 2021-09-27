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
    public Map<TreeNode, Map<Integer, Integer>> map;
    public int count;
    public int targetSum;

    public int pathSum(TreeNode root, int targetSum) {
        map = new HashMap<>();
        count = 0;
        this.targetSum = targetSum;
        dfs(root);
        return count;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        int ans = 0;
        count += dp(node, targetSum);
        dfs(node.left);
        dfs(node.right);
    }

    // 以node为根节点的子树，存在的满足条件的路径数
    public int dp(TreeNode node, int t) {
        if (node == null) {
            return 0;
        }
        if (map.containsKey(node)) {
            Map<Integer, Integer> map2 = map.get(node);
            if (map2.containsKey(t)) {
                return map2.get(t);
            }
        }
        int ans = 0;
        if (node.val == t) {
            ans++;
        }
        ans += dp(node.left, t-node.val) + dp(node.right, t-node.val);
        Map<Integer, Integer> map2 = map.getOrDefault(node, new HashMap<>());
        map2.put(t, ans);
        map.put(node, map2);
        return ans;
    }
}