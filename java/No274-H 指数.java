class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for (int i = 0; i < citations.length / 2; i++) {
            int temp = citations[i];
            citations[i] = citations[citations.length - 1 - i];
            citations[citations.length - 1 - i] = temp;
        }
        for (int i = 0; i < citations.length; i++) {
            if (i+1 > citations[i]) {
                return i;
            }
        }
        return citations.length;
    }
}