class Solution {
    public int numDistinct(String s, String t) {
        if(s.length() < t.length()) {
            return 0;
        }
        if(s.length() == t.length()) {
            if(s.equals(t)) {
                return 1;
            }
            return 0;
        }
        if(t.length() == 0) {
            return 0;
        }
        int sLength = s.length();
        int tLength = t.length();
        // dp[i][j]：s的前i个字符的以第i个字符结尾的子序列中t的前j个字符出现的次数
        int[][] dp = new int[sLength+1][tLength+1];
        dp[0][0] = 1;
        for(int j = 1; j <= tLength; j++) {
            dp[0][j] = 0;
        }
        for(int i = 1; i <= sLength; i++) {
            dp[i][0] = 0;
        }
        for(int i = 1; i <= sLength; i++) {
            for(int j = 1; j <= Math.min(tLength, i); j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    for(int k = i-1; k >= j-1; k--) {
                        dp[i][j] += dp[k][j-1];
                    }
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }
        int res = 0;
        for(int i = tLength; i <= sLength; i++) {
            res += dp[i][tLength];
        }
        return res;
    }


// 回溯超时
//     String S;
//     String T;
//     int indexT;
//     int endS;

//     public int numDistinct(String s, String t) {
//         if(s.length() < t.length()) {
//             return 0;
//         }
//         if(s.length() == t.length()) {
//             if(s.equals(t)) {
//                 return 1;
//             }
//             return 0;
//         }
//         if(t.length() == 0) {
//             return 0;
//         }
//         S = s;
//         T = t;
//         indexT = 0;
//         int start = 0;
//         while(start < S.length()) {
//             if(S.charAt(start) == T.charAt(0)) {
//                 break;
//             }
//             start++;
//         }
//         endS = S.length()-1;
//         while(endS >= 0) {
//             if(S.charAt(endS) == T.charAt(T.length()-1)) {
//                 break;
//             }
//             endS--;
//         }
//         int res = backtrace(start);
//         return res;
//     }

// // 从S[start]开始回溯
//     public int backtrace(int start) {
//         if(indexT == T.length()) {
//             return 1;
//         }
//         if(endS-start+1 < T.length()-indexT) {
//             return 0;
//         }
//         int res = 0;
//         for(int i = start; i <= endS; i++) {
//             if(S.charAt(i) == T.charAt(indexT)) {
//                 indexT++;
//                 res += backtrace(i+1);
//                 indexT--;
//             }
//         }
//         return res;
//     }
}