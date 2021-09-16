class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int id = i*n + j;
                map.compute(board[i][j], (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                        v.add(id);
                    } else {
                        v.add(id);
                    }
                    return v;
                });
            }
        }
        List<String> ans = new ArrayList<>();
        boolean[][] bools = new boolean[m][n];
        for (String word : words) {
            char first = word.charAt(0);
            for (int pos : map.getOrDefault(first, new ArrayList<Integer>())) {
                int i = pos/n;
                int j = pos%n;
                bools[i][j] = true;
                if (dfs(board, word, i, j, 1, bools)) {
                    ans.add(word);
                    bools[i][j] = false;
                    break;
                }
                bools[i][j] = false;
            }
        }
        return ans;
    }

    public boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        char target = word.charAt(index);
        // 上
        if (i > 0 && !visited[i-1][j] && board[i-1][j] == target) {
            visited[i-1][j] = true;
            if (dfs(board, word, i-1, j, index+1, visited)) {
                visited[i-1][j] = false;
                return true;
            }
            visited[i-1][j] = false;
        }
        // 下
        if (i < board.length-1 && !visited[i+1][j] && board[i+1][j] == target) {
            visited[i+1][j] = true;
            if (dfs(board, word, i+1, j, index+1, visited)) {
                visited[i+1][j] = false;
                return true;
            }
            visited[i+1][j] = false;
        }
        // 左
        if (j > 0 && !visited[i][j-1] && board[i][j-1] == target) {
            visited[i][j-1] = true;
            if (dfs(board, word, i, j-1, index+1, visited)) {
                visited[i][j-1] = false;
                return true;
            }
            visited[i][j-1] = false;
        }
        // 右
        if (j < board[0].length-1 && !visited[i][j+1] && board[i][j+1] == target) {
            visited[i][j+1] = true;
            if (dfs(board, word, i, j+1, index+1, visited)) {
                visited[i][j+1] = false;
                return true;
            }
            visited[i][j+1] = false;
        }
        return false;
    }
}