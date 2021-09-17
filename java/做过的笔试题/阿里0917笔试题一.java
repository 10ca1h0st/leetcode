import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        String[] split = line.split("\\s+");
        int n = Integer.valueOf(split[0]);
        int k = Integer.valueOf(split[1]);
        int[] nums = new int[n];
        line = bf.readLine();
        split = line.split("\\s+");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(split[i]);
        }
        int ans = 0;
        int[][] dp = new int[n][k+1];
        for (int i = 0; i < n; i++) {
            dp[i][1] = 1;
            ans++;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 2; j <= k; j++) {
                if (i+1 < j) {
                    break;
                }
                for (int p = i-1; p >= 0; p--) {
                    if (nums[p] < nums[i] && dp[p][j-1] > 0) {
                        dp[i][j] += dp[p][j-1];
                    }
                }
                ans += dp[i][j];
            }
//            ans += dp[i][k];
        }
        System.out.println(ans);
    }
}