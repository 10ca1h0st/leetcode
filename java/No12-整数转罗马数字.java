class Solution {
    public String intToRoman(int num) {
        Map<Integer, Character> map = new HashMap<>();
        map.put(1, 'I');
        map.put(5, 'V');
        map.put(10, 'X');
        map.put(50, 'L');
        map.put(100, 'C');
        map.put(500, 'D');
        map.put(1000, 'M');
        Deque<Integer> low2high = new ArrayDeque<>();
        while(num > 0) {
            low2high.offer(num % 10);
            num = num / 10;
        }
        StringBuilder sb = new StringBuilder();
        int base = (int)Math.pow(10, low2high.size() - 1);
        while(!low2high.isEmpty()) {
            int i = low2high.pollLast();
            if(i == 0) {

            }
            else if(i != 4 && i != 9) {
                if(i >= 5) {
                    sb.append(map.get(5*base));
                    i = i - 5;
                    while(i > 0) {
                        char c = map.get(base);
                        sb.append(c);
                        i--;
                    }
                }
                else {
                    char c = map.get(base);
                    while(i > 0) {
                        sb.append(c);
                        i--;
                    }
                }
            }
            else if(i == 4) {
                if(base == 1) {
                    sb.append("IV");
                }
                else if(base == 10) {
                    sb.append("XL");
                }
                else if(base == 100) {
                    sb.append("CD");
                }
            }
            else {
                if(base == 1) {
                    sb.append("IX");
                }
                else if(base == 10) {
                    sb.append("XC");
                }
                else if(base == 100) {
                    sb.append("CM");
                }
            }
            base /= 10;
        }
        return sb.toString();
    }
}