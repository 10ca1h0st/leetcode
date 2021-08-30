import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
<div class="subject-question">输入<span>n</span>条线段，线段可以用<span>(x, y) </span>来表示，<span>x</span>是起点，<span>y</span>是终点。<span>x, y</span>均为整数且满足<span>0&lt;x&lt;y&lt;1,000,000</span>。要求计算这些线段所覆盖的不重合区间的总长度。</div>
 */

public class dianxin01 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int n  = Integer.valueOf(line);
        int[][] ranges = new int[n][2];
        int i = 0;
        while (i < n) {
            line = bf.readLine();
            String[] split = line.split(",");
            ranges[i][0] = Integer.valueOf(split[0]);
            ranges[i][1] = Integer.valueOf(split[1]);
            i++;
        }
        Arrays.sort(ranges, new Comparator<int[]>() {
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
        // 无重复区间起点
        int start = 0;
        // 无重复区间终点
        int end = 0;
        int ans = 0;
        for (int[] cur : ranges) {
            if (start == 0) {
                start = cur[0];
                end = cur[1];
            } else {
                if (cur[0] < start) {
                    if (cur[1] <= start) {
                        continue;
                    } else if (cur[1] <= end) {
                        start = cur[1];
                    } else if (cur[1] > end) {
                        start = end;
                        end = cur[1];
                    }
                }
                else if (cur[0] > end) {
                    ans += end - start;
                    start = cur[0];
                    end = cur[1];
                } else if (cur[1] <= end) {
                    ans += cur[0] - start;
                    start = cur[1];
                    ///
                } else if (cur[1] > end) {
                    ans += cur[0] - start;
                    start = end;
                    end = cur[1];
                }
            }
        }
        ans += end - start;
        System.out.println(ans);
    }
}
