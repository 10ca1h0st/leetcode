class Solution {
    public String[] permutation(String s) {
        Set<String> seen = new HashSet<>();
        int[] chs = new int[26];
        for(char ch : s.toCharArray()) {
            chs[ch - 'a']++;
        }
        int left = s.length();
        Deque<Character> cur = new ArrayDeque<>();
        backtrace(seen, chs, left, cur);
        int size = seen.size();
        String[] ans = new String[size];
        int index = 0;
        for(String res : seen) {
            ans[index] = res;
            index++;
        }
        return ans;
    }

    public void backtrace(Set<String> seen, int[] chs, int left, Deque<Character> cur) {
        if(left == 0) {
            StringBuilder sb = new StringBuilder();
            for(char ch : cur) {
                sb.append(ch);
            }
            String s = sb.reverse().toString();
            if(!seen.contains(s)) {
                seen.add(s);
            }
            return;
        }
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] != 0) {
                // 选择字符'a'+i
                left--;
                chs[i]--;
                cur.push((char)('a'+i));
                backtrace(seen, chs, left, cur);
                cur.pop();
                chs[i]++;
                left++;
            }
        }
    }
}