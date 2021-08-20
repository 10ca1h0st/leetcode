class Solution {
    public String reverseStr(String s, int k) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            int step = 0;
            StringBuilder temp = new StringBuilder();
            while (i < s.length() && step < k) {
                temp.insert(0, s.charAt(i));
                i++;
                step++;
            }
            step = 0;
            while (i < s.length() && step < k) {
                temp.append(s.charAt(i));
                i++;
                step++;
            }
            sb.append(temp.toString());
        }
        return sb.toString();
    }
}