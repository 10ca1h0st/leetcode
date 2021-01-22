class Solution {
    public int makeConnected(int n, int[][] connections) {
        int[][] edges = new int[connections.length][3];
        for(int i = 0; i < edges.length; i++) {
            edges[i][0] = i;
            edges[i][1] = connections[i][0];
            edges[i][2] = connections[i][1];
        }
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges) {
            uf.union(edge);
        }
        if(uf.CC == 1) {
            return 0;
        }
        if(uf.repeatedEdge.size() < uf.CC-1) {
            return -1;
        }
        return uf.CC-1;
    }
}

class UnionFind {
    // 记录造成环的边的索引
    List<Integer> repeatedEdge;
    int CC;
    int[] parent;

    UnionFind(int V) {
        CC = V;
        parent = new int[V];
        for(int i = 0; i < V; i++) {
            parent[i] = i;
        }
        repeatedEdge = new ArrayList<>();
    }

    int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean union(int[] edge) {
        int x = edge[1];
        int y = edge[2];
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) {
            repeatedEdge.add(edge[0]);
            return false;
        }
        parent[rootY] = rootX;
        CC--;
        return true;
    }
}