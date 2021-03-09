import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 树的序列化和反序列化 {
    static StringBuilder str;

    static final String NULL = "#";
    static final String SEP = ",";

    public String serialize(TreeNode root) {
        str = new StringBuilder();
        helpSerializeSeq(root);
        return str.toString();
    }

//    层序遍历
    public static void helpSerializeSeq(TreeNode node) {
        if(node == null) {
            str.append(NULL).append(SEP);
            return;
        }
//        这里队列不能使用ArratDeque，因为ArrayDeque不允许添加null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            TreeNode first = queue.poll();
            if(first == null) {
                str.append(NULL).append(SEP);
                continue;
            }
            str.append(first.val).append(SEP);
            queue.offer(first.left);
            queue.offer(first.right);
        }
        return;
    }

//    后序
    public static void helpSerializePost(TreeNode node) {
        if(node == null) {
            str.append(NULL).append(SEP);
            return;
        }
        helpSerializePost(node.left);
        helpSerializePost(node.right);
        str.append(node.val).append(SEP);
    }

//    前序
    public static void helpSerializePre(TreeNode node) {
        if(node == null) {
            str.append(NULL + SEP);
            return;
        }
        str.append(node.val);
        str.append(SEP);
        helpSerializePre(node.left);
        helpSerializePre(node.right);
    }

    public TreeNode deserialize(String data) {
        String[] nodes = data.split(SEP);
        LinkedList<String> list = new LinkedList<>(List.of(nodes));
        return helpDeserializeSeq(list);
    }

//    层序遍历
    public static TreeNode helpDeserializeSeq(LinkedList<String> nodes) {
        String rootV = nodes.poll();
        if(NULL.equals(rootV)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(rootV));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!nodes.isEmpty()) {
            TreeNode first = queue.poll();
            String left = nodes.removeFirst();
            String right = nodes.removeFirst();
            if(NULL.equals(left)) {
                first.left = null;
            }
            else {
                TreeNode leftNode = new TreeNode(Integer.valueOf(left));
                first.left = leftNode;
                queue.offer(leftNode);
            }
            if(NULL.equals(right)) {
                first.right = null;
            }
            else {
                TreeNode rightNode = new TreeNode(Integer.valueOf(right));
                first.right = rightNode;
                queue.offer(rightNode);
            }
        }
        return root;
    }

//    后序
    public static TreeNode helpDeserializePost(LinkedList<String> nodes) {
        String v = nodes.removeLast();
        if(NULL.equals(v)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(v));
        node.right = helpDeserializePost(nodes);
        node.left = helpDeserializePost(nodes);
        return node;
    }

//    前序
    public static TreeNode helpDesrializePre(LinkedList<String> nodes) {
//        if(nodes.isEmpty()) {
//            return null;
//        }
        String v = nodes.removeFirst();
//        注意，比较字符串相等，不能用==
        if(NULL.equals(v)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(v));
        node.left = helpDesrializePre(nodes);
        node.right = helpDesrializePre(nodes);
        return node;
    }
}
