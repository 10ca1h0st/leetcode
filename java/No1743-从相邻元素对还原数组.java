class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        int[] ans = new int[n];
        Map<Integer, List<Integer>> adjacent = new HashMap<>();
        Set<Integer> heads = new HashSet<>();
        for (int[] adjacentPair : adjacentPairs) {
            int a = adjacentPair[0];
            int b = adjacentPair[1];
            List<Integer> adjacentA = adjacent.getOrDefault(a, new ArrayList<>());
            if (adjacentA.isEmpty()) {
                heads.add(a);
            } else {
                heads.remove(a);
            }
            adjacentA.add(b);
            adjacent.put(a, adjacentA);
            List<Integer> adjacentB = adjacent.getOrDefault(b, new ArrayList<>());
            if (adjacentB.isEmpty()) {
                heads.add(b);
            } else {
                heads.remove(b);
            }
            adjacentB.add(a);
            adjacent.put(b, adjacentB);
        }
        int head = 0;
        for (int i : heads) {
            head = i;
        }
        ans[0] = head;
        int prev = head;
        for (int i = 1; i < ans.length; i++) {
            List<Integer> list = adjacent.get(prev);
            if (list.size() == 1) {
                ans[i] = list.get(0);
            } else {
                ans[i] = (list.get(0)==ans[i-2])?list.get(1):list.get(0);
            }
            prev = ans[i];
        }
        return ans;
    }
}