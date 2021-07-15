class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : arr) {
            pq.add(e);
        }
        int prev = 1;
        pq.poll();
        while (!pq.isEmpty()) {
            int top = pq.poll();
            if (top - prev > 1) {
                prev = prev + 1;
            } else {
                prev = top;
            }
        }
        return prev;
    }
}