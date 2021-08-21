import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 环形分发糖果_力扣135的变体 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        String[] split = line.split("\\s+");
        int[] ages = new int[split.length];
        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.valueOf(split[i]);
        }
        System.out.println(getPapers(ages));
    }

    public static int getPapers(int[] ages) {
        int n = ages.length;
        if (n == 1) {
            return 1;
        }
        int[] ages1 = new int[n+1];
        int[] res = new int[n+1];
        System.arraycopy(ages, 0, ages1, 0, n);
        ages1[n] = ages[0];
        for (int i = 0; i <= n; i++) {
            if (i > 0 && ages1[i] > ages1[i-1]) {
                res[i] = res[i-1] + 1;
            } else {
                res[i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            ages1[i] = ages[n-1-i];
        }
        ages1[n] = ages[n-1];
        int[] res2 = new int[n+1];
        for (int i = 0; i <= n; i++) {
            if (i > 0 && ages1[i] > ages1[i-1]) {
                res2[i] = res2[i-1] + 1;
            } else {
                res2[i] = 1;
            }
        }
        int ret = 0;
        ret += Math.max(res[n], res2[n-1]);
        ret += Math.max(res[n-1], res2[n]);
        for (int i = 1; i < n-1; i++) {
            ret += Math.max(res[i], res2[n-1-i]);
        }
        return ret;
    }
}
