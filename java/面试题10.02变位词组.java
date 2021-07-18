class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sort = s.toCharArray();
            Arrays.sort(sort);
            String sortStr = String.valueOf(sort);
            List<String> list = map.getOrDefault(sortStr, new ArrayList<String>());
            list.add(s);
            map.put(sortStr, list);
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> v = entry.getValue();
            ans.add(v);
        }
        return ans;
    }
}