class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] split = preorder.split(",");
        Queue<String> queue = new ArrayDeque<>(List.of(split));
        boolean res = isValidPre(queue);
        return res && queue.isEmpty();
    }

    public boolean isValidPre(Queue<String> nodes) {
        if(nodes.isEmpty()) {
            return false;
        }
        else if("#".equals(nodes.poll())) {
            return true;
        }
        boolean res = false;
        res = isValidPre(nodes);
        res = res && isValidPre(nodes);
        return res;
    }
}

// 官方解答
class Solution {
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/yan-zheng-er-cha-shu-de-qian-xu-xu-lie-h-jghn/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。