class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = "1";
        n--;
        while (n > 0) {
            s = describe(s);
            n--;
        }
        return s;
    }

    public String describe(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        char prev = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (prev != ch && count > 0) {
                sb.append(count);
                sb.append(prev);
                count = 1;
            } else {
                count++;
            }
            prev = ch;
        }
        if (count > 0) {
            sb.append(count);
            sb.append(prev);
        }
        return sb.toString();
    }
}