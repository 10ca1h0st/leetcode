class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 如果添加的边使得有向图出现了一个环，如果出现了入度为2的顶点，删除环中终点为该顶点的边
        //                                 如果没有入度为2的顶点，则说明添加的边终点为根节点，此时，删除环中在edges中最后出现的边
        // 如果没有环，一定有入度为2的顶点，删除终点为该顶点的边（删除在edges中最后出现的边）

        int V = edges.length;
        DiGraph graph = new DiGraph(V);
        for(int[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }

        graph.calCycle();
        int[] ret = null;
        if(graph.hasCycle) {
            if(graph.cycleHas2InDegree != -1) {
                return new int[] {graph.cycleHas2InDegreeSrc, graph.cycleHas2InDegree};
            }
            else {
                for(int[] edge : edges) {
                    if(graph.cycle.contains(edge[0]) && graph.cycle.contains(edge[1])) {
                        ret = edge;
                    }
                }
                return ret;
            }
        }

        for(int[] edge : edges) {
            if(graph.indegree[edge[1]] == 2) {
                ret = edge;
            }
        }
        return ret;
    }
}

class DiGraph {
    int[] indegree;
    int[] outdegree;
    List<Integer>[] adj;
    // 顶点从1开始
    int V;
    int E;
    boolean hasCycle;
    int cycleHas2InDegree;
    int cycleHas2InDegreeSrc;
    Set<Integer> cycle;
    boolean[] visited;
    boolean[] onStack;
    int[] edgeTo;

    DiGraph(int V) {
        this.V = V;
        this.E = 0;
        indegree = new int[V+1];
        outdegree = new int[V+1];
        adj = (List<Integer>[]) new List[V+1];
        hasCycle = false;
        cycleHas2InDegree = -1;
        cycleHas2InDegreeSrc = -1;
        cycle = new HashSet<>();
        visited = new boolean[V+1];
        onStack = new boolean[V+1];
        edgeTo = new int[V+1];
        for(int i = 1; i < V+1; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int src, int dst) {
        indegree[dst]++;
        outdegree[src]++;
        adj[src].add(dst);
    }

    void calCycle() {
        // 已经添加完边，计算是否有环
        for(int i = 1; i < V+1; i++) {
            if(hasCycle) {
                break;
            }
            if(!visited[i]) {
                dfs(i);
            }
        }
    }

    void dfs(int i) {
        visited[i] = true;
        onStack[i] = true;
        for(int v : adj[i]) {
            if(hasCycle) {
                return;
            }
            if(!visited[v]) {
                edgeTo[v] = i;
                dfs(v);
            }
            else if(onStack[v]) {
                hasCycle = true;
                for(int x = i; x != v; x = edgeTo[x]) {
                    if(indegree[x] == 2) {
                        cycleHas2InDegreeSrc = edgeTo[x];
                        cycleHas2InDegree = x;
                        return;
                    }
                    cycle.add(x);
                }
                if(indegree[v] == 2) {
                    cycleHas2InDegreeSrc = i;
                    cycleHas2InDegree = v;
                }
                cycle.add(v);
                return;
            }
        }
        onStack[i] = false;
    }
}