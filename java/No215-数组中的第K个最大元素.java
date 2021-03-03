class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int target = nums.length - k;
        int i = partition(nums, start, end);
        while(i != target) {
            if(i > target) {
                end = i - 1;
                i = partition(nums, start, end);
            }
            else {
                start = i + 1;
                i = partition(nums, start, end);
            }
        }
        return nums[i];
    }

    // 以nums[end]为flag，以flag为中间元素将nums[start...end]分割为两部分，并返回最终flag应放置位置的索引，注意end>=start
    public int partition(int[] nums, int start, int end) {
        int flag = nums[end];
        int i = start - 1;
        // 小于等于索引i的地方放着小于flag的元素
        for(int j = start; j < end; j++) {
            if(nums[j] < flag) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, i+1, end);
        return i+1;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 使用堆
class Solution {
    public int findKthLargest(int[] nums, int k) {
        MyPriorityQueue pq = new MyPriorityQueue(nums.length, true);
        for(int num : nums) {
            pq.insert(num);
        }
        int delCount = k;
        int ret = 0;
        while(delCount > 0) {
            ret = pq.delete();
            delCount--;
        }
        return ret;
    }

    // 固定大小的优先队列
    class MyPriorityQueue {
        int[] arr;
        // 大根堆？
        boolean max;
        // 目前堆中含有的元素数量
        int N;

        MyPriorityQueue(int length, boolean max) {
            arr = new int[length];
            this.max = max;
            N = 0;
        }

        void insert(int num) {
            arr[N] = num;
            N++;
            swim(N-1);
        }

        int delete() {
            int ret = arr[0];
            swap(0, N-1);
            N--;
            sink(0);
            return ret;
        }

        void swap(int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        void swim(int i) {
            int parent = (i-1) / 2;
            while(i > 0 && less(parent, i)) {
                swap(parent, i);
                i = parent;
                parent = (i-1) / 2;
            }
        }

        void sink(int i) {
            // arr[i]是否需要下沉
            boolean exch = false;
            int child = 0;
            while((child=2*i+1) < N) {
                exch = false;
                if(child < N-1 && less(child, child+1)) {
                    child++;
                }
                if(less(i, child)) {
                    swap(i, child);
                    exch = true;
                }
                if(exch) {
                    i = child;
                }
                else {
                    break;
                }
            }
        }

        // 如果是大根堆，返回arr[i]<arr[j]
        // 如果是小根堆，返回arr[i]>arr[j]
        boolean less(int i, int j) {
            if(max) {
                return arr[i] < arr[j];
            }
            else {
                return arr[i] > arr[j];
            }
        }
    }
}