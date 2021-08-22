import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class meituan02 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        String s= line;
        line = bf.readLine();
        int count = Integer.valueOf(line);
        Map<Character, List<Integer>> positions = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            List<Integer> v = positions.getOrDefault(s.charAt(i), new ArrayList<>());
            v.add(i);
            positions.put(s.charAt(i), v);
        }
        int insertPos = s.length();
        StringBuilder sb = new StringBuilder(s);
        while (count > 0) {
            line = bf.readLine();
            count--;
            String[] split = line.split("\\s+");
            int op = Integer.valueOf(split[0]);
            if (op == 2) {
                // 查询
                int param = Integer.valueOf(split[1]);
                char key = sb.charAt(param-1);
                List<Integer> v = positions.get(key);
                if (v.size() == 1) {
                    System.out.println(-1);
                    continue;
                }
                int ans = sb.length()+1;
                for (int i = 0; i < v.size(); i++) {
                    if (v.get(i) == param-1) {
                        if (i > 0) {
                            ans = Math.min(ans, v.get(i)-v.get(i-1));
                        }
                        if (i < v.size()-1) {
                            ans = Math.min(ans, v.get(i+1)-v.get(i));
                        }
                        break;
                    }
                }
                System.out.println(ans);
            } else if (op == 1) {
                // 添加
                char param = split[1].charAt(0);
                List<Integer> v = positions.getOrDefault(param, new ArrayList<>());
                v.add(insertPos);
                insertPos++;
                positions.put(param, v);
                sb.append(param);
            }
        }
    }
}
