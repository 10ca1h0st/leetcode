import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baidu01 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int N = Integer.valueOf(line.split("\\s+")[0]);
        int K = Integer.valueOf(line.split("\\s+")[1]);
        int[][] origin = new int[N][N];
        for (int i = 0; i < N; i++) {
            line = bf.readLine();
            String[] split = line.split("\\s+");
            for (int j = 0; j < N; j++) {
                origin[i][j] = Integer.valueOf(split[j]);
            }
        }
        for (int i = 0; i < N; i++) {
            int count = 0;
            while (count < K) {
                for (int j = 0; j < N; j++) {
                    int count2 = 0;
                    while (count2 < K) {
                        System.out.print(origin[i][j]);
                        if (j < N - 1 || count2 < K - 1) {
                            System.out.print(" ");
                        }
                        count2++;
                    }
                }
                if (i < N - 1 || count < K - 1)
                System.out.println();
                count++;
            }
        }
    }
}
