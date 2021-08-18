import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class pdd_7_25_1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        String range = null;
        int max = 0;
        char first = '0';
        char second = '0';
        char third = '0';
        char c1 = '0';
        char c2 = '0';
        char c3 = '0';
        for (int i = 0; i < line.length(); ) {
            if (i + 3 > line.length()) {
                break;
            }
            c1 = line.charAt(i);
            if (c1 < first) {
                i += 1;
                continue;
            }
            c2 = line.charAt(i+1);
            if (c1 == first && c2 < second) {
                if (c2 < first) {
                    i += 2;
                } else {
                    i += 1;
                }
                continue;
            }
            c3 = line.charAt(i+2);
            if (c1 == first && c2 == second && c3 < third) {
                if (c2 < first) {
                    if (c3 < first) {
                        i += 3;
                    } else {
                        i += 2;
                    }
                } else {
                    i += 1;
                }
                continue;
            }
            first = c1;
            second = c2;
            third = c3;
            int temp = (first-'0') * 100 + (second-'0') * 10 + third-'0';
            max = Math.max(max, temp);
            if (first == '9' && second == '9' && third == '9') {
                break;
            }
            i += 1;
        }
        System.out.println(max);
    }
}
