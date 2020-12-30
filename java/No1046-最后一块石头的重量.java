class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if(a > b) {
                    return -1;
                }
                else if(a < b) {
                    return 1;
                }
                return 0;
            }
        });
        for(int stone : stones) {
            heap.add(stone);
        }
        int a = 0;
        int b = 0;
        while(!heap.isEmpty()) {
            a = heap.poll();
            if(heap.isEmpty()) {
                return a;
            }
            b = heap.poll();
            if(a == b) {
                continue;
            }
            else if(a > b) {
                heap.add(a-b);
            }
            else {
                heap.add(b-a);
            }
        }
        return 0;
    }
}