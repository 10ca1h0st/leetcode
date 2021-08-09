class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        List<Integer> already = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        already.add(1);
        seen.add(1);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : already) {
            for (int j : primes) {
                pq.offer(i*j);
            }
        }
        while (already.size() < n) {
            int gen = pq.poll();
            if (seen.contains(gen)) {
                continue;
            }
            already.add(gen);
            seen.add(gen);
            for (int i : primes) {
                if (((long)i*gen) > Integer.MAX_VALUE) {
                    continue;
                }
                pq.add(i*gen);
            }
        }
        return already.get(n-1);
    }
}