// 加了哈希表缓存重复调用的函数结果
class Solution {
    private int ans;
    Map<Integer, Integer> whenFull;

    public int countDigitOne(int n) {
        int[] numbers = new int[10];
        int _n = n;
        int i = 9;
        while (_n > 0) {
            numbers[i] = _n % 10;
            _n = _n / 10;
            i--;
        }
        ans = 0;
        // whenFull.get(i)表示当调用函数backtrace(numbers, i, true)时，给ans带来的增长
        whenFull = new HashMap<>();
        backtrace(numbers, 0, false);
        return ans;
    }

    private void backtrace(int[] numbers, int index, boolean full) {
        if (index == 10) {
            return;
        }
        if (full && whenFull.containsKey(index)) {
            ans += whenFull.get(index);
            return;
        }
        int originAns = ans;
        int start = full ? 9 : numbers[index];
        for (int i = start; i >= 0; i--) {
            if (i == 1) {
                if (full || i < numbers[index]) {
                    ans += (int)Math.pow(10, 9-index);
                } else {
                    int add = 0;
                    for (int j = index+1; j < 10; j++) {
                        add += numbers[j] * (int)Math.pow(10, 9-j);
                    }
                    ans += add+1;
                }
            }
            if (full || i < numbers[index]) {
                backtrace(numbers, index+1, true);
            } else {
                backtrace(numbers, index+1, false);
            }
        }
        if (full) {
            whenFull.put(index, ans - originAns);
        }
    }
}

// 超时35/38
class Solution {
    private int ans;

    public int countDigitOne(int n) {
        int[] numbers = new int[10];
        int _n = n;
        int i = 9;
        while (_n > 0) {
            numbers[i] = _n % 10;
            _n = _n / 10;
            i--;
        }
        ans = 0;
        backtrace(numbers, 0, false);
        return ans;
    }

    private void backtrace(int[] numbers, int index, boolean full) {
        if (index == 10) {
            return;
        }
        int start = full ? 9 : numbers[index];
        for (int i = start; i >= 0; i--) {
            if (i == 1) {
                if (full || i < numbers[index]) {
                    ans += (int)Math.pow(10, 9-index);
                } else {
                    int add = 0;
                    for (int j = index+1; j < 10; j++) {
                        add += numbers[j] * (int)Math.pow(10, 9-j);
                    }
                    ans += add+1;
                }
            }
            if (full || i < numbers[index]) {
                backtrace(numbers, index+1, true);
            } else {
                backtrace(numbers, index+1, false);
            }
        }
    }
}