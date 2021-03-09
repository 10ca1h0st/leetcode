class Solution {
    public String removeDuplicates(String S) {
        int length = S.length();
        if(length <= 1) {
            return S;
        }
        int prev = 0;
        int cur = 1;
        StringBuilder sb = new StringBuilder();
        boolean duplicate = false;
        while(cur < length) {
            if(S.charAt(prev) == S.charAt(cur)) {
                duplicate = true;
                prev += 2;
                cur += 2;
            }
            else {
                sb.append(S.charAt(prev));
                prev++;
                cur++;
            }
        }
        if(prev < length) {
            sb.append(S.charAt(prev));
        }
        if(duplicate) {
            return removeDuplicates(sb.toString());
        }
        return sb.toString();
    }
}