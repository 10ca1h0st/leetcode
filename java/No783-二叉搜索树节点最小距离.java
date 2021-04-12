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

// 官方解答 直接使用中序遍历
class Solution {
    int pre;
    int ans;

    public int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/solution/er-cha-sou-suo-shu-jie-dian-zui-xiao-ju-8u87w/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。