class Solution {
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        Map<Integer, Integer> pos = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; ++i) {
            pos.put(target[i], i);
        }
        List<Integer> d = new ArrayList<Integer>();
        for (int val : arr) {
            if (pos.containsKey(val)) {
                int idx = pos.get(val);
                int it = binarySearch(d, idx);
                if (it != d.size()) {
                    d.set(it, idx);
                } else {
                    d.add(idx);
                }
            }
        }
        return n - d.size();
    }

    public int binarySearch(List<Integer> d, int target) {
        int size = d.size();
        if (size == 0 || d.get(size - 1) < target) {
            return size;
        }
        int low = 0, high = size - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (d.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}

作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/solution/de-dao-zi-xu-lie-de-zui-shao-cao-zuo-ci-hefgl/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



// 超时
class Solution {
    public int minOperations(int[] target, int[] arr) {
        Set<Integer> targetSet = new HashSet<>();
        for (int i : target) {
            targetSet.add(i);
        }
        int alreadyHave = 0;
        Set<Integer> arrSet = new HashSet<>();
        for (int i : arr) {
            if ((!arrSet.contains(i)) && targetSet.contains(i)) {
                alreadyHave++;
            }
            arrSet.add(i);
        }
        for (int i = alreadyHave; i >= 1; i--) {
            List<List<Integer>> seqs = getSubseq(arr, i, targetSet);
            for (List<Integer> seq : seqs) {
                if (isSubseq(target, seq)) {
                    return target.length - seq.size();
                }
            }
        }
        return target.length;
    }

    public List<List<Integer>> getSubseq(int[] arr, int subLength, Set<Integer> targetSet) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Set<Integer> resSet = new HashSet<>();
        backtrace(arr, 0, subLength, targetSet, res, resSet, ans);
        return ans;
    }

    public void backtrace(int[] arr, int start, int subLength, Set<Integer> targetSet, List<Integer> res, Set<Integer> resSet, List<List<Integer>> ans) {
        if (subLength == 0) {
            ans.add(new ArrayList<>(res));
            return;
        }
        for (int i = start; i <= arr.length-subLength; i++) {
            if (targetSet.contains(arr[i]) && (!resSet.contains(arr[i]))) {
                res.add(arr[i]);
                resSet.add(arr[i]);
                backtrace(arr, i+1, subLength-1, targetSet, res, resSet, ans);
                res.remove(res.size()-1);
                resSet.remove(arr[i]);
            }
        }
    }

    public boolean isSubseq(int[] target, List<Integer> seq) {
        int index = 0;
        for (int i = 0; i < target.length; i++) {
            if (target[i] == seq.get(index)) {
                index++;
            }
            if (index == seq.size()) {
                return true;
            }
        }
        return false;
    }
}