class Solution {
	// 使用floyd算法计算连通分量的个数
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(isConnected[i][k] == 1 && isConnected[k][j] == 1 && isConnected[i][j] != 1) {
                        isConnected[i][j] = 1;
                    }
                }
            }
        }
        Set<Integer> arrived = new HashSet<>();
        int ret = 0;
        for(int i = 0; i < n; i++) {
            if(arrived.contains(i)) {
                continue;
            }
            ret++;
            for(int j = 0; j < n; j++) {
                if(isConnected[i][j] == 1) {
                    arrived.add(j);
                }
            }
        }
        return ret;
    }
}