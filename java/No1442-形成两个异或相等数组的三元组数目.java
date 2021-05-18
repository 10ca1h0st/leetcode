class Solution {
    public int countTriplets(int[] arr) {
        int[] xorPrefix = new int[arr.length + 1];
        int xor = xorPrefix[0];
        for(int i = 1; i <= arr.length; i++) {
            xor = xor ^ arr[i-1];
            xorPrefix[i] = xor;
        }
        int ans = 0;
        for(int i = 0; i < arr.length-1; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                for(int k = j; k < arr.length; k++) {
                    if(check(xorPrefix, i, j, k)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public boolean check(int[] xorPrefix, int i, int j ,int k) {
        int a = xorPrefix[j] ^ xorPrefix[i];
        int b = xorPrefix[k+1] ^ xorPrefix[j];
        return a==b;
    }
}

// 官方解答 只用了一重循环
class Solution {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] ^ arr[i];
        }
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        Map<Integer, Integer> total = new HashMap<Integer, Integer>();
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            if (cnt.containsKey(s[k + 1])) {
                ans += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
            }
            cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
            total.put(s[k], total.getOrDefault(s[k], 0) + k);
        }
        return ans;
    }
}