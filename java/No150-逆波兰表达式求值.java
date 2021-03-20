class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < tokens.length; i++) {
            if(isOperation(tokens[i])) {
                int n1 = stack.pop();
                int n2 = stack.pop();
                if(tokens[i].charAt(0) == '+') {
                    stack.push(n1+n2);
                }
                else if(tokens[i].charAt(0) == '-') {
                    stack.push(n2-n1);
                }
                else if(tokens[i].charAt(0) == '*') {
                    stack.push(n1*n2);
                }
                else if(tokens[i].charAt(0) == '/') {
                    stack.push(n2/n1);
                }
            }
            else {
                // 数字
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    public boolean isOperation(String s) {
        if(s.length() == 1 && (s.charAt(0) < '0' || s.charAt(0) > '9')) {
            return true;
        }
        return false;
    }
}