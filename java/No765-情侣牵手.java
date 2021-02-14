class Solution {
    public int minSwapsCouples(int[] row) {
        int length = row.length;
        int couples = length / 2;
        UnionFind uf = new UnionFind(length);
        // 初始化uf，连接已有的边
        for(int i = 0; i < length; ) {
            uf.union(row[i], row[i+1], true);
            i += 2;
        }
        for(int i = 1; i <= couples; i++) {
            uf.union(2*i-2, 2*i-1, false);
        }
        return uf.unionCount;
    }
}

class UnionFind {
    int N;
    int unionCount;
    int[] parent;

    UnionFind(int n) {
        N = n;
        parent = new int[N];
        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }
        unionCount = 0;
    }

    int find(int x) {
        if(x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 当init为true时，连接两个节点，不增加unionCount的值
    // 当init为true时，连接两个节点，不增加unionCount的值
    boolean union(int x, int y, boolean init) {
        x = find(x);
        y = find(y);
        if(x == y) {
            return false;
        }
        parent[y] = x;
        if(!init) {
            unionCount++;
        }
        return true;
    }
}