class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int length1 = firstList.length;
        int length2 = secondList.length;
        int i = 0;
        int j = 0;
        List<int[]> list = new ArrayList<>();
        while(i < length1 && j < length2) {
            int[] r = common(firstList[i], secondList[j]);
            if(r != null) {
                list.add(r);
            }
            if(firstList[i][1] >= secondList[j][1]) {
                j++;
            }
            else {
                i++;
            }
        }
        int[][] res = new int[list.size()][2];
        for(int k = 0; k < res.length; k++) {
            res[k][0] = list.get(k)[0];
            res[k][1] = list.get(k)[1];
        }
        return res;
    }

    public int[] common(int[] a, int[] b) {
        if(a[1] < b[0] || b[1] < a[0]) {
            return null;
        }
        if(a[0] <= b[0]) {
            if(a[1] <= b[1]) {
                return new int[] {b[0], a[1]};
            }
            else {
                return new int[] {b[0], b[1]};
            }
        }
        else {
            if(b[1] <= a[1]) {
                return new int[] {a[0], b[1]};
            }
            else {
                return new int[] {a[0], a[1]};
            }
        }
    }
}