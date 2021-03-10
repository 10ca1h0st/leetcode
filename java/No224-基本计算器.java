class Solution {

    static final String SPACE = " ";
    static final String leftBracket = "(";
    static final String rightBracket = ")";
    static final String ADD = "+";
    static final String MINUS = "-";

    public int calculate(String s) {
        Deque<String> stack = new ArrayDeque<>();
        char[] chs = s.toCharArray();
        for(int i = 0; i < chs.length;) {
            if(chs[i] == SPACE.charAt(0)) {
                i++;
                continue;
            }
            if(chs[i] == leftBracket.charAt(0)) {
                stack.push(leftBracket);
                i++;
                continue;
            }
            if(chs[i] == ADD.charAt(0)) {
                stack.push(ADD);
                i++;
                continue;
            }
            if(chs[i] == MINUS.charAt(0)) {
                stack.push(MINUS);
                i++;
                continue;
            }
            if(chs[i] != rightBracket.charAt(0)) {
                // 数字
                StringBuilder sb = new StringBuilder();
                while(i < chs.length && chs[i] >= '0' && chs[i] <= '9') {
                    sb.append(chs[i]);
                    i++;
                }
                stack.push(sb.toString());
                continue;
            }
            // 只能为闭括号
            int value = 0;
            int cur = 0;
            boolean add = true;
            String top = stack.pop();
            while(!leftBracket.equals(top)) {
                if(ADD.equals(top) || MINUS.equals(top)) {
                    add = ADD.equals(top)?true:false;
                    if(add) {
                        value += cur;
                    }
                    else {
                        value -= cur;
                    }
                }
                else {
                    cur = Integer.valueOf(top);
                }
                top = stack.pop();
            }
            value += cur;
            stack.push(String.valueOf(value));
            i++;
        }
        int ret = 0;
        int cur = 0;
        boolean add = true;
        String top = null;
        while(!stack.isEmpty()) {
            top = stack.pop();
            if(ADD.equals(top) || MINUS.equals(top)) {
                add = ADD.equals(top)?true:false;
                if(add) {
                    ret += cur;
                }
                else {
                    ret -= cur;
                }
                // if(add) {
                //     ret += cur;
                // }
                // else {
                //     ret -= cur;
                // }
                // add = ADD.equals(top)?true:false;
            }
            else {
                cur = Integer.valueOf(top);
            }
        }
        if(ADD.equals(top) || MINUS.equals(top)) {
            return ret;
        }
        ret += cur;
        return ret;
    }
}