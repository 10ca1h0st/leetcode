class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii/solution/fen-ge-hui-wen-chuan-ii-by-leetcode-solu-norx/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


// 第28/29样例失败，为什么？找到了原因，但是会超时
class Solution {
    // 字符串[i...j]是否为回文串，默认i==j时，和，i>j时为true
    static boolean[][] panlidrome;
    static int[][] memo;

    public int minCut(String s) {
        // 分割方法从分割0次到分割s.length-1次
        int cut = 0;
        int length = s.length();
        if(length == 0) {
            return 0;
        }
        panlidrome = new boolean[length][length];
        memo = new int[length][length];
        for(int i = 0; i < length; i++) {
            Arrays.fill(panlidrome[i], true);
            Arrays.fill(memo[i], -1);
        }
        // 预处理，减少之后重复判断某个子串为回文串
        for(int i = length - 2; i >= 0; i--) {
            for(int j = i+1; j < length; j++) {
                panlidrome[i][j] = (s.charAt(i) == s.charAt(j)) && panlidrome[i+1][j-1];
            }
        }
        if(panlidrome[0][length-1]) {
            return 0;
        }
        return minCutTimes(s, 0, length-1);
    }

    // s[start...end]最小切分次数
    public static int minCutTimes(String s, int start, int end) {
        if(end <= start) {
            return 0;
        }
        if(memo[start][end] != -1) {
            return memo[start][end];
        }
        // 首先寻找最长的回文子串
        int panlidromeLength = end - start + 1;
        int left = 0;;
        int right = 0;
        int res = Integer.MAX_VALUE;
        boolean found = false;
        while(panlidromeLength >= 1) {
            left = start;
            right = left + panlidromeLength - 1;
            while(right <= end) {
                if(panlidrome[left][right]) {
                    found = true;
                    int tempRes = 0;
                    if(left > start) {
                        tempRes++;
                    }
                    if(right < end) {
                        tempRes++;
                    }
                    int temp1 = minCutTimes(s, start, left-1);
                    if(start < left-1) {
                        memo[start][left-1] = temp1;
                    }
                    int temp2 = minCutTimes(s, right+1, end);
                    if(right+1 < end) {
                        memo[right+1][end] = temp2;
                    }
                    tempRes += temp1 + temp2;
                    res = Math.min(res, tempRes);
                }
                left++;
                right++;
            }
			// 不应该有这个判断，因为并不是每次切割越长越好，
			// 可能这次切割长，但是剩下的字符串切割次数很多，
			// 导致最终切割次数之和反而变大
            //if(found) {
            //    break;
            //}
            panlidromeLength--;
        }
        memo[start][end] = res;
        return res;
    }
}




// 枚举切分次数，超时

class Solution {

    static List<List<String>> res;
    static LinkedList<String> r;
    // 字符串[i...j]是否为回文串，默认i==j时，和，i>j时为true
    static boolean[][] panlidrome;

    public int minCut(String s) {
        // 分割方法从分割0次到分割s.length-1次
        int cut = 0;
        int length = s.length();
        if(length == 0) {
            return 0;
        }
        panlidrome = new boolean[length][length];
        for(int i = 0; i < length; i++) {
            Arrays.fill(panlidrome[i], true);
        }
        // 预处理，减少之后重复判断某个子串为回文串
        for(int i = length - 2; i >= 0; i--) {
            for(int j = i+1; j < length; j++) {
                panlidrome[i][j] = (s.charAt(i) == s.charAt(j)) && panlidrome[i+1][j-1];
            }
        }
        if(isPanlidrome(0, length-1)) {
            return 0;
        }
        for(cut = 1; cut < length-1; cut++) {
            boolean res = cut(s, cut, 0, length-1);
            if(res) {
                return cut;
            }
        }
        // 最终将字符串每个字符一次切割，一定能满足条件
        return length-1;
    }

    // 用times次切割s[start...end]，是否能满足其切割的每一部分都是回文串
    public static boolean cut(String s, int times, int start, int end) {
        if(times == 0) {
            if(isPanlidrome(start, end)) {
                return true;
            }
            return false;
        }
        // times > 0
        boolean res = false;
        for(int cutPosition = start; cutPosition < end-(times-1); cutPosition++) {
            if(isPanlidrome(start, cutPosition)) {
                res = res || cut(s, times-1, cutPosition+1, end);
                if(res) {
                    break;
                }
            }
        }
        return res;
    }

    // i<=j
    public static boolean isPanlidrome(int i, int j) {
        return panlidrome[i][j];
    }
}