class Solution {
    Map<Integer, Boolean>[] memo;
    public boolean checkValidString(String s) {
        memo = new HashMap[s.length()];
        for (int i = 0; i < memo.length; i++) {
            memo[i] = new HashMap<>();
        }
        // 表示目前左括号比右括号多的数量
        int more = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ')') {
                more--;
                if (more < 0) {
                    return false;
                }
            } else if (cur == '(') {
                more++;
            } else {
                return helper(s, i+1, more+1) || helper(s, i+1, more-1) || helper(s, i+1, more);
            }
        }
        return more == 0;
    }

    public boolean helper(String s, int start, int more) {
        int originMore = more;
        if (start >= s.length()) {
            return more==0;
        }
        if (memo[start].containsKey(more)) {
            return memo[start].get(more);
        }
        if (more < 0) {
            memo[start].put(originMore, false);
            return false;
        }
        if (s.length() - start < more) {
            memo[start].put(originMore, false);
            return false;
        }
        for (int i = start; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == ')') {
                more--;
                if (more < 0) {
                    memo[start].put(originMore, false);
                    return false;
                }
            } else if (cur == '(') {
                more++;
            } else {
                if (helper(s, i+1, more+1) || helper(s, i+1, more-1) || helper(s, i+1, more)) {
                    memo[start].put(originMore, true);
                    return true;
                } else {
                    memo[start].put(originMore, false);
                    return false;
                }
            }
        }
        memo[start].put(originMore, more==0);
        return more == 0;
    }
}