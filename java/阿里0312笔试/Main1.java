import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main1 {
    public static int V;
    public static int E;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        V = Integer.valueOf(line.split(" ")[0]);
        E = Integer.valueOf(line.split(" ")[1]);
        List<Integer>[] graph = new List[V];
        for(int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
//        V个顶点 E条边

        for(int i = 0; i < E; i++) {
            line = bf.readLine();
            int from = Integer.valueOf(line.split(" ")[0]) - 1;
            int to = Integer.valueOf(line.split(" ")[1]) - 1;
            graph[from].add(to);
        }
        int testcases = Integer.valueOf(bf.readLine());
        while(testcases-- > 0) {
            line = bf.readLine();
            int source = Integer.valueOf(line.split(" ")[0]) - 1;
            int target = Integer.valueOf(line.split(" ")[1]) - 1;
            int res = dijkstra(graph, source, target);
            System.out.println(res);
        }
    }

    //    source索引从0开始
    public static int dijkstra(List<Integer>[] graph, int source, int target) {
        int[] distance = new int[V];
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(distance[o1] > distance[o2]) {
                    return 1;
                }
                return -1;
            }
        });
        for(int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;
        pq.add(source);
        while(!pq.isEmpty()) {
            int top = pq.poll();
            if(top == target) {
                return distance[target];
            }
            for(int to : graph[top]) {
                if(distance[to] > distance[top] + 1) {
                    distance[to] = distance[top] + 1;
                    pq.remove(to);
                    pq.add(to);
                }
            }
        }
        return -1;
    }
}