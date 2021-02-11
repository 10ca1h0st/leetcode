class KthLargest {
    private PriorityQueue<Integer> pq;
    private int k;
    public KthLargest(int k, int[] nums){
        pq = new PriorityQueue<Integer>(k);
        this.k = k;
        Arrays.sort(nums);
        // 因为是小根堆，这样add元素，add时不需要上浮操作
        for(int i = Math.max(0, nums.length-k); i < nums.length; i++) {
            pq.add(nums[i]);
        }
    }
    
    public int add(int val) {
        if(pq.size() == k) {
            if(val > pq.peek()) {
                pq.poll();
                pq.add(val);
            }
        }
        else {
            pq.add(val);
        }
        return pq.peek();
    }
}