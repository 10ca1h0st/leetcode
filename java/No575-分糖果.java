class Solution {
    public int distributeCandies(int[] candyType) {
        int count  = candyType.length;
        Arrays.sort(candyType);
        int prev = -100001;
        int sister = 0;
        for (int i= 0; i < count; i++) {
            if (candyType[i] != prev) {
                sister++;
                if (sister == count/2) {
                    return sister;
                }
            }
            prev = candyType[i];
        }
        return sister;
    }
}