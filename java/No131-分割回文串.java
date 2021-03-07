class Solution {

    static List<List<String>> res;
    static LinkedList<String> r;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        r = new LinkedList<>();
        // 分割方法从分割0次到分割s.length-1次
        int cut = 0;
        int length = s.length();
        if(length == 0) {
            return res;
        }
        if(isPanlidrome(s)) {
            res.add(List.of(s));
        }
        if(length == 1) {
            return res;
        }
        for(cut = 1; cut < length; cut++) {
            cut(s, cut, 0, length-1);
        }
        return res;
    }

    // 从[start. end]还可有times次切割机会，start <= end
    public static void cut(String s, int times, int start, int end) {
        if(times == 0) {
            if(isPanlidrome(s.substring(start, end+1))) {
                r.addLast(s.substring(start, end+1));
                res.add(new ArrayList<>(r));
                r.removeLast();
            }
            return;
        }
        // times > 0
        for(int cutPosition = start; cutPosition < end; cutPosition++) {
            if(isPanlidrome(s.substring(start, cutPosition+1))) {
                r.addLast(s.substring(start, cutPosition+1));
                cut(s, times-1, cutPosition+1, end);
                r.removeLast();
            }
        }
    }

    public static boolean isPanlidrome(String s) {
        int length = s.length();
        if(length == 1) {
            return true;
        }
        if((length & 1) == 1) {
            int point = length / 2;
            int i = point - 1;
            int j = point + 1;
            int curLen = 1;
            while(curLen != length) {
                if(s.charAt(i) == s.charAt(j)) {
                    i--;
                    j++;
                    curLen += 2;
                }
                else {
                    break;
                }
            }
            if(curLen != length) {
                return false;
            }
            return true;
        }
        else {
            int left = length / 2 - 1;
            int right = length / 2;
            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            int i = left - 1;
            int j = right + 1;
            int curLen = 2;
            while(curLen != length) {
                if(s.charAt(i) == s.charAt(j)) {
                    i--;
                    j++;
                    curLen += 2;
                }
                else {
                    break;
                }
            }
            if(curLen != length) {
                return false;
            }
            return true;
        }
    }
}

// 官方代码
class Solution {
    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/palindrome-partitioning/solution/fen-ge-hui-wen-chuan-by-leetcode-solutio-6jkv/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。