class Solution {
    public int[] ans;
    public boolean done;
    public int k;
    public int[] smallestK(int[] arr, int k) {
        ans = new int[k];
        done = false;
        this.k = k;
        quicksort(arr, 0, arr.length-1);
        return ans;
    }

    public void quicksort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int pivot = partition(arr, start, end);
        if (pivot == k-1) {
            System.arraycopy(arr, 0, ans, 0, k);
            done = true;
            return;
        }
        quicksort(arr, start, pivot-1);
        if (done) {
            return;
        }
        quicksort(arr, pivot+1, end);
    }

    public int partition(int[] arr, int start, int end) {
        int pivot = start;
        int index = start + 1;
        for (int i = index; i <= end; i++) {
            if (arr[i] < arr[pivot]) {
                int temp = arr[index];
                arr[index] = arr[i];
                arr[i] = temp;
                index++;
            }
        }
        int temp = arr[index-1];
        arr[index-1] = arr[pivot];
        arr[pivot] = temp;
        return index-1;
    }
}