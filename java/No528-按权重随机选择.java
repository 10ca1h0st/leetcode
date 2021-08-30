class Solution {

    private int total;
    private Random rand;
    private int[] w;

    public Solution(int[] w) {
        total = 0;
        for (int weight : w) {
            total += weight;
        }
        rand = new Random();
        this.w = w;
    }
    
    public int pickIndex() {
        int select = rand.nextInt(total);
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            if (select < sum) {
                return i;
            }
        }
        return -1;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */