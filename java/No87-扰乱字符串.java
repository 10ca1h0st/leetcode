class Solution {
    // 记忆化搜索存储状态的数组
    // -1 表示 false，1 表示 true，0 表示未计算
    int[][][] memo;
    String s1, s2;

    public boolean isScramble(String s1, String s2) {
        int length = s1.length();
        this.memo = new int[length][length][length + 1];
        this.s1 = s1;
        this.s2 = s2;
        return dfs(0, 0, length);
    }

    // 第一个字符串从 i1 开始，第二个字符串从 i2 开始，子串的长度为 length，是否和谐
    public boolean dfs(int i1, int i2, int length) {
        if (memo[i1][i2][length] != 0) {
            return memo[i1][i2][length] == 1;
        }

        // 判断两个子串是否相等
        if (s1.substring(i1, i1 + length).equals(s2.substring(i2, i2 + length))) {
            memo[i1][i2][length] = 1;
            return true;
        }

        // 判断是否存在字符 c 在两个子串中出现的次数不同
        if (!checkIfSimilar(i1, i2, length)) {
            memo[i1][i2][length] = -1;
            return false;
        }
        
        // 枚举分割位置
        for (int i = 1; i < length; ++i) {
            // 不交换的情况
            if (dfs(i1, i2, i) && dfs(i1 + i, i2 + i, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
            // 交换的情况
            if (dfs(i1, i2 + length - i, i) && dfs(i1 + i, i2, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
        }

        memo[i1][i2][length] = -1;
        return false;
    }

    public boolean checkIfSimilar(int i1, int i2, int length) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = i1; i < i1 + length; ++i) {
            char c = s1.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (int i = i2; i < i2 + length; ++i) {
            char c = s2.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) - 1);
        }
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            int value = entry.getValue();
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}

// class Solution {
//     Map<String, Set<String>> dp;

//     public boolean isScramble(String s1, String s2) {
//         if(s1.length() == 1) {
//             return s1.equals(s2);
//         }
//         Set<Character> chs = new HashSet<>();
//         for(char ch : s1.toCharArray()) {
//             chs.add(ch);
//         }
//         for(char ch : s2.toCharArray()) {
//             if(!chs.contains(ch)) {
//                 return false;
//             }
//         }
//         dp = new HashMap<>();
//         int length = s1.length();
//         for(int split = 1; split < length; split++) {
//             Set<String> pre = possible(s1.substring(0, split));
//             Set<String> post = possible(s1.substring(split, length));
//             for(String e1 : pre) {
//                 for(String e2 : post) {
//                     if(s2.equals(e1+e2)) {
//                         return true;
//                     }
//                     if(s2.equals(e2+e1)) {
//                         return true;
//                     }
//                 }
//             }
//         }
//         return false;
//     }

//     // 返回s所有可能的扰乱字符串
//     public Set<String> possible(String s) {
//         if(s.length() == 0) {
//             return new HashSet<>();
//         }
//         if(s.length() == 1) {
//             return Set.of(s);
//         }
//         if(dp.containsKey(s)) {
//             return dp.get(s);
//         }
//         int length = s.length();
//         Set<String> ans = new HashSet<>();
//         for(int split = 1; split < length; split++) {
//             Set<String> pre = possible(s.substring(0, split));
//             Set<String> post = possible(s.substring(split, length));
//             for(String e1 : pre) {
//                 for(String e2 : post) {
//                     ans.add(e1+e2);
//                     ans.add(e2+e1);
//                 }
//             }
//         }
//         dp.put(s, ans);
//         return ans;
//     }
// }