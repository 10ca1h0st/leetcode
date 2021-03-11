class Solution {
    public int calculate(String s) {
        // 先变为逆波兰表达式
        // 处理-1+2这种情况
        if(s.charAt(0) == '-') {
            StringBuilder sb = new StringBuilder(s);
            sb.insert(0, '0');
            s = sb.toString();
        }
        // 处理2*(-1+2)这种情况
        // 用special这个集合记录左括号后面直接跟符号的这种左括号的位置
        Set<Integer> special = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '-' && i > 0 && s.charAt(i-1) == '(') {
                special.add(i-1);
            }
        }
        // 转换为逆波兰
        List<String> list = new ArrayList();
        Deque<Character> stack = new ArrayDeque();
        int num = -1;
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == ' ') {
                continue;
            }
            if(arr[i] >= '0' && arr[i] <= '9') {
                // 数字
                if(num == -1) {
                    num = Integer.valueOf(arr[i]+"");
                }
                else {
                    num = num*10 + Integer.valueOf(arr[i]+"");
                }
            }
            else if(isOperation(arr[i])) {
                // 是加减乘除
                if(num != -1) {
                    list.add(String.valueOf(num));
                    num = -1;
                }
                char curOp = arr[i];
                while(!stack.isEmpty()) {
                    char topOp = stack.peek();
                    if(topOp == '(') {
                        break;
                    }
                    if(!cmpOrder(topOp, curOp)) {
                        // topOp的优先级小于curOp
                        break;
                    }
                    topOp = stack.pop();
                    list.add(String.valueOf(topOp));
                }
                stack.push(curOp);
            }
            else if(arr[i] == '(') {
                if(special.contains(i)) {
                    num = 0;
                }
                stack.push('(');
            }
            else {
                // 右括号
                while(!stack.isEmpty()) {
                    char topOp = stack.pop();
                    if(topOp == '(') {
                        break;
                    }
                    list.add(String.valueOf(topOp));
                }
            }
        }
        list.add(String.valueOf(num));
        while(!stack.isEmpty()) {
            list.add(String.valueOf(stack.pop()));
        }
        // for(String i : list) {
        //     System.out.println(i);
        // }
        // 逆波兰表达式
        return evalRPN(list);
    }

    public int evalRPN(List<String> list) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s : list) {
            if(isOperation(s.charAt(0))) {
                int n1 = stack.pop();
                int n2 = stack.pop();
                int n = 0;
                if(s.charAt(0) == '+') {
                    n = n1 + n2;
                }
                else if(s.charAt(0) == '-') {
                    n = n2 - n1;
                }
                else if(s.charAt(0) == '*') {
                    n = n1 * n2;
                }
                else {
                    n = n2 / n1;
                }
                stack.push(n);
            }
            else {
                // 是数字
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();
    }

    public boolean isOperation(char c) {
        if(c=='+' || c=='-' || c=='*' || c=='/') {
            return true;
        }
        return false;
    }

    // o1的优先级是否大于等于o2的
    public boolean cmpOrder(char o1, char o2) {
        if(o2=='+' || o2=='-') {
            return true;
        }
        if(o1=='*' || o1=='/') {
            return true;
        }
        return false;
    }
}