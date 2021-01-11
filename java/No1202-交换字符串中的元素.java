public class Solution {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        // 第 1 步：将任意交换的结点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if (hashMap.containsKey(root)) {
                hashMap.get(root).offer(charArray[i]);
            } else {
                // PriorityQueue<Character> minHeap = new PriorityQueue<>();
                // minHeap.offer(charArray[i]);
                // hashMap.put(root, minHeap);
                // 上面三行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
                hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
            }
        }

        // 第 3 步：重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();
    }

    private class UnionFind {

        private int[] parent;
        /**
         * 以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
         */
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度仅加了 1
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度不变
            } else {
                // 同理，此时以 rootX 为根结点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}
// 此解法超出内存限制
class Solution {
    int[][] graph;
    boolean[] visited;
    int length;
    List<Integer> connected;
    

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        // pairs中的索引对，可以根据它们是否是连通的分割
        // 例如，[1, 2]和[0, 2]这两个索引对，因为都有索引2，所以，这两个索引对是连通的
        // 即，索引0, 1, 2所对应的三个字符可以互相任意交换位置
        // 而索引对[0, 3]和[1, 2]就不是连通的
        length = s.length();
        graph = new int[length][length];
        visited = new boolean[length];
        connected = new ArrayList<>();
        for(List<Integer> pair : pairs) {
            int e1 = pair.get(0);
            int e2 = pair.get(1);
            graph[e1][e2] = 1;
            graph[e2][e1] = 1;
        }
        for(int i = 0; i < length; i++) {
            graph[i][i] = 1;
        }
        char[] sChars = s.toCharArray();
        for(int i = 0; i < length; i++) {
            connected.clear();
            if(!visited[i]) {
                dfs(i);
            }
            if(!connected.isEmpty()) {
                Collections.sort(connected);
                // 现在connected记录着连通的索引，这些索引对应的字符可以任意交换,且connected中的索引是排好序的
                char[] chs = new char[connected.size()];
                int j = 0;
                for(int index : connected) {
                    chs[j] = sChars[index];
                    j++;
                }
                Arrays.sort(chs);
                j = 0;
                for(int index : connected) {
                    sChars[index] = chs[j];
                    j++;
                }
            }
        }
        return String.valueOf(sChars);
    }

    void dfs(int source) {
        for(int i = 0; i < length; i++) {
            if(!visited[i] && graph[source][i] == 1) {
                visited[i] = true;
                connected.add(i);
                dfs(i);
            }
        }
    }

    
}