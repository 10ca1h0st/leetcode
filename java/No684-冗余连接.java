class Solution {
    boolean[] visited;
    boolean[] onStack;
    int[] edgeTo;
    boolean cycle;
    Set<Integer> cyclePath;

    public int[] findRedundantConnection(int[][] edges) {
        // 通过dfs找到环，并记录环的边

        int N = edges.length;
        // 第一步，将edges转换为邻接表
        Graph graph = new Graph(N);
        for(int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            graph.addEdge(v1, v2);
        }

        // dfs
        visited = new boolean[N+1];
        onStack = new boolean[N+1];
        edgeTo = new int[N+1];
        cycle = false;
        cyclePath = new HashSet<>();
        for(int v = 1; v <= N; v++) {
            if(cycle) {
                break;
            }
            if(!visited[v]) {
                // System.out.println("dfs("+v+")");
                dfs(graph, v);
            }
        }
        if(!cycle) {
            // error occurs here
        }
        // for(int v : cyclePath) {
        //     System.out.print(v+" ");
        // }
        // System.out.println();
        int[] ret = new int[2];
        for(int[] edge : edges) {
            // edge是否在cyclePath中出现
            if(cyclePath.contains(edge[0]) && cyclePath.contains(edge[1])) {
                ret[0] = edge[0];
                ret[1] = edge[1];
            }
        }
        return ret;
    }

    void dfs(Graph g, int i) {
        visited[i] = true;
        onStack[i] = true;
        for(int v : g.E[i]) {
            if(cycle) {
                return;
            }
            if(!visited[v]) {
                // 当前dfs走的路径中，有一条i到v的边
                edgeTo[v] = i;
                // System.out.println("dfs("+v+")");
                dfs(g, v);
            }
            // 针对无向图，需要添加条件 edgeTo[i] != v
            else if(onStack[v] && edgeTo[i] != v) {
                // visited[v] == T && onStack[v] == T
                // 出现环
                cycle = true;
                for(int x = i; x != v; x = edgeTo[x]) {
                    cyclePath.add(x);
                }
                cyclePath.add(v);
                cyclePath.add(i);
                return;
            }
        }
        onStack[i] = false;
    }
}

class Graph {
    // 顶点从1开始计数
    int V;
    List<Integer>[] E;

    Graph(int V) {
        this.V = V;
        E = (List<Integer>[]) new List[V+1];
        for(int i = 0; i < V+1; i++) {
            E[i] = new ArrayList<>();
        }
    }

    void addEdge(int v1, int v2) {
        E[v1].add(v2);
        E[v2].add(v1);
    }

}