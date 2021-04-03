import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LCS {
//    求最长公共子序列
    public static int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        if(length1==0 || length2==0) {
            return 0;
        }
        int[][] choose = new int[length1+1][length2+1];
        int[][] dp = new int[length1+1][length2+1];
        for(int i = 1; i <= length1; i++) {
            dp[i][0] = 0;
        }
        for(int i = 1; i <= length2; i++) {
            dp[0][i] = 0;
        }
        for(int i = 1; i <= length1; i++) {
            for(int j = 1; j <= length2; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        Set<String> ans = lcsString(dp, text1, text2, text1.length(), text2.length());
        for(String s : ans) {
            System.out.println(s);
        }
        return dp[length1][length2];
    }

//    求text1的长为i的前缀和text2的长为j的前缀之间的lcs
    public static Set<String> lcsString(int[][] dp, String text1, String text2, int i, int j) {
        Set<String> ans = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0) {
            if(dp[i][j] == 0) {
                break;
            }
            if(text1.charAt(i-1) == text2.charAt(j-1)) {
                sb.append(text1.charAt(i-1));
                i--;
                j--;
            }
            else {
                if(dp[i-1][j] > dp[i][j-1]) {
                    i = i-1;
                    j = j;
                }
                else if(dp[i-1][j] < dp[i][j-1]) {
                    i = i;
                    j = j-1;
                }
                else {
//                    两者相等
                    Set<String> ans1 = lcsString(dp, text1, text2, i-1, j);
                    Set<String> ans2 = lcsString(dp, text1, text2, i, j-1);
                    if(ans1.isEmpty() && ans2.isEmpty()) {
                        break;
                    }
                    String suffix = sb.reverse().toString();
                    for(String s : ans1) {
                        ans.add(s+suffix);
                    }
                    for(String s : ans2) {
                        ans.add(s+suffix);
                    }
                    break;
                }
            }
        }
        if(sb.length()==0 && ans.isEmpty()) {
            return ans;
        }
        else if(!ans.isEmpty()) {
            return ans;
        }
        else {
            return Set.of(sb.reverse().toString());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String text1 = bf.readLine();
        String text2 = bf.readLine();
        longestCommonSubsequence(text1, text2);
    }


}
