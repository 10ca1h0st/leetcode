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
    private static class Item {
        int val;
        int row;
        int col;

        Item(int v, int r, int c) {
            val = v;
            row = r;
            col = c;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<Item> queue = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                if (i1.col < i2.col) {
                    return -1;
                } else if (i1.col > i2.col) {
                    return 1;
                } else if (i1.row < i2.row) {
                    return -1;
                } else if (i1.row > i2.row) {
                    return 1;
                } else if (i1.val < i2.val) {
                    return -1;
                } else if (i1.val > i2.val) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        bfs(root, 0, 0, queue);
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            Item top = queue.poll();
            int col = top.col;
            res.add(top.val);
            while (!queue.isEmpty() && col == queue.peek().col) {
                res.add(queue.poll().val);
            }
            ans.add(res);
        }
        return ans;
    }

    private void bfs(TreeNode node, int row, int col, PriorityQueue<Item> queue) {
        if (node == null) {
            return;
        }
        queue.offer(new Item(node.val, row, col));
        bfs(node.left, row+1, col-1, queue);
        bfs(node.right, row+1, col+1, queue);
    }
}