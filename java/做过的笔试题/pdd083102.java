import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class pdd083102 {
    public static void main(String[] args) throws IOException {
//        String s = "sbcde";
//        System.out.println(getMaxStr(s));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();
        int n = Integer.valueOf(line);
        String[] strs = new String[n];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            line = bf.readLine();
            strs[i] = line;
            map.put(line, i);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String o1min = getMinStr(o1);
                String o2min = getMinStr(o2);
                if (o1min.compareTo(o2min) < 0) {
                    return -1;
                } else if (o1min.compareTo(o2min) > 0) {
                    return 1;
                } else {
                    if (map.get(o1) < map.get(o2)) {
                        return -1;
                    } else if (map.get(o1) > map.get(o2)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
        for(String s : strs) {
            System.out.println(s);
        }
    }

    public static String getMinStr(String s) {
        char[] chs = s.toCharArray();
        for (int i = 0; i < s.length()/2; i++) {
            if (chs[i] > chs[s.length()-1-i]) {
                char temp = chs[i];
                chs[i] = chs[s.length()-1-i];
                chs[s.length()-1-i] = temp;
            }
        }
        return String.valueOf(chs);
    }
}
