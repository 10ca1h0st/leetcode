class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> gaps = new HashMap<>();
        int max = 0;
        for(List<Integer> per : wall) {
            int sum = 0;
            for(int i= 0; i < per.size()-1; i++) {
                int gap = per.get(i);
                sum += gap;
                int v = gaps.getOrDefault(sum, 0);
                v++;
                gaps.put(sum, v);
                max = Math.max(max, v);
            }
        }
        return wall.size() - max;
    }
}