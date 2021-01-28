class Solution {
    static Integer AliceType = 1;
    static Integer BobType = 2;
    static Integer BIG = 10;
    static Integer SMALL = 1;
    // 最小生成树存放的是边的索引
    Set<Integer> mst;
    Set<Integer> deleted;

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        mst = new HashSet<>();
        deleted = new HashSet<>();
        for(int[] edge : edges) {
            edge[1]--;
            edge[2]--;
        }
        // 新的边，索引0为原来边在edges中的索引，索引4为边的权重
        int[][] newEdges = new int[edges.length][5];
        for(int i = 0; i < newEdges.length; i++) {
            newEdges[i][0] = i;
            newEdges[i][1] = edges[i][0];
            newEdges[i][2] = edges[i][1];
            newEdges[i][3] = edges[i][2];
            if(edges[i][0] == AliceType || edges[i][0] == BobType) {
                // 只能单独被一人使用的边，权重设置为大
                newEdges[i][4] = BIG;
            }
            else {
                newEdges[i][4] = SMALL;
            }
        }
        // 先判断Alice是否可以完全遍历图
        boolean canAlice = isConnected(n, newEdges, new HashSet<Integer>(List.of(BobType)));
        if(!canAlice) {
            return -1;
        }

        boolean canBob = isConnected(n, newEdges, new HashSet<Integer>(List.of(AliceType)));
        if(!canBob) {
            return -1;
        }
        genMST(n, newEdges, new HashSet<Integer>(List.of(BobType)));
        genMST(n, newEdges, new HashSet<Integer>(List.of(AliceType)));
        return newEdges.length - mst.size();
    }

    boolean isConnected(int n, int[][] edges, Set<Integer> filter) {
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges) {
            if(filter.contains(edge[1])) {
                continue;
            }
            uf.union(edge[2], edge[3]);
        }
        if(uf.countCC != 1) {
            return false;
        }
        return true;
    }

    // 这个方法错误之处在于，两次调用构成的二叉堆不同，因此在删除最小元素后，两次调用中，下一个最小元素可能不一样
    void _genMST(int n, int[][] edges, Set<Integer> filter) {
        UnionFind uf = new UnionFind(n);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[4] - b[4];
            }
        });
        for(int[] edge : edges) {
            if(filter.contains(edge[1])) {
                continue;
            }
            pq.add(edge);
        }
        int size = 0;
        // while(!pq.isEmpty() && size < n-1) {
        while(!pq.isEmpty()) {
            int[] edge = pq.poll();
            // System.out.println("edge type is "+edge[1]);
            if(uf.union(edge[2], edge[3])) {
                size++;
                mst.add(edge[0]);
            }
            else {
                // System.out.println("find a useless edge");
                deleted.add(edge[0]);
            }
        }
        // System.out.println(uf.countCC);
        // System.out.println(size);
    }

    void genMST(int n, int[][] edges, Set<Integer> filter) {
        UnionFind uf = new UnionFind(n);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[4] - b[4];
            }
        });
        for(int[] edge : edges) {
            pq.add(edge);
        }
        int size = 0;
        while(!pq.isEmpty() && size < n-1) {
            int[] edge = pq.poll();
            if(filter.contains(edge[1])) {
                continue;
            }
            if(uf.union(edge[2], edge[3])) {
                size++;
                mst.add(edge[0]);
            }
        }
    }
}

class UnionFind {
    int[] parent;
    // 连通分量的个数
    int countCC;

    UnionFind(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        countCC = n;
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) {
            return false;
        }
        parent[rootY] = rootX;
        countCC--;
        return true;
    }

    int find(int x) {
        if(x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}