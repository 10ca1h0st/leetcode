class Solution {
    public boolean isValidSudoku(char[][] board) {
        List<Integer>[] positions = new List[9];
        for (int i = 0; i < 9; i++) {
            positions[i] = new ArrayList<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (Character.isDigit(ch)) {
                    positions[ch-'1'].add(i*9+j);
                }
            }
        }
        // 验证规则1和2
        // rows[i][j]为true表示第i行已经出现过数字j+1
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        // 验证规则3
        // squares[i][j]为true表示第i个小方格已经出现过数字j+1
        boolean[][] squares = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            List<Integer> pos = positions[i];
            for (int id : pos) {
                int row = id/9;
                int col = id%9;
                if (rows[row][i]) {
                    return false;
                }
                rows[row][i] = true;
                if (cols[col][i]) {
                    return false;
                }
                cols[col][i] = true;
                // 计算当前数字所处的方块
                int square = (row/3) * 3 + col/3;
                if (squares[square][i]) {
                    return false;
                }
                squares[square][i] = true;
            }
        }
        return true;
    }
}