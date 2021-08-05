// 官方解法
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }
}


// 超时
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] inCycle = new boolean[n];
        boolean[] path = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                path = new boolean[n];
                path[i] = true;
                bfs(i, graph, visited, inCycle, path);
                path[i] = false;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!inCycle[i]) {
                ans.add(i);
            }
        }
        return ans;
    }

    private void bfs(int v, int[][] graph, boolean[] visited, boolean[] inCycle, boolean[] path) {
        visited[v] = true;
        for (int to : graph[v]) {
            if (path[to]) {
                // 出现环
                for (int i = 0; i < path.length; i++) {
                    if (path[i]) {
                        inCycle[i] = true;
                    }
                }
            } else {
                path[to] = true;
                bfs(to, graph, visited, inCycle, path);
                path[to] = false;
            }
        }
    }
}