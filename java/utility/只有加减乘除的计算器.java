import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        String s = "10+11-2+4/2+3";
        System.out.println(calc(s));
    }

    public static int calc(String s) {
        char add = '+';
        char minus = '-';
        char multiply = '*';
        char div = '/';
        char[] chs = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char op = add;
        for (int i = 0; i < chs.length; i++) {
            char cur = chs[i];
            if (cur >= '0' && cur <= '9') {
                num = num * 10 + cur-'0';
            }
            if ((cur < '0' || cur > '9') || i == chs.length-1) {
                if (op == add) {
                    stack.push(num);
                } else if (op == minus) {
                    stack.push(0-num);
                } else if (op == multiply) {
                    int top = stack.pop();
                    stack.push(top * num);
                } else if (op == div) {
                    int top = stack.pop();
                    stack.push(top/num);
                }
                num = 0;
                op = cur;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}
