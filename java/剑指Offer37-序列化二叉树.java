/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) {
            return "N";
        }
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        sb.append(root.val);
        TreeNode last = root;
        TreeNode top = null;
        while(stack.size() != 0) {
            top = stack.peek();
            if(top.left != null && top.left != last &&
            top.right != last) {
                stack.push(top.left);
                sb.append(',');
                sb.append(top.left.val);
            }
            else if(top.right != null && top.right != last) {
                if(top.left == null) {
                    sb.append(',');
                    sb.append('N');
                }
                stack.push(top.right);
                sb.append(',');
                sb.append(top.right.val);
            }
            else {
                // 左右子树都遍历完成
                last = top;
                if(last.left == null && last.right == null) {
                    sb.append(',');
                    sb.append('N');
                    sb.append(',');
                    sb.append('N');
                }
                else if(last.right == null) {
                    sb.append(',');
                    sb.append('N');
                }
                stack.pop();
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("N")) {
            return null;
        }
        String[] nodeVals = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(nodeVals[0]));
        int rightStart = helper(root, 1, nodeVals, true);
        helper(root, rightStart, nodeVals, false);
        return root;
    }

    private int helper(TreeNode parent, int start, String[] nodeVals, boolean left) {
        if(nodeVals[start].equals("N")) {
            if(left) {
                parent.left = null;
            }
            else {
                parent.right = null;
            }
            return start + 1;
        }
        TreeNode node = new TreeNode(Integer.valueOf(nodeVals[start]));
        int rightStart = helper(node, start + 1, nodeVals, true);
        int nextNodeStart = helper(node, rightStart, nodeVals, false);
        if(left) {
            parent.left = node;
        }
        else {
            parent.right = node;
        }
        return nextNodeStart;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));