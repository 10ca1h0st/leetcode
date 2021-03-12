class Solution {
    public String removeDuplicates(String s, int k) {
        Deque<Node> stack = new ArrayDeque<>();
        char prev = 0;
        for(int i = 0; i < s.length(); i++) {
            if(stack.isEmpty()) {
                stack.push(new Node(s.charAt(i), 1));
                prev = s.charAt(i);
            }
            else if(prev == s.charAt(i)) {
                int order = stack.peek().order + 1;
                if(order == k) {
                    order -= 1;
                    while(order-- > 0) {
                        stack.pop();
                    }
                    if(!stack.isEmpty()) {
                        prev = stack.peek().val;
                    }
                }
                else {
                    stack.push(new Node(s.charAt(i), order));
                }
            }
            else {
                stack.push(new Node(s.charAt(i), 1));
                prev = s.charAt(i);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        while(!stack.isEmpty()) {
            sb.append(stack.pop().val);
        }
        return sb.reverse().toString();
    }

    class Node {
        char val;
        int order;
        Node(char val, int order) {
            this.val = val;
            this.order = order;
        }
    }
}