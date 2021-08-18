import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class huawei081801 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        int money = Integer.valueOf(line.split(" ")[0]);
        int N = Integer.valueOf(line.split(" ")[1]);
        int _N = N;
        int[] price = new int[N];
        int[] count =  new int[N];
        int[] love = new int[N];
        int index = 0;
        while (_N > 0) {
            line = bf.readLine();
            price[index] = Integer.valueOf(line.split("\\s+")[0]);
            count[index] = Integer.valueOf(line.split("\\s+")[1]);
            love[index] = Integer.valueOf(line.split("\\s+")[2]);
            index++;
            _N--;
        }
        int[][] dp = new int[N+1][money+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= money; j++) {
                for (int k = 0; k <= count[i-1]; k++) {
                    if (j-k*price[i-1] < 0) {
                        break;
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-k*price[i-1]] + k*love[i-1]);
                }
            }
        }
        System.out.println(dp[N][money]);
    }
}
