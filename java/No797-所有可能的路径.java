class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, 0, path, ans);
        return ans;
    }

    public void dfs(int[][] graph, int from, List<Integer> path, List<List<Integer>> ans) {
        if (from == graph.length-1) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int to : graph[from]) {
            path.add(to);
            dfs(graph, to, path, ans);
            path.remove(path.size()-1);
        }
    }
}