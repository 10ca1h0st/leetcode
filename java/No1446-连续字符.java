class Solution {
    public int maxPower(String s) {
        char prev = 1;
        int power = 0;
        int con = 0;
        for (int i = 0; i < s.length(); i++) {
            if (prev == s.charAt(i)) {
                con++;
            } else {
                power = Math.max(power, con);
                con = 1;
                prev = s.charAt(i);
            }
        }
        power = Math.max(power, con);
        return power;
    }
}