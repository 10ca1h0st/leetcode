import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class huawei081803 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        line = bf.readLine();
        int M = Integer.valueOf(line.split("\\s+")[0]);
        int N = Integer.valueOf(line.split("\\s+")[1]);
        int[][] matrix = new int[M][N];
        for (int i = 0; i < M; i++) {
            line = bf.readLine();
            String[] split = line.split("\\s+");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.valueOf(split[j]);
            }
        }
        line = bf.readLine();
        int startX = Integer.valueOf(line.split("\\s+")[0]);
        int startY = Integer.valueOf(line.split("\\s+")[1]);
        int endX = Integer.valueOf(line.split("\\s+")[2]);
        int endY = Integer.valueOf(line.split("\\s+")[3]);
//        System.out.println("here");
        int type = matrix[startX][startY];
        int empty = 0;
        Set<Integer> seen = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(startX*N + startY, 0);
        seen.add(startX*N + startY);
        // queue存放这4维数组 前2维放着坐标，[2]放着到达坐标走的折线数，[3]放着当前点的上一个点在当前点的位置，
        // 0表示上，1表示左，2表示下，3表示右，-1表示当前点为初始点
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startX, startY, 0, -1});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] position = queue.poll();
//                if (position[0] == endX && position[1] == endY) {
//                    System.out.println(position[2]);
//                    return;
//                }
                // 可以向左
                if (position[1] > 0) {
                    if (matrix[position[0]][position[1]-1] == empty) {
                        if (position[3] == 1) {
                            // 从左到达当前点，没必要回去
                        } else {
                            if (position[3] == 3) {
                                // 右边来的，同一条直线
                                if (!map.containsKey(position[0]*N + position[1]-1) || map.get(position[0]*N + position[1]-1) > position[2]) {
                                    queue.offer(new int[]{position[0], position[1] - 1, position[2], 3});
                                    map.put(position[0]*N + position[1]-1, position[2]);
                                }
                            } else {
                                if (!map.containsKey(position[0]*N + position[1]-1) || map.get(position[0]*N + position[1]-1) > position[2]+1) {
                                    queue.offer(new int[]{position[0], position[1] - 1, position[2] + 1, 3});
                                    map.put(position[0]*N + position[1]-1, position[2]+1);
                                }
                            }
                            seen.add(position[0]*N + position[1]-1);
                        }
                    } else if (matrix[position[0]][position[1]-1] == type) {
                        if (position[0] == endX && position[1]-1 == endY) {
                            if (position[3] == 1 || position[3] == 3) {
                                System.out.println(position[2]);
                            } else {
                                System.out.println(position[2]+1);
                            }
                            return;
                        }
                    }
                }
                // 可以向右
                if (position[1] < N-1) {
                    if (matrix[position[0]][position[1]+1] == empty) {
                        if (position[3] == 3) {
                            // 从右到达当前点，没必要回去
                        } else {
                            if (position[3] == 1) {
                                // 左边来的，同一条直线
                                if (!map.containsKey(position[0]*N + position[1]+1) || map.get(position[0]*N + position[1]+1) > position[2]) {
                                    queue.offer(new int[]{position[0], position[1] + 1, position[2], 1});
                                    map.put(position[0]*N + position[1]+1, position[2]);
                                }
                            } else {
                                if (!map.containsKey(position[0]*N + position[1]+1) || map.get(position[0]*N + position[1]+1) > position[2]+1) {
                                    queue.offer(new int[]{position[0], position[1] + 1, position[2]+1, 1});
                                    map.put(position[0]*N + position[1]+1, position[2]+1);
                                }
                            }
                            seen.add(position[0]*N + position[1]+1);
                        }
                    } else if (matrix[position[0]][position[1]+1] == type) {
                        if (position[0] == endX && position[1]+1 == endY) {
                            if (position[3] == 1 || position[3] == 3) {
                                System.out.println(position[2]);
                            } else {
                                System.out.println(position[2]+1);
                            }
                            return;
                        }
                    }
                }
                // 可以向上
                if (position[0] > 0) {
                    if (matrix[position[0]-1][position[1]] == empty) {
                        if (position[3] == 0) {
                            // 从上到达当前点，没必要回去
                        } else {
                            if (position[3] == 2) {
                                // 下边来的，同一条直线
                                if (!map.containsKey((position[0]-1)*N + position[1]) || map.get((position[0]-1)*N + position[1]) > position[2])
                                {
                                    queue.offer(new int[]{position[0] - 1, position[1], position[2], 2});
                                    map.put((position[0]-1)*N + position[1], position[2]);
                                }
                            } else {
                                if (!map.containsKey((position[0]-1)*N + position[1]) || map.get((position[0]-1)*N + position[1]) > position[2]+1)
                                {
                                    queue.offer(new int[]{position[0] - 1, position[1], position[2]+1, 2});
                                    map.put((position[0]-1)*N + position[1], position[2]+1);
                                }
                            }
                            seen.add((position[0]-1)*N + position[1]);
                        }
                    } else if (matrix[position[0]-1][position[1]] == type) {
                        if (position[0]-1 == endX && position[1] == endY) {
                            if (position[3] == 0 || position[3] == 2) {
                                System.out.println(position[2]);
                            } else {
                                System.out.println(position[2]+1);
                            }
                            return;
                        }
                    }
                }
                // 可以向下
                if (position[0] < M-1) {
                    if (matrix[position[0]+1][position[1]] == empty) {
                        if (position[3] == 2) {
                            // 从下到达当前点，没必要回去
                        } else {
                            if (position[3] == 0) {
                                // 上边来的，同一条直线
                                if (!map.containsKey((position[0]+1)*N + position[1]) || map.get((position[0]+1)*N + position[1]) > position[2]) {
                                    queue.offer(new int[]{position[0] + 1, position[1], position[2], 0});
                                    map.put((position[0]+1)*N + position[1], position[2]);
                                }
                            } else {
                                if (!map.containsKey((position[0]+1)*N + position[1]) || map.get((position[0]+1)*N + position[1]) > position[2]+1) {
                                    queue.offer(new int[]{position[0] + 1, position[1], position[2]+1, 0});
                                    map.put((position[0]+1)*N + position[1], position[2]+1);
                                }
                            }
                            seen.add((position[0]+1)*N + position[1]);
                        }
                    } else if (matrix[position[0]+1][position[1]] == type) {
                        if (position[0]+1 == endX && position[1] == endY) {
                            if (position[3] == 0 || position[3] == 2) {
                                System.out.println(position[2]);
                            } else {
                                System.out.println(position[2]+1);
                            }
                            return;
                        }
                    }
                }
                size--;
            }
        }
        System.out.println(-1);
    }
}
