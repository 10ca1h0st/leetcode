class MedianFinder {

    private PriorityQueue<Integer> big;
    private PriorityQueue<Integer> small;
    private int size;

    /** initialize your data structure here. */
    public MedianFinder() {
        big = new PriorityQueue<>();
        small = new PriorityQueue<>(Collections.reverseOrder());
        size = 0;
    }
    
    public void addNum(int num) {
        if (size%2 == 0) {
            big.offer(num);
            small.offer(big.poll());
        } else {
            // 在加入元素之前，总元素个数为奇数，此时small.size()-big.size()==1
            if (num >= small.peek()) {
                big.offer(num);
            } else {
                small.offer(num);
                big.offer(small.poll());
            }
        }
        size++;
    }
    
    public double findMedian() {
        if (size%2 == 0) {
            return (double)(small.peek() + big.peek()) / 2;
        } else {
            return small.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */