class Solution {
    boolean[] marked;
    PriorityQueue<Edge> pq;
    int[][] pointsG;

    public int minCostConnectPoints(int[][] points) {
        int V = points.length;
        marked = new boolean[V];
        pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.weight - e2.weight;
            }
        });
        pointsG = points;
        int ret = 0;
        visit(points[0], 0);
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(marked[e.vIndex] && marked[e.wIndex]) {
                // 这条边的两个顶点都已经选择过了
                continue;
            }
            if(marked[e.vIndex]) {
                // w还没选过
                visit(e.w, e.wIndex);
            }
            else if(marked[e.wIndex]) {
                visit(e.v, e.vIndex);
            }
            ret += e.weight;
        }
        return ret;
    }

    // 遍历所有顶点，将还未访问过的顶点和顶点v组成的边添加到pq中
    // vIndex为顶点v在points中的索引
    void visit(int[] v, int vIndex) {
        marked[vIndex] = true;
        for(int i = 0; i < marked.length; i++) {
            if(!marked[i]) {
                Edge e = new Edge(v, vIndex, pointsG[i], i);
                pq.add(e);
            }
        }
    }
}

class Edge {
    int[] v;
    int vIndex;
    int[] w;
    int wIndex;
    int weight;
    Edge(int[] v, int vIndex, int[] w, int wIndex) {
        this.v = v;
        this.vIndex = vIndex;
        this.w = w;
        this.wIndex = wIndex;
        this.weight = Math.abs(v[0] - w[0]) + Math.abs(v[1] - w[1]);
    }
}