import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class meituan01 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        int n = Integer.valueOf(line);
        int[] nums = new int[n];
        line  = bf.readLine();
        String[] split = line.split("\\s+");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.valueOf(split[i]);
        }
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            List<Integer> res = new ArrayList<>();
            res.add(nums[i]);
            backtrace(nums, visited, res, nums[i], set, ans);
            visited[i] = false;
        }
        System.out.println(ans.size());
        for (List<Integer> res : ans) {
            for (int i = 0; i < res.size()-1; i++) {
                System.out.print(res.get(i)+" ");
            }
            System.out.print(res.get(res.size()-1));
            System.out.println();
        }
    }

    private static void backtrace(int[] nums, boolean[] visited, List<Integer> res, int value, Set<Integer> set, List<List<Integer>> ans) {
        if (res.size() == nums.length) {
            if (!set.contains(value)) {
                set.add(value);
                ans.add(new ArrayList<>(res));
            }
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                res.add(nums[i]);
                int _value = value;
                backtrace(nums, visited, res, value*10+nums[i], set, ans);
                value = _value;
                res.remove(res.size()-1);
                visited[i] = false;
            }
        }
    }
}
