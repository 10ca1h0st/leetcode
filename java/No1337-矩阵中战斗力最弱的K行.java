class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        List<int[]> records = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            int sum = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    break;
                }
                sum += 1;
            }
            records.add(new int[] {i, sum});
        }
        Collections.sort(records, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] < b[1]) {
                    return -1;
                } else if (a[1] > b[1]) {
                    return 1;
                } else if (a[0] < b[0]) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = records.get(i)[0];
        }
        return ans;
    }
}