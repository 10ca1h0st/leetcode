class Solution {
    public int snakesAndLadders(int[][] board) {
        int start = 1;
        int rows = board.length;
        int cols = board[0].length;
        int NN = rows * cols;
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        seen.add(start);
        int step = 0;
        while(queue.size() != 0) {
            int size = queue.size();
            while(size > 0) {
                int top = queue.poll();
                if(top == NN) {
                    return step;
                }
                int move = -1;
                for(int i = 1; i <= 6 && top + i <= NN; i++) {
                    move = top + i;
                    if(move == NN) {
                        return step + 1;
                    }
                    int[] ij = getIJ(rows, cols, move);
                    if(seen.contains(move)) {
                        // 这里不直接continue的原因是题目中“玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动”这个限制
                        // seen包含move可能是因为move是一个梯子的终点，或者move是从top+i这种方式获得的
                        // 当seen包含move是因为move是一个梯子的终点时，这是如果move也是一个梯子的起点，则如果直接continue会略过以move为起点的梯子的终点，这样在某些情况下导致需要的步数变多
                        if(board[ij[0]][ij[1]] != -1) {
                            move = board[ij[0]][ij[1]];
                            if(move == NN) {
                                return step + 1;
                            }
                            if(seen.contains(move)) {
                                continue;
                            }
                            queue.offer(move);
                            seen.add(move);
                        }
                        continue;
                    }
                    // int[] ij = getIJ(rows, cols, move);
                    if(board[ij[0]][ij[1]] == -1) {
                        queue.offer(move);
                        seen.add(move);
                    }
                    else {
                        move = board[ij[0]][ij[1]];
                        if(move == NN) {
                            return step + 1;
                        }
                        if(seen.contains(move)) {
                            continue;
                        }
                        queue.offer(move);
                        seen.add(move);
                    }
                }
                size--;
            }
            step++;
        }
        return -1;
    }

    public int[] getIJ(int rows, int cols, int id) {
        int[] res = new int[2];
        res[0] = rows - 1 - (id-1)/cols;
        if(((id-1)/cols) % 2 == 0) {
            res[1] = (id-1) % cols;
        }
        else {
            res[1] = cols - 1 - (id-1)%cols;
        }
        return res;
    }
}