import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HDOJ1176 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            //        map[i][j]代表第i秒第j个位置饼的个数
            int[][] map = new int[100000 + 1][11];
            //        一共掉了多久的饼
            int maxSecond = 0;
            int lines = Integer.valueOf(bf.readLine().trim());
            if (lines == 0) {
                return;
            }
            while (lines > 0) {
                String[] line = bf.readLine().trim().split(" ");
                //            第几秒
                int time = Integer.valueOf(line[1]);
                maxSecond = Math.max(maxSecond, time);
                //            什么位置
                int position = Integer.valueOf(line[0]);
                map[time][position]++;
                lines--;
            }
            for (int second = maxSecond - 1; second >= 0; second--) {
                for (int position = 0; position <= 10; position++) {
                    if (position == 0) {
                        map[second][0] = max(map[second + 1][0], map[second + 1][1], 0) + map[second][0];
                    } else if (position == 10) {
                        map[second][10] = max(map[second + 1][9], map[second + 1][10], 0) + map[second][10];
                    } else {
                        map[second][position] = max(map[second + 1][position - 1], map[second + 1][position], map[second + 1][position + 1]) + map[second][position];
                    }
                }
            }
            System.out.println(map[0][5]);
        }
    }

    public static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}
