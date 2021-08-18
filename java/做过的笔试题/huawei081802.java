import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class huawei081802 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        int X = Integer.valueOf(line.split(" ")[0]);
        int M = Integer.valueOf(line.split(" ")[1]);
        line = bf.readLine();
        String[] split = line.split(" ");
        int[] price = new int[M];
        for (int i = 0; i < M; i++) {
            price[i] = Integer.valueOf(split[i]);
        }
        int[][] dp = new int[M+1][X+1];
        for (int i = 0; i <= M; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= X; j++) {
                if (j >= price[i-1]) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-price[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        if (dp[M][X] == 0) {
            System.out.println(-1);
        }
        System.out.println(dp[M][X]);
    }
}
