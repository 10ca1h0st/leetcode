class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chs = s.toCharArray();
        int ans = 0;
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] == 'I') {
                if(i < chs.length - 1 && chs[i+1] == 'V') {
                    ans += 4;
                    i++;
                }
                else if(i < chs.length - 1 && chs[i+1] == 'X') {
                    ans += 9;
                    i++;
                }
                else {
                    ans += 1;
                }
            }
            else if(chs[i] == 'X') {
                if(i < chs.length - 1 && chs[i+1] == 'L') {
                    ans += 40;
                    i++;
                }
                else if(i < chs.length - 1 && chs[i+1] == 'C') {
                    ans += 90;
                    i++;
                }
                else {
                    ans += 10;
                }
            }
            else if(chs[i] == 'C') {
                if(i < chs.length - 1 && chs[i+1] == 'D') {
                    ans += 400;
                    i++;
                }
                else if(i < chs.length - 1 && chs[i+1] == 'M') {
                    ans += 900;
                    i++;
                }
                else {
                    ans += 100;
                }
            }
            else {
                ans += map.get(chs[i]);
            }
        }
        return ans;
    }
}