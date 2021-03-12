import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 题目名称：Peaceful Rooks
// 给你一个n*n的网格，再给你m个车（象棋中的车，可以直线移动），
// 刚开始每个车的位置都不会相互吃掉，问需要几步才能使所有的车都在正对角线上。
// https://blog.csdn.net/qq_45719639/article/details/111470570

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        int testcases = Integer.valueOf(bf.readLine());
        while(testcases-- > 0) {
            line = bf.readLine();
            int n = Integer.valueOf(line.split(" ")[0]);
            int m = Integer.valueOf(line.split(" ")[1]);
            UF uf = new UF(n);
            int res = 0;
            while(m-- > 0) {
                line = bf.readLine();
                int row = Integer.valueOf(line.split(" ")[0]);
                int col = Integer.valueOf(line.split(" ")[1]);
                if(row == col) {
                    continue;
                }
                else if(uf.find(row-1) == uf.find(col-1)) {
                    res += 2;
                }
                else {
                    res += 1;
                    uf.union(row-1, col-1);
                }
            }
            System.out.println(res);
        }
    }

    static class UF {
        int[] parent;
        int[] size;
//        连通分量
        int CG;

        UF(int V) {
            parent = new int[V];
            size = new int[V];
            Arrays.fill(size, 1);
            CG = V;
            for(int i = 0; i < V; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if(x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) {
                return false;
            }
            CG--;
            if(size[rootX] > size[rootY]) {
//                小树接到大树下面
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            else {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
            return true;
        }
    }
}