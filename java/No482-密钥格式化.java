class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int index = s.length()-1;
        int count = 0;
        while (index >= 0) {
            if (s.charAt(index) != '-') {
                count++;
                char ch = s.charAt(index);
                if (ch >= 'a' && ch <= 'z') {
                    ch = (char)('A' + ch - 'a');
                }
                sb.append(ch);
                if (count == k) {
                    sb.append('-');
                    count = 0;
                }
            }
            index--;
        }
        if (sb.length() > 0 && sb.charAt(sb.length()-1) == '-') {
            sb.delete(sb.length()-1, sb.length());
        }
        return sb.reverse().toString();
    }
}