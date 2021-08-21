import java.util.*;


public class wangyihulianwang04 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算最小航行费用
     * @param input int整型二维数组 二维网格
     * @return int整型
     */
    public int minSailCost (int[][] input) {
        // write code here
        final int costWater = 2;
        final int water = 0;
        final int costLand = 1;
        final int land = 1;

        final int bad = 2;

        Queue<Position> queue = new ArrayDeque<>();
        int[][] visited = new int[input.length][input[0].length];
        visited[0][0] = -1;
        queue.offer(new Position(0, 0, 0));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Position p = queue.poll();
                // 向上
                if (p.x > 0 && input[p.x-1][p.y] != bad) {
                    if (input[p.x-1][p.y] == land) {
                        if (visited[p.x-1][p.y] == 0 || visited[p.x-1][p.y] > p.cost+costLand) {
                            // 没到过[p.x-1][p.y]或者，曾经到过但是花费的费用比这次高
                            Position next = new Position(p.x-1, p.y, p.cost + costLand);
                            queue.offer(next);
                            visited[p.x-1][p.y] = next.cost;
                        }
                    } else {
                        if (visited[p.x-1][p.y] == 0 || visited[p.x-1][p.y] > p.cost+costWater) {
                            Position next = new Position(p.x-1, p.y, p.cost + costWater);
                            queue.offer(next);
                            visited[p.x-1][p.y] = next.cost;
                        }
                    }
                }
                // 向下
                if (p.x < input.length-1 && input[p.x+1][p.y] != bad) {
                    if (input[p.x+1][p.y] == land) {
                        if (visited[p.x+1][p.y] == 0 || visited[p.x+1][p.y] > p.cost+costLand) {
                            // 没到过[p.x+1][p.y]或者，曾经到过但是花费的费用比这次高
                            Position next = new Position(p.x+1, p.y, p.cost + costLand);
                            queue.offer(next);
                            visited[p.x+1][p.y] = next.cost;
                        }
                    } else {
                        if (visited[p.x+1][p.y] == 0 || visited[p.x+1][p.y] > p.cost+costWater) {
                            Position next = new Position(p.x+1, p.y, p.cost + costWater);
                            queue.offer(next);
                            visited[p.x+1][p.y] = next.cost;
                        }
                    }
                }
                // 向左
                if (p.y > 0 && input[p.x][p.y-1] != bad) {
                    if (input[p.x][p.y-1] == land) {
                        if (visited[p.x][p.y-1] == 0 || visited[p.x][p.y-1] > p.cost+costLand) {
                            Position next = new Position(p.x, p.y-1, p.cost + costLand);
                            queue.offer(next);
                            visited[p.x][p.y-1] = next.cost;
                        }
                    } else {
                        if (visited[p.x][p.y-1] == 0 || visited[p.x][p.y-1] > p.cost+costWater) {
                            Position next = new Position(p.x, p.y-1, p.cost + costWater);
                            queue.offer(next);
                            visited[p.x][p.y-1] = next.cost;
                        }
                    }
                }
                // 向右
                if (p.y < input[0].length-1 && input[p.x][p.y+1] != bad) {
                    if (input[p.x][p.y+1] == land) {
                        if (visited[p.x][p.y+1] == 0 || visited[p.x][p.y+1] > p.cost+costLand) {
                            Position next = new Position(p.x, p.y+1, p.cost + costLand);
                            queue.offer(next);
                            visited[p.x][p.y+1] = next.cost;
                        }
                    } else {
                        if (visited[p.x][p.y+1] == 0 || visited[p.x][p.y+1] > p.cost+costWater) {
                            Position next = new Position(p.x, p.y+1, p.cost + costWater);
                            queue.offer(next);
                            visited[p.x][p.y+1] = next.cost;
                        }
                    }
                }
                size--;
            }
        }
        if (visited[input.length-1][input[0].length-1] != 0) {
            return visited[input.length-1][input[0].length-1];
        }
        return -1;
    }

    private static class Position {
        int x, y;
        int cost;
        Position(int _x, int _y, int _cost) {
            x = _x;
            y = _y;
            cost = _cost;
        }
    }
}