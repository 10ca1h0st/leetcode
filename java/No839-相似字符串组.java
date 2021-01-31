// 超时的解法
class Solution {
    // curGroup中包含的字符串的相似字符串组成的集合
    Set<String> similar;
    public int numSimilarGroups(String[] strs) {
        similar = new HashSet<>();
        boolean[] visited = new boolean[strs.length];
        int visitedCount = 0;
        int group = 0;
        List<String> curGroup = new ArrayList<>();
        curGroup.add(strs[0]);
        visited[0] = true;
        visitedCount++;
        group++;
        addSimilarString(curGroup.get(curGroup.size()-1));
        while(visitedCount < strs.length) {
            boolean findNew = false;
            for(int i = 0; i < strs.length; i++) {
                if(visited[i]) {
                    continue;
                }
                if(similar.contains(strs[i])) {
                    findNew = true;
                    visited[i] = true;
                    visitedCount++;
                    curGroup.add(strs[i]);
                    addSimilarString(curGroup.get(curGroup.size()-1));
                }
            }
            if(!findNew) {
                curGroup = new ArrayList<>();
                similar = new HashSet<>();
                for(int i = 0 ; i < strs.length; i++) {
                    if(!visited[i]) {
                        visited[i] = true;
                        visitedCount++;
                        curGroup.add(strs[i]);
                        addSimilarString(curGroup.get(curGroup.size()-1));
                        break;
                    }
                }
                group++;
            }
        }
        return group;
    }

    void addSimilarString(String origin) {
        similar.add(origin);
        char[] originChar = origin.toCharArray();
        for(int i = 0; i < originChar.length; i++) {
            for(int j = i; j < originChar.length; j++) {
                if(originChar[i] == originChar[j]) {
                    continue;
                }
                char tmp = originChar[i];
                originChar[i] = originChar[j];
                originChar[j] = tmp;
                similar.add(String.valueOf(originChar));
                tmp = originChar[i];
                originChar[i] = originChar[j];
                originChar[j] = tmp;
            }
        }
    }
}

// 击败了5%的解法
class Solution {
    UnionFind uf;
    public int numSimilarGroups(String[] strs) {
        uf = new UnionFind(strs.length, strs);
        for(int i = 0; i < strs.length; i++) {
            for(int j = i; j < strs.length; j++) {
                if(isSimilar(i, j)) {
                    uf.union(i, j);
                }
            }
        }
        return uf.setCC;
    }

    // s1与s2是否是相似的，若s1已经位于一个节点数大于1的连通分量之中，如果s2和该连通分量中任何字符串相似，则也算作s1与s2相似
    boolean isSimilar(int s1Index, int s2Index) {
        return uf.isSimilar(s1Index, s2Index);
    }
}

class UnionFind {
    int[] parent;
    int n;
    String[] strs;
    // 连通分量的个数
    int setCC;

    UnionFind(int n, String[] strs) {
        parent = new int[n];
        this.strs = strs;
        setCC = n;
        this.n = n;
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    boolean isSimilar(int x, int y) {
        int xRoot = parent[x];
        int yRoot = parent[y];
        if(xRoot == yRoot) {
            return true;
        }
        for(int i = 0; i < n; i++) {
            if(parent[i] == xRoot) {
                for(int j = 0; j < n; j++) {
                    if(parent[j] == yRoot) {
                        if(_isSimilar(i, j)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean _isSimilar(int x, int y) {
        String s1 = strs[x];
        String s2 = strs[y];
        // table用来表示字符串中字符出现的位置
        int[][] table = new int[26][s1.length()];
        for(int i = 0; i < s1.length(); i++) {
            table[s1.charAt(i)-'a'][i] = 1;
        }
        int diff = 0;
        for(int i = 0; i < s2.length(); i++) {
            if(table[s2.charAt(i)-'a'][i] != 1) {
                diff++;
            }
        }
        if(diff == 2 || diff == 0) {
            return true;
        }
        return false;
    }

    int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) {
            return false;
        }
        parent[y] = x;
        setCC--;
        for(int i = 0; i < n; i++) {
            find(i);
        }
        return true;
    }
}