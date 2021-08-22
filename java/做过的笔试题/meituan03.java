import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class meituan03 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        final int MOD = (int) (Math.pow(10, 9)) + 7;
        int ans = backtrace(line, 0, line.length()-1, MOD);
        System.out.println(ans);
    }

    private static int backtrace(String s, int start, int end, int MOD) {
        if (end <= start) {
            return 1;
        }
        if (end - start == 1) {
            return 2;
        }
        List<Integer> pos = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                int leftPos = stack.pop();
                if (stack.isEmpty()) {
                    pos.add(leftPos);
                    pos.add(i);
                }
            }
        }
        if (pos.size() == 2) {
            return backtrace(s, start+1, end-1, MOD) + 1;
        }
        int value = 1;
        for (int i = 0; i < pos.size(); ) {
            int res = backtrace(s, pos.get(i), pos.get(i+1), MOD);
            value = (int)(((long) (value * res)) % MOD);
            i+=2;
        }
        return value;
    }
}