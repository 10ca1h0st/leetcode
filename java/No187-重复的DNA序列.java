class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int length = s.length();
        Map<String, Integer> map = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i+9 < length; i++) {
            String ss = s.substring(i, i+10);
            if (!map.containsKey(ss)) {
                map.put(ss, 1);
            } else if (map.get(ss) == 1) {
                ans.add(ss);
                map.put(ss, 2);
            }
        }
        return ans;
    }
}