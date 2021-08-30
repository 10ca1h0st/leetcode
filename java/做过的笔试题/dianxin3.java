import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dianxin3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int capacity = Integer.valueOf(line);
        line = bf.readLine();
        String[] split = line.split("\\s+");
        int n = Integer.valueOf(split[0]);
        int[] values = new int[n];
        for (int i = 1; i <= n; i++) {
            values[i-1] = Integer.valueOf(split[i]);
        }
        line = bf.readLine();
        split = line.split("\\s+");
        int[] weights = new int[n];
        for (int i = 1; i <= n; i++) {
            weights[i-1] = Integer.valueOf(split[i]);
        }
        int[][] dp = new int[capacity+1][n+1];
        for (int i = 1; i <= capacity; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j-1];
                if (i >= weights[j-1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-weights[j-1]][j-1] + values[j-1]);
                }
            }
        }
        System.out.println(dp[capacity][n]);
    }
}
