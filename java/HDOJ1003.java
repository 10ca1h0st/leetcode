import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HDOJ1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.valueOf(bf.readLine().trim());
        int i = 1;
        int[] arr = null;
        while(true) {
            String[] line = bf.readLine().trim().split(" ");
            arr = new int[line.length - 1];
            for(int index = 1; index < line.length; index++) {
                arr[index-1] = Integer.valueOf(line[index]);
            }
            int[] res = maxSubSequence(arr);
            System.out.println("Case " + i + ":");
            System.out.println(res[0] + " " + res[1] + " " + res[2]);
            i++;
            if(i > cases) {
                break;
            }
            System.out.println();
        }
    }

    public static int[] maxSubSequence(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int[] res = new int[3];
        int start = 0;
        for(int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(sum > max) {
                max = sum;
                res[0] = max;
                res[1] = start + 1;
                res[2] = i + 1;
            }
            if(sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }
        return res;
    }
}
