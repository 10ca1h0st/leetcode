// 官方解答 BFS
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = rec.getOrDefault(site, new ArrayList<Integer>());
                for (int j : list) {
                    edge[i][j] = edge[j][i] = true;
                }
                list.add(i);
                rec.put(site, list);
            }
        }

        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> que = new LinkedList<Integer>();
        for (int site : rec.getOrDefault(source, new ArrayList<Integer>())) {
            dis[site] = 1;
            que.offer(site);
        }
        while (!que.isEmpty()) {
            int x = que.poll();
            for (int y = 0; y < n; y++) {
                if (edge[x][y] && dis[y] == -1) {
                    dis[y] = dis[x] + 1;
                    que.offer(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int site : rec.getOrDefault(target, new ArrayList<Integer>())) {
            if (dis[site] != -1) {
                ret = Math.min(ret, dis[site]);
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/bus-routes/solution/gong-jiao-lu-xian-by-leetcode-solution-yifz/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


//2021-6-28再次解答，超时，43/45
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(target == source) {
            return 0;
        }
        int maxStop = 0;
        for(int[] route : routes) {
            for(int stop : route) {
                if(stop > maxStop) {
                    maxStop = stop;
                }
            }
        }
        // distance[i]表示从source到i的最小换乘次数
        int[] distance = new int[maxStop + 1];
        for(int i = 0; i < distance.length; i++) {
            distance[i] = routes.length + 1;
        }
        distance[source] = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return distance[a] - distance[b];
            }
        });
        pq.offer(source);
        List<Integer> neighbors = null;
        Set<Integer> seen = new HashSet<>();
        seen.add(source);
        while(!pq.isEmpty()) {
            int top = pq.poll();
            if(top == target) {
                return distance[target];
            }
            seen.add(top);
            neighbors = getNeighbors(routes, seen, top);
            if(top == source) {
                for(int neighbor : neighbors) {
                    distance[neighbor] = 1;
                    pq.offer(neighbor);
                }
                continue;
            }
            for(int neighbor : neighbors) {
                if(distance[neighbor] > distance[top] + 1) {
                    distance[neighbor] = distance[top] + 1;
                    pq.remove(neighbor);
                    pq.offer(neighbor);
                    // seen.add(neighbor);
                }
            }
        }
        return -1;
    }

    public List<Integer> getNeighbors(int[][] routes, Set<Integer> seen, int center) {
        List<Integer> res = new ArrayList<>();
        int index = 0;
        for(int[] route : routes) {
            boolean isNeighbor = false;
            for(int stop : route) {
                if(stop == center) {
                    isNeighbor = true;
                    break;
                }
            }
            if(isNeighbor) {
                for(int stop : route) {
                    if(!seen.contains(stop)) {
                        res.add(stop);
                    }
                }
            }
            index++;
        }
        return res;
    }
}



class Solution {

    static int res;
    static int tempRes;
    static Set<Integer> visited;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) {
            return 0;
        }
        visited = new HashSet<>();
        res = Integer.MAX_VALUE;
        tempRes = 1;
        Map<Integer, List<Node>> graph = new HashMap<>();
        for(int num = 0; num < routes.length; num++) {
            int[] route = routes[num];
            for(int i = 0; i < route.length; i++) {
                List<Node> list = graph.getOrDefault(route[i], new ArrayList<>());
                for(int j = 0; j < route.length; j++) {
                    if(j == i) {
                        continue;
                    }
                    list.add(new Node(route[j], num+1));
                }
                graph.put(route[i], list);
            }
        }
        // dfs超时
        // visited.add(source);
        // for(Node node : graph.getOrDefault(source, new ArrayList<>())) {
        //     int curBusStop = node.busStop;
        //     int curBusNum = node.busNum;
        //     dfs(graph, curBusStop, curBusNum, target);
        // }
        // if(res == Integer.MAX_VALUE) {
        //     return -1;
        // }
        // return res;

        // 使用bfs
        Queue<BfsNode> queue = new ArrayDeque<>();
        for(Node node : graph.getOrDefault(source, new ArrayList<>())) {
            visited.add(node.busStop);
            queue.add(new BfsNode(node.busStop, node.busNum, 1, new HashSet<>()));
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                BfsNode top = queue.poll();
                if(top.busStop == target) {
                    return top.transfer;
                }
                for(Node node : graph.getOrDefault(top.busStop, new ArrayList<>())) {
                    if(visited.contains(node.busStop)) {
                        continue;
                    }
                    if(top.token.contains(node.busNum) && node.busNum != top.busNum) {
                        continue;
                    }
                    visited.add(node.busStop);
                    if(node.busNum == top.busNum) {
                        queue.offer(new BfsNode(node.busStop, node.busNum, top.transfer, top.token));
                    }
                    else {
                        queue.offer(new BfsNode(node.busStop, node.busNum, top.transfer+1, top.token));
                    }
                }
            }
        }
        return -1;
    }

    public static void dfs(Map<Integer, List<Node>> graph, int curBusStop, int curBusNum, int target) {
        if(curBusStop == target) {
            res = Math.min(res, tempRes);
            return;
        }
        visited.add(curBusStop);
        for(Node node : graph.getOrDefault(curBusStop, new ArrayList<>())) {
            if(visited.contains(node.busStop)) {
                continue;
            }
            if(node.busNum == curBusNum) {
                dfs(graph, node.busStop, node.busNum, target);
            }
            else {
                tempRes++;
                dfs(graph, node.busStop, node.busNum, target);
                tempRes--;
            }
        }
        visited.remove(curBusStop);
        return;
    }
}

class BfsNode {
    int transfer;
    int busStop;
    int busNum;
    // 从根节点到目前节点，乘坐过的公交车线路的集合
    Set<Integer> token;
    BfsNode(int busStop, int busNum, int transfer, Set<Integer> token) {
        this.busStop = busStop;
        this.busNum = busNum;
        this.transfer = transfer;
        this.token = token;
        token.add(busNum);
    }
}

class Node {
    int busStop;
    int busNum;
    Node(int busStop, int busNum) {
        this.busStop = busStop;
        this.busNum = busNum;
    }
}

// 使用Dijkstra，初始时，将每一个公交站当做节点，对每一条线路中的所有节点，
// 将它们两两连接起来，且边的权值设为1，即代表从一个节点到另一个节点，只要乘一次公交，
// 图建立完成后，就是求源节点到目标节点之间的最短路径，使用Dijkstra算法
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // 邻接表表示图
        Map<Integer, List<Edge>> graph = new HashMap<>();
        // 顶点集合
        Set<Integer> V = new HashSet<>();
        for(int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            for(int j = 0; j < route.length; j++) {
                V.add(route[j]);
                List<Edge> list = graph.getOrDefault(route[j], new ArrayList<>());
                for(int k = 0; k < route.length; k++) {
                    if(k == j) {
                        continue;
                    }
                    list.add(new Edge(route[k], 1));
                }
                graph.put(route[j], list);
            }
        }
        return Dijkstra(graph, V, source, target);
    }

    public static int Dijkstra(Map<Integer, List<Edge>> graph, Set<Integer> V, int source, int target) {
        // 节点到source的距离
        Map<Integer, Integer> distance = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                if(distance.get(a) > distance.get(b)) {
                    return 1;
                }
                else if(distance.get(a) < distance.get(b)) {
                    return -1;
                }
                return 0;
            }
        });
        for(int v : V) {
            distance.put(v, 10000000);
        }
        distance.put(source, 0);
        pq.offer(source);
        while(!pq.isEmpty()) {
            int top = pq.poll();
            if(top == target) {
                return distance.get(top);
            }
            // 放松节点操作
            for(Edge e : graph.getOrDefault(top, new ArrayList<>())) {
                int v = e.dstV;
                if(distance.get(v) > distance.get(top) + e.weight) {
                    distance.put(v, distance.get(top) + e.weight);
                    if(pq.contains(v)) {
                        pq.remove(v);
                    }
                    pq.offer(v);
                }
            }
        }
        return -1;
    }

    static class Edge {
        int dstV;
        int weight;
        Edge(int dstV, int weight) {
            this.dstV = dstV;
            this.weight = weight;
        }
    }
}

// 官方代码
import java.awt.Point;

class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S==T) return 0;
        int N = routes.length;

        List<List<Integer>> graph = new ArrayList();
        for (int i = 0; i < N; ++i) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList());
        }
        Set<Integer> seen = new HashSet();
        Set<Integer> targets = new HashSet();
        Queue<Point> queue = new ArrayDeque();

        // Build the graph.  Two buses are connected if
        // they share at least one bus stop.
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }

        // Initialize seen, queue, targets.
        // seen represents whether a node has ever been enqueued to queue.
        // queue handles our breadth first search.
        // targets is the set of goal states we have.
        for (int i = 0; i < N; ++i) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                seen.add(i);
                queue.offer(new Point(i, 0));
            }
            if (Arrays.binarySearch(routes[i], T) >= 0)
                targets.add(i);
        }

        while (!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.x, depth = info.y;
            if (targets.contains(node)) return depth+1;
            for (Integer nei: graph.get(node)) {
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    queue.offer(new Point(nei, depth+1));
                }
            }
        }

        return -1;
    }

    public boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) return true;
            if (A[i] < B[j]) i++; else j++;
        }
        return false;
    }
}

作者：LeetCode
链接：https://leetcode-cn.com/problems/bus-routes/solution/gong-jiao-lu-xian-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。