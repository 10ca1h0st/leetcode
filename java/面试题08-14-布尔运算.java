class Solution {
    Map<String, Integer> map = new HashMap<>();

    public int countEval(String s, int result) {
        if(s.length() == 1) {
            if(Integer.valueOf(s) != result) {
                return 0;
            }
            return 1;
        }
        String key = s + String.valueOf(result);
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int ans = 0;
        for(int i = 0; i < s.length()-1; i += 2) {
            char op = s.charAt(i+1);
            int[] operands = maybe(op, result);
            int size = operands.length;
            for(int j = 0; j < size; j += 2) {
                if(operands[j] == operands[j+1]) {
                    ans += countEval(s.substring(0, i+1), operands[j]) * countEval(s.substring(i+2), operands[j+1]);
                }
                else {
                    ans += countEval(s.substring(0, i+1), operands[j]) * countEval(s.substring(i+2), operands[j+1]);
                    ans += countEval(s.substring(0, i+1), operands[j+1]) * countEval(s.substring(i+2), operands[j]);
                }
            }
        }
        map.put(key, ans);
        return ans;
    }

    // 当中间操作符为op，要求的结果为result，则op两边的操作数可能为什么
    public int[] maybe(char op, int result) {
        if(op == '&') {
            if(result == 0) {
                return new int[] {0, 0, 0, 1};
            }
            else {
                return new int[] {1, 1};
            }
        }
        if(op == '|') {
            if(result == 0) {
                return new int[] {0, 0};
            }
            else  {
                return new int[] {0, 1, 1, 1};
            }
        }
        else {
            if(result == 0) {
                return new int[] {0, 0, 1, 1};
            }
            else {
                return new int[] {0, 1};
            }
        }
    }
}