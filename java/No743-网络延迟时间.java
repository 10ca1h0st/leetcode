class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<int[]>());
        }
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int cost = time[2];
            edges.get(from-1).add(new int[] {to-1, cost});
        }
        int ans = dijkstra(edges, k-1);
        return ans;
    }

// 计算从source遍历全图的最短时间
    private int dijkstra(List<List<int[]>> edges, int source) {
        int[] distances = new int[edges.size()];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[source] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer v1, Integer v2) {
                if (distances[v1] < distances[v2]) {
                    return -1;
                } else if (distances[v1] > distances[v2]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (int v = 0; v < distances.length; v++) {
            pq.add(v);
        }
        int maxCost = 0;
        while (!pq.isEmpty()) {
            int closest = pq.poll();
            if (distances[closest] == Integer.MAX_VALUE) {
                return -1;
            }
            maxCost = Math.max(maxCost, distances[closest]);
            for (int[] edge : edges.get(closest)) {
                int to = edge[0];
                int cost = edge[1];
                if (distances[to] > distances[closest] + cost) {
                    distances[to] = distances[closest] + cost;
                    pq.remove(to);
                    pq.offer(to);
                }
            }
        }
        return maxCost;
    }
}