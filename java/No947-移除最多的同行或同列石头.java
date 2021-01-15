class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                if(isSameRowOrCol(stones[i], stones[j])) {
                    union(parent, i, j);
                }
            }
        }
        for(int i = 0; i < n; i++) {
            find(parent, i);
        }
        Set<Integer> connected = new HashSet<>();
        for(int p : parent) {
            connected.add(p);
        }
        return n - connected.size();
    }

    boolean isSameRowOrCol(int[] s1, int[] s2) {
        if(s1[0] == s2[0] || s1[1] == s2[1]) {
            return true;
        }
        return false;
    }

    void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    int find(int[] parent, int x) {
        if(parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}