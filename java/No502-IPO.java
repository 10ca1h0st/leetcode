class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        Set<Integer> couldDo = new HashSet<>();
        Set<Integer> couldNotDo = new HashSet<>();
        for (int i = 0; i < profits.length; i++) {
            if (capital[i] > w) {
                couldNotDo.add(i);
            } else {
                couldDo.add(i);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i, Integer j) {
                if (profits[i] > profits[j]) {
                    return -1;
                } else if (profits[i] < profits[j]) {
                    return 1;
                }
                return 0;
            }
        });
        for (int i : couldDo) {
            pq.add(i);
        }
        int count = 0;
        int base = w;
        while (!pq.isEmpty() && count < k) {
            int task = pq.poll();
            base += profits[task];
            List<Integer> deleted = new ArrayList<>();
            for (int i : couldNotDo) {
                if (capital[i] <= base) {
                    pq.add(i);
                    deleted.add(i);
                    couldDo.add(i);
                }
            }
            couldNotDo.removeAll(deleted);
            count++;
        }
        return base;
    }
}