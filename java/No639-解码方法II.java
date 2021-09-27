class Solution {
    public int numDecodings(String s) {
        int mod = 1000000007;
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        if (s.charAt(0) == '*') {
            dp[1] = 9;
        } else if (s.charAt(0) != '0') {
            dp[1] = 1;
        }
        for (int i = 2; i < dp.length; i++) {
            char prev = s.charAt(i-2);
            char cur = s.charAt(i-1);
            if (cur == '*') {
                dp[i] = (int)((9*(long)dp[i-1])%mod);
            } else if (cur == '0') {
                // do nothing
            } else {
                dp[i] = dp[i-1];
            }
            dp[i] = (int)((dp[i] + (long)dp[i-2]*maybe(prev, cur))%mod);
        }
        return dp[s.length()];
    }

    // 字符串ab可能组成的合法编码个数,ab看做整体
    public int maybe(char a, char b) {
        if (a == '0') {
            return 0;
        }
        if (b == '*') {
            if (a == '1') {
                return 9;
            } else if (a == '2') {
                return 6;
            } else if (a == '*') {
                return maybe('1', b) + maybe('2', b);
            } else {
                return 0;
            }
        } else if (b != '0') {
            if (a == '1') {
                return 1;
            } else if (a == '2') {
                if (b < '7') {
                    return 1;
                } else {
                    return 0;
                }
            } else if (a == '*') {
                return maybe('1', b) + maybe('2', b);
            } else {
                return 0;
            }
        } else {
            if (a == '1') {
                return 1;
            } else if (a == '2') {
                return 1;
            } else if (a == '*') {
                return 2;
            } else {
                return 0;
            }
        }
    }
}