import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HDOJ2084 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcases = Integer.valueOf(bf.readLine());
        int[][] map = null;
        while(testcases-- > 0) {
            int height = Integer.valueOf(bf.readLine());
            if(height == 1) {
                System.out.println(Integer.valueOf(bf.readLine()));
                continue;
            }
            map = new int[height][height];
            for(int row = 0; row < height; row++) {
                String line = bf.readLine();
                String[] nums = line.split(" ");
                for(int i = 0; i < nums.length; i++) {
                    map[row][i] = Integer.valueOf(nums[i]);
                }
            }
            for(int i = height-2; i >=0; i--) {
                for(int j = 0; j < i+1; j++) {
                    map[i][j] = Math.max(map[i+1][j], map[i+1][j+1]) + map[i][j];
                }
            }
            System.out.println(map[0][0]);
        }
    }
}
