class Solution {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> s1Map = new HashMap<>();
        for(char c : s1.toCharArray()) {
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }
        // [left, right)
        int left = 0;
        int right = 0;
        int length = s2.length();
        // 窗口中包含的已经满足数量的字符的个数
        int num = 0;
        while(right < length) {
            char cur = s2.charAt(right);
            right++;
            if(!s1Map.containsKey(cur)) {
                while(left < right - 1) {
                    char leftCh = s2.charAt(left);
                    s1Map.put(leftCh, s1Map.get(leftCh) + 1);
                    left++;
                }
                left = right;
                num = 0;
            }
            else {
                s1Map.put(cur, s1Map.get(cur) - 1);
                if(s1Map.get(cur) < 0) {
                    // 窗口中多了一个cur
                    while(s2.charAt(left) != cur) {
                        char leftCh = s2.charAt(left);
                        s1Map.put(leftCh, s1Map.get(leftCh) + 1);
                        if(s1Map.get(leftCh) == 1) {
                            num--;
                        }
                        left++;
                    }
                    s1Map.put(cur, 0);
                    left++;
                }
                else if(s1Map.get(cur) == 0) {
                    num++;
                    if(num == s1Map.size()) {
                        // System.out.println(left+" "+right);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}