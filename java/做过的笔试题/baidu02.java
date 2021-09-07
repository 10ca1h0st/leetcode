import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baidu02 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int n = Integer.valueOf(line);
        for (int i = 0; i < n; i++) {
            line = bf.readLine().split("\\s+")[0];
            String ans = f(line);
            if (i < n - 1) {
                System.out.println(ans);
            } else {
                System.out.print(ans);
            }
//            int num = Integer.valueOf(line);
        }

    }

    public static String f(String num) {
        char[] chs = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean alreadyLess = false;
        for (int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            if (ch == '3') {
                sb.append(ch);
            } else if (ch > '3') {
                sb.append('3');
                alreadyLess = true;
            } else if (ch == '0') {
                if (alreadyLess) {
                    sb.append('3');
                    continue;
                }
                StringBuilder sb2 = new StringBuilder();
                char prev = 0;
                while (sb.length() > 0) {
                    prev = sb.charAt(sb.length()-1);
                    sb.deleteCharAt(sb.length()-1);
                    if (prev == '1') {
                        sb2.append('3');
                    } else {
                        sb2.append((char)(prev-1));
                        break;
                    }
                }
                if (sb.length() == 0 && prev == '1') {
                    int j = 0;
                    while (j < sb2.length()-1) {
                        sb.append('3');
                        j++;
                    }
                    sb.append('3');
                } else {
                    sb.append(sb2.reverse().toString());
                    sb.append('3');
                }
                alreadyLess = true;
            } else {
                if (alreadyLess) {
                    sb.append('3');
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
}
