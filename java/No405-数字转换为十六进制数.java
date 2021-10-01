class Solution {
    public String toHex(int num) {
        int mask = 15;
        StringBuilder ans = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        while (num != 0) {
            int res = num & mask;
            if (res >= 10) {
                ans.append((char)('f'-mask+res));
            } else {
                ans.append((char)('0'+res));
            }
            num = num >>> 4;
        }
        return ans.reverse().toString();
    }
}