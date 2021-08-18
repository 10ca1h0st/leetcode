import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class pdd_7_25_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        int N = Integer.valueOf(line);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    if (o1[1] < o2[1]) {
                        return -1;
                    } else if (o1[1] > o2[1]) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
        int n = N;
        while (n > 0) {
            line = bf.readLine();
            int start = Integer.valueOf(line.split(" ")[0]);
            int end = Integer.valueOf(line.split(" ")[1]);
            int[] range = new int[] {start, end};
            pq.offer(range);
            n--;
        }
        int[] prev = pq.poll();
        int[] cur = null;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (contain(prev, cur)) {
                System.out.println("true");
                return;
            }
            if (cur[1] > prev[1]) {
                prev = cur;
            }
        }
        System.out.println("false");
    }

    public static boolean contain(int[] r1, int[] r2) {
        if (r1[1]-r1[0] < r2[1]-r2[0]) {
            return false;
        }
        if (r1[0] <= r2[0] && r1[1] >= r2[1]) {
            return true;
        }
        return false;
    }
}
