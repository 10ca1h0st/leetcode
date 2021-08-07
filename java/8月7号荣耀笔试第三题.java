import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class rongyao3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());
        int[][] rectangles = new int[n][2];
        PriorityQueue<int[]> pq1 = new PriorityQueue<>(new Comparator<int[]>() {
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
        String line = bf.readLine();
        String[] split = line.split(" ");
        int index = 0;
        for (int i = 0; i < split.length; i+=2) {
            rectangles[index][0] = Integer.valueOf(split[i]);
            int L = Integer.valueOf(split[i]);
            rectangles[index][1] = Integer.valueOf(split[i + 1]);
            int W = Integer.valueOf(split[i + 1]);
            pq1.offer(new int[] {L, W});
            index++;
        }
        int ans = 0;
        while (!pq1.isEmpty()) {
            PriorityQueue<int[]> backup = new PriorityQueue<>(new Comparator<int[]>() {
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
            int[] cur = null;
            int[] pre = pq1.poll();
            while (!pq1.isEmpty()) {
                cur = pq1.poll();
                if (!beNext(pre, cur)) {
                    backup.offer(cur);
                } else {
                    pre = cur;
                }
            }
            pq1 = backup;
            ans++;
        }
        System.out.println(ans);
    }

    private static boolean beNext(int[] pre, int[] post) {
        if (post[0] >= pre[0] && post[1] >= pre[1]) {
            return true;
        }
        return false;
    }
}
