import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class meituan04 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        int n = Integer.valueOf(line);
        int[] nums = new int[n];
        line = bf.readLine();
        String[] split = line.split("\\s+");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(split[i]);
        }
        //op==1
        int[] prefixSum = new int[n+1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += nums[i-1];
            prefixSum[i] = sum;
        }
        //op==2
        // valid[i]表示前i个数的有效值
//        int[] valid = new int[n+1];
//        valid[0] = 0;
//        int validSum = 0;
//        for (int i = 1; i <= n; i++) {
//
//        }
        //op==3
        line = bf.readLine();
        int m = Integer.valueOf(line);
        while (m > 0) {
            m--;
            line = bf.readLine();
            split = line.split("\\s+");
            int op = Integer.valueOf(split[0]);
            int L = Integer.valueOf(split[1]);
            int R = Integer.valueOf(split[2]);
            if (op == 1) {
                System.out.println(prefixSum[R] - prefixSum[L-1]);
            } else if (op == 2) {
                int total = prefixSum[R] - prefixSum[L-1];
                int ans = 0;
                for (int i = L-1; i < R; i++) {
                    ans += (int) Math.pow(total-nums[i], 2);
                }
                System.out.println(ans);
            } else if (op == 3) {
                int ans = -1001;
                for (int i = L-1; i < R; i++) {
                    ans = Math.max(ans, nums[i]);
                }
                System.out.println(ans);
            }
        }
    }
}
