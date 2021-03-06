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