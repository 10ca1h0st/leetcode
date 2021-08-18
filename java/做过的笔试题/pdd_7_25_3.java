import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class pdd_7_25_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        int N = Integer.valueOf(line);
        while (N > 0) {
            line = bf.readLine();
            int A = Integer.valueOf(line.split(" ")[0]);
            int B = Integer.valueOf(line.split(" ")[1]);
            int C = Integer.valueOf(line.split(" ")[2]);
            int Q = Integer.valueOf(line.split(" ")[3]);
            if (canGen(A, B, C, Q)) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
            N--;
        }
    }

    public static boolean canGen(int A, int B, int C, int Q) {
        boolean[] dp = new boolean[Q+1];
        dp[A] = true;
        int minPossible = Math.min(A+B, A*C);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(Math.max(A+B, A*C));
        for (int i = minPossible; i <= Q; ) {
            if (i-B >= A) {
                dp[i] = dp[i] || dp[i-B];
            }
            if (i%C == 0 && i/C >= A) {
                dp[i] = dp[i] || dp[i / C];
            }
            i++;
        }
        return dp[Q];
//        if ((Q-A)%B == 0) {
//            return true;
//        }
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(A);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size > 0) {
//                int top = queue.poll();
//                if (top == Q) {
//                    return true;
//                }
//                if ((long)(top+B) == Q) {
//                    return true;
//                } else if ((long)(top+B) < Q) {
//                    queue.offer(top+B);
//                }
//                if ((long)(top*C) == Q) {
//                    return true;
//                } else if ((long)(top*C) < Q) {
//                    queue.offer(top*C);
//                }
//                size--;
//            }
//        }
//        return false;
    }
}
