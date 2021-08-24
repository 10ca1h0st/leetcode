class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<Node>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            graph[from].add(new Node(to, cost));
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(src, 0));
        int step = 0;
        int ans = Integer.MAX_VALUE;
        // 使用这个visited避免超时的发生
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        while (!queue.isEmpty() && step <= k+1) {
            int size = queue.size();
            while (size > 0) {
                size--;
                Node node = queue.poll();
                if (node.pos == dst) {
                    ans = Math.min(ans, node.cost);
                }
                for (Node toNode : graph[node.pos]) {
                    if (visited[toNode.pos] != Integer.MAX_VALUE && visited[toNode.pos] < node.cost+toNode.cost) {
                        continue;
                    }
                    queue.offer(new Node(toNode.pos, node.cost + toNode.cost));
                    visited[toNode.pos] = node.cost + toNode.cost;
                }
            }
            step++;
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }


    static class Node {
        int pos;
        int cost;
        Node(int _pos, int _cost) {
            pos = _pos;
            cost = _cost;
        }
    }
}