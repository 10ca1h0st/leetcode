import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HDOJ1004 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcases = Integer.valueOf(bf.readLine());
        while(testcases > 0) {
            HashMap<String, Integer> count = new HashMap<>();
            String maxColor = null;
            int maxColorCount = 0;
            for(int line = 0; line < testcases; line++) {
                String color = bf.readLine().trim();
                int v = count.getOrDefault(color, 0);
                v += 1;
                if(v > maxColorCount) {
                    maxColorCount = v;
                    maxColor = color;
                }
                count.put(color, v);
            }
            System.out.println(maxColor);
            testcases = Integer.valueOf(bf.readLine());;
        }
    }
}
