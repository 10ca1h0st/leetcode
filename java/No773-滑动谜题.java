class Solution {
    public int slidingPuzzle(int[][] board) {
        int target = 123450;
        int position = value(board);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(position);
        int step = 0;
        Set<Integer> seen = new HashSet<>();
        seen.add(position);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size != 0) {
                int v = queue.poll();
                if(v == target) {
                    return step;
                }
                List<Integer> res = move(v);
                for(int i : res) {
                    if(seen.contains(i)) {
                        continue;
                    }
                    queue.offer(i);
                    seen.add(i);
                }
                size--;
            }
            step++;
        }
        return -1;
    }

    public int value(int[][] board) {
        int v = 0;
        for(int[] i : board) {
           for(int j : i) {
               v = v * 10 + j;
           }
        }
        return v;
    }

    public List<Integer> move(int position) {
        Deque<Integer> split = new ArrayDeque<>();
        int _position = position;
        while(_position != 0) {
            split.push(_position % 10);
            _position = _position / 10;
        }
        if(split.size() < 6) {
            // 012345这种数字
            split.push(0);
        }
        int[][] board = new int[2][3];
        int index = 0;
        for(int i : split) {
            board[index/board[0].length][index%board[0].length] = i;
            index++;
        }
        int zeroI = 0;
        int zeroJ = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                }
            }
        }
        // 上下左右
        int[] xMove = new int[] {0, 0, -1, 1};
        int[] yMove = new int[] {-1, 1, 0, 0};
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < xMove.length; i++) {
            if(zeroI+xMove[i] >= 0 && zeroI+xMove[i] < board.length &&
               zeroJ+yMove[i] >= 0 && zeroJ+yMove[i] < board[0].length
            ) {
                int origin = board[zeroI+xMove[i]][zeroJ+yMove[i]];
                board[zeroI+xMove[i]][zeroJ+yMove[i]] = 0;
                board[zeroI][zeroJ] = origin;
                res.add(value(board));
                board[zeroI+xMove[i]][zeroJ+yMove[i]] = origin;
                board[zeroI][zeroJ] = 0;
            }
        }
        return res;
    }
}