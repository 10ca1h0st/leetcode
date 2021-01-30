class Solution {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        List<int[]> edges = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i < N-1) {
                    int[] edge = new int[] {i*N+j, (i+1)*N+j, Math.max(grid[i][j], grid[i+1][j])};
                    edges.add(edge);
                }
                if(j < N-1) {
                    int[] edge = new int[] {i*N+j, i*N+(j+1), Math.max(grid[i][j], grid[i][j+1])};
                    edges.add(edge);
                }
            }
        }
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                return x[2] - y[2];
            }
        });
        UnionFind uf = new UnionFind(N*N);
        int ret = 0;
        for(int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            uf.union(from, to);
            if(uf.isConnected(0, N*N-1)) {
                ret = weight;
                break;
            }
        }
        return ret;
    }
}

class UnionFind {
    int[] parent;

    UnionFind(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean isConnected(int x, int y) {
        x = find(x);
        y = find(y);
        return x == y;
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) {
            return false;
        }
        parent[rootY] = rootX;
        return true;
    }
}