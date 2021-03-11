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
        Node[] dp = new Node[arr.length];
        dp[0] = new Node(arr[0], 1, 1);
        for(int i = 1; i < dp.length; i++) {
            if(dp[i-1].v >= 0) {
                dp[i] = new Node(dp[i-1].v+arr[i], dp[i-1].i, i+1);
            }
            else {
                dp[i] = new Node(arr[i], i+1, i+1);
            }
        }
        int[] res = new int[3];
        res[0] = dp[0].v;
        res[1] = dp[0].i;
        res[2] = dp[0].j;
        for(int i = 1; i < dp.length; i++) {
            if(dp[i].v > res[0]) {
                res[0] = dp[i].v;
                res[1] = dp[i].i;
                res[2] = dp[i].j;
            }
        }
        return res;
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

class Node {
    int v;
    int i;
    int j;
    Node(int v, int i, int j) {
        this.v = v;
        this.i = i;
        this.j = j;
    }
}
