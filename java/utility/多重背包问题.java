/*
有 N 种物品和一个容量是 V 的背包。

第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。

求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
输出最大价值。

输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。

接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int N = Integer.valueOf(line.split("\\s+")[0]);
        int V = Integer.valueOf(line.split("\\s+")[1]);
        int[] weight = new int[N];
        int[] value = new int[N];
        int[] count = new int[N];
        for (int i = 0; i < N; i++) {
            line = bf.readLine();
            String[] split = line.split("\\s+");
            weight[i] = Integer.valueOf(split[0]);
            value[i] = Integer.valueOf(split[1]);
            count[i] = Integer.valueOf(split[2]);
        }
        int[][] dp = new int[N+1][V+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 0; k <= Math.min(count[i-1], j/weight[i-1]); k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-k*weight[i-1]] + k*value[i-1]);
                }
            }
        }
        System.out.println(dp[N][V]);
    }
}
