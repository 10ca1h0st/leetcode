/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (k == 0) {
            return List.of(target.val);
        }
        Map<TreeNode, TreeNode> fathers = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        find(root, target, fathers, queue);
        List<Integer> ans = new ArrayList<>();
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        while (k > 0 && !queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode top = queue.poll();
                if (k == 1) {
                    ans.add(top.val);
                }
                if (top.left != null) {
                    if (!visited.contains(top.left)) {
                        queue.offer(top.left);
                    }
                }
                if (top.right != null) {
                    if (!visited.contains(top.right)) {
                        queue.offer(top.right);
                    }
                }
                if (fathers.getOrDefault(top, null) != null) {
                    if (!visited.contains(fathers.get(top))) {
                        queue.offer(fathers.get(top));
                    }
                }
                visited.add(top);
                size--;
            }
            k--;
        }
        return ans;
    }

    private void find(TreeNode node, TreeNode target, Map<TreeNode, TreeNode> fathers, Queue<TreeNode> queue) {
        if (node == null) {
            return;
        }
        if (node == target) {
            if (fathers.getOrDefault(target, null) != null) {
                queue.offer(fathers.get(target));
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            return;
        }
        if (node.left != null) {
            fathers.put(node.left, node);
            find(node.left, target, fathers, queue);
            if (!queue.isEmpty()) {
                return;
            }
        }
        if (node.right != null) {
            fathers.put(node.right, node);
            find(node.right, target, fathers, queue);
            if (!queue.isEmpty()) {
                return;
            }
        }
        return;
    }
}