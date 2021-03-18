import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[33];
        Random rand = new Random();
        for(int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
        }
//        QuickSort.sort(arr);
//        MergeSort.sort(arr);
        MergeSortNotRecursive.sort(arr);
//        QuickSortNotRecursive.sort(arr);
        for(int num : arr) {
            System.out.print(num+" ");
        }
    }
}

class QuickSortNotRecursive {

    static void sort(int[] arr) {
        int length = arr.length;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        queue.offer(length-1);
        while(!queue.isEmpty()) {
            int lo = queue.poll();
            int hi = queue.poll();
            int pivot = partition(arr, lo, hi);
            if(lo < pivot-1) {
                queue.offer(lo);
                queue.offer(pivot-1);
            }
            if(hi > pivot+1) {
                queue.offer(pivot+1);
                queue.offer(hi);
            }
        }
    }

    static int partition(int[] arr, int lo, int hi) {
        int pivot = lo;
        int index = lo + 1;
        for(int i = index; i <= hi; i++) {
            if(arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index-1);
        return index-1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class MergeSortNotRecursive {
    static int[] aux;

    static void sort(int[] arr) {
        int length = arr.length;
        aux = new int[length];
        int size = 1;
        for(; size < length; size += size) {
            for(int i = 0; i + size - 1 < length - 1; i += 2*size) {
                merge(arr, i, i + size - 1, Math.min(i + size + size - 1, length-1));
            }
        }
    }

    static void merge(int[] arr, int lo, int middle, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo;
        int j = middle + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > middle) {
                arr[k] = aux[j++];
            }
            else if(j > hi) {
                arr[k] = aux[i++];
            }
            else if(aux[i] <= aux[j]) {
                arr[k] = aux[i++];
            }
            else  {
                arr[k] = aux[j++];
            }
        }
    }
}

class MergeSort {
    static int[] aux;

    static void sort(int[] arr) {
        int length = arr.length;
        aux = new int[length];
        sort(arr, 0 ,length-1);
    }

    static void sort(int[] arr, int lo, int hi) {
        if(lo >= hi) {
            return;
        }
        int middle = lo + (hi - lo) / 2;
        sort(arr, lo, middle);
        sort(arr, middle+1, hi);
        merge(arr, lo, middle, hi);
    }

    static void merge(int[] arr, int lo, int middle, int hi) {
        System.arraycopy(arr, lo, aux, lo, hi - lo + 1);
        int i = lo;
        int j = middle + 1;
        for(int k = lo; k <= hi; k++) {
            if(i > middle) {
                arr[k] = aux[j++];
            }
            else if(j > hi) {
                arr[k] = aux[i++];
            }
            else if(aux[i] <= aux[j]) {
                arr[k] = aux[i++];
            }
            else {
                arr[k] = aux[j++];
            }
        }
    }
}

class QuickSort {

    static void sort(int[] arr) {
        int length = arr.length;
        sort(arr, 0, length-1);
    }

    static void sort(int[] arr, int lo, int hi) {
        if(hi <= lo) {
            return;
        }
        int pivot = partition(arr, lo, hi);
        sort(arr, lo, pivot-1);
        sort(arr, pivot+1, hi);
    }

    static int partition(int[] arr, int lo, int hi) {
        int pivot = lo;
//        从index开始（包含inidex），都是大于等于arr[pivot]的
        int index = pivot + 1;
        for(int i = index; i <= hi; i++) {
            if(arr[i] < arr[pivot]) {
                swap(arr, i ,index);
                index++;
            }
        }
        swap(arr, pivot, index-1);
        return index-1;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
