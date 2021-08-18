import java.util.*;


  class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
      this.val = val;
    }
  }


public class beike04 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param root TreeNode类
     * @return int整型
     */
    // 记录子树的节点个数
    Map<TreeNode, Integer> count;
    // 键为子树的序列化表示，值为子树的根节点
    Set<String> set;
    int ans;
    public int maxSubTree (TreeNode root) {
        // write code here
        count = new HashMap<>();
        set = new HashSet<>();
        ans = 0;
        postOrder(root);
        return ans;
    }

    // 返回以node为子树的节点个数, 以node为子树的字符串表示
    public String[] postOrder(TreeNode node) {
        if (node.left == null && node.right == null) {
            //count.put(node, 1);
            if (set.contains(String.valueOf(node.val))) {
                ans = Math.max(ans, 1);
            } else {
                set.add(String.valueOf(node.val));
            }
            return new String[] {"1", String.valueOf(node.val)+",#,#"};
        }
        else if (node.left == null) {
            String[] res = postOrder(node.right);
            //count.put(node, 1+Integer.parseInt(res[0]));
            String tree = String.valueOf(node.val) + ",#," + res[1];
            if (set.contains(tree)) {
                ans = Math.max(ans, 1+Integer.parseInt(res[0]));
            } else {
                set.add(tree);
            }
            return new String[] {String.valueOf(1+Integer.parseInt(res[0])), tree};
        }
        else if (node.right == null) {
            String[] res = postOrder(node.left);
            //count.put(node, 1+Integer.parseInt(res[0]));
            String tree = String.valueOf(node.val) + "," + res[1] + ",#";
            if (set.contains(tree)) {
                ans = Math.max(ans, 1+Integer.parseInt(res[0]));
            } else {
                set.add(tree);
            }
            return new String[] {String.valueOf(1+Integer.parseInt(res[0])), tree};
        } else {
            String[] res1 = postOrder(node.left);
            String[] res2 = postOrder(node.right);
            //count.put(node, 1+Integer.parseInt(res1[0])+Integer.parseInt(res2[0]));
            String tree = String.valueOf(node.val) + "," + res1[1] + "," + res2[1];
            if (set.contains(tree)) {
                ans = Math.max(ans, 1+Integer.parseInt(res1[0])+Integer.parseInt(res2[0]));
            } else {
                set.add(tree);
            }
            return new String[] {String.valueOf(1+Integer.parseInt(res1[0])+Integer.parseInt(res2[0])), tree};
        }
    }
}
