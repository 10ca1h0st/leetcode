class Solution {
    public String reverseParentheses(String s) {
        String leftBracket = "(";
        Deque<String> stack = new ArrayDeque<>();
        for(int i = 0; i < s.length(); ) {
            if(s.charAt(i) == '(') {
                stack.push(leftBracket);
                i++;
            }
            else if(s.charAt(i) == ')') {
                // pop
                StringBuilder builder = new StringBuilder();
                while(!stack.isEmpty() && !stack.peek().equals(leftBracket)) {
                    builder.insert(0, stack.pop());
                }
                // 弹出一个'('
                stack.pop();
                String ans = reverse(builder.toString());
                stack.push(ans);
                i++;
            }
            else {
                int start = i;
                while(i < s.length() && s.charAt(i) != '(' && s.charAt(i) != ')') {
                    i++;
                }
                String sub = s.substring(start, i);
                stack.push(sub);
            }
        }
        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) {
            builder.insert(0, stack.pop());
        }
        return builder.toString();
    }

    public String reverse(String s) {
        StringBuilder builder = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--) {
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }
}