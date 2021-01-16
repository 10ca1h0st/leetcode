public class Solution {

    private int rows;
    private int cols;

    public static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.rows = grid.length;
        this.cols = grid[0].length;

        // 第 1 步：把 grid 中的砖头全部击碎，通常算法问题不能修改输入数据，这一步非必需，可以认为是一种答题规范
        int[][] copy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = grid[i][j];
            }
        }

        // 把 copy 中的砖头全部击碎
        for (int[] hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }

        // 第 2 步：建图，把砖块和砖块的连接关系输入并查集，size 表示二维网格的大小，也表示虚拟的「屋顶」在并查集中的编号
        int size = rows * cols;
        UnionFind unionFind = new UnionFind(size + 1);

        // 将下标为 0 的这一行的砖块与「屋顶」相连
        for (int j = 0; j < cols; j++) {
            if (copy[0][j] == 1) {
                unionFind.union(j, size);
            }
        }

        // 其余网格，如果是砖块向上、向左看一下，如果也是砖块，在并查集中进行合并
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (copy[i][j] == 1) {
                    // 如果上方也是砖块
                    if (copy[i - 1][j] == 1) {
                        unionFind.union(getIndex(i - 1, j), getIndex(i, j));
                    }
                    // 如果左边也是砖块
                    if (j > 0 && copy[i][j - 1] == 1) {
                        unionFind.union(getIndex(i, j - 1), getIndex(i, j));
                    }
                }
            }
        }

        // 第 3 步：按照 hits 的逆序，在 copy 中补回砖块，把每一次因为补回砖块而与屋顶相连的砖块的增量记录到 res 数组中
        int hitsLen = hits.length;
        int[] res = new int[hitsLen];
        for (int i = hitsLen - 1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];

            // 注意：这里不能用 copy，语义上表示，如果原来在 grid 中，这一块是空白，这一步不会产生任何砖块掉落
            // 逆向补回的时候，与屋顶相连的砖块数量也肯定不会增加
            if (grid[x][y] == 0) {
                continue;
            }

            // 补回之前与屋顶相连的砖块数
            int origin = unionFind.getSize(size);

            // 注意：如果补回的这个结点在第 1 行，要告诉并查集它与屋顶相连（逻辑同第 2 步）
            if (x == 0) {
                unionFind.union(y, size);
            }

            // 在 4 个方向上看一下，如果相邻的 4 个方向有砖块，合并它们
            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (inArea(newX, newY) && copy[newX][newY] == 1) {
                    unionFind.union(getIndex(x, y), getIndex(newX, newY));
                }
            }

            // 补回之后与屋顶相连的砖块数
            int current = unionFind.getSize(size);
            // 减去的 1 是逆向补回的砖块（正向移除的砖块），与 0 比较大小，是因为存在一种情况，添加当前砖块，不会使得与屋顶连接的砖块数更多
            res[i] = Math.max(0, current - origin - 1);

            // 真正补上这个砖块
            copy[x][y] = 1;
        }
        return res;
    }

    /**
     * 输入坐标在二维网格中是否越界
     *
     * @param x
     * @param y
     * @return
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /**
     * 二维坐标转换为一维坐标
     *
     * @param x
     * @param y
     * @return
     */
    private int getIndex(int x, int y) {
        return x * cols + y;
    }

    private class UnionFind {

        /**
         * 当前结点的父亲结点
         */
        private int[] parent;
        /**
         * 以当前结点为根结点的子树的结点总数
         */
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 路径压缩，只要求每个不相交集合的「根结点」的子树包含的结点总数数值正确即可，因此在路径压缩的过程中不用维护数组 size
         *
         * @param x
         * @return
         */
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            // 在合并的时候维护数组 size
            size[rootY] += size[rootX];
        }

        /**
         * @param x
         * @return x 在并查集的根结点的子树包含的结点总数
         */
        public int getSize(int x) {
            int root = find(x);
            return size[root];
        }
    }
}

// class Solution {
//     public int[] hitBricks(int[][] grid, int[][] hits) {
//         // 将所有砖块从坐标映射到索引
//         Map<String, Integer> map = new HashMap<>();
//         int m = grid.length;
//         int n = grid[0].length;
//         int index = 0;
//         for(int i = 0; i < m; i++) {
//             for(int j = 0; j < n; j++) {
//                 if(grid[i][j] == 1) {
//                     index++;
//                 }
//             }
//         }
//         Graph graph = new Graph(index);
//         index = 0;
//         for(int i = 0; i < m; i++) {
//             for(int j = 0; j < n; j++) {
//                 if(grid[i][j] == 1) {
//                     int curV = index;
//                     map.put(String.valueOf(i)+","+String.valueOf(j), index);
//                     index++;
//                     if(i == 0) {
//                         graph.addSpecialV(curV);
//                     }
//                     if(i > 0 && grid[i-1][j] == 1) {
//                         int v = map.get(String.valueOf(i-1)+","+String.valueOf(j));
//                         graph.addEdge(curV, v);
//                     }
//                     if(j > 0 && grid[i][j-1] == 1) {
//                         int v = map.get(String.valueOf(i)+","+String.valueOf(j-1));
//                         graph.addEdge(curV, v);
//                     }
//                 }
//             }
//         }

//         graph.calConnected();
//         int[] ret = new int[hits.length];
//         index = 0;
//         for(int[] hit : hits) {
//             int v = map.getOrDefault(String.valueOf(hit[0]) + "," + String.valueOf(hit[1]), -1);
//             if(v == -1) {
//                 ret[index++] = 0;
//                 continue;
//             }
//             int diff = graph.removeV(v);
//             if(diff == 0) {
//                 diff = 1;
//             }
//             ret[index] = diff - 1;
//             index++;
//         }
//         return ret;
//     }
// }

// class Graph {
//     int V;
//     List<Integer>[] adj;

//     // 记录与网格顶部接触的砖块的索引
//     List<Integer> specialV;
//     // 图中所有包含specialV中的砖块的连通分量包含的砖块的个数
//     int total;
//     // 被删除的砖块
//     List<Integer> deleted;
    

//     Graph(int V) {
//         this.V = V;
//         adj = (List<Integer>[]) new List[V];
//         for(int i = 0; i < V; i++) {
//             adj[i] = new ArrayList<>();
//         }
//         specialV = new ArrayList<>();
//         total = 0;
//         deleted = new ArrayList<>();
//     }

//     void addEdge(int v1, int v2) {
//         adj[v1].add(v2);
//         adj[v2].add(v1);
//     }

//     void addSpecialV(int v) {
//         specialV.add(v);
//     }

//     void calConnected() {
//         boolean[] visited = new boolean[V];
//         total = 0;
//         for(int v : specialV) {
//             if(deleted.contains(v)) {
//                 continue;
//             }
//             if(!visited[v]) {
//                 dfs(visited, v);
//             }
//         }
//     }

//     int removeV(int v) {
//         deleted.add(v);
//         int originTotal = total;
//         calConnected();
//         return originTotal - total;
//     }

//     void dfs(boolean[] visited, int i) {
//         visited[i] = true;
//         total++;
//         for(int v : adj[i]) {
//             if(deleted.contains(v)) {
//                 continue;
//             }
//             if(!visited[v]) {
//                 dfs(visited, v);
//             }
//         }
//     }

// }