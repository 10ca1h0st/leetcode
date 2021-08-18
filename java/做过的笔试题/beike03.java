import java.util.*;


public class beike03 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param a int整型一维数组
     * @param t int整型
     * @return long长整型
     */
    public long section (int[] a, int t) {
        // write code here
        long totalRanges = (a.length * (a.length-1)) / 2;
        Set<Integer> delete = new HashSet<>();
        Set<String> notSpecialPair = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if ((a[i]^a[j]) == t) {
                    //notSpecialPair.add(String.valueOf(i)+":"+String.valueOf(j));
                    for (int start = 0; start <= i; start++) {
                        for (int end = a.length-1; end >= j; end--) {
                            int range = start*a.length + end;
                            if (!delete.contains(range)) {
                                totalRanges--;
                                delete.add(range);
                            }
                        }
                    }
                }
            }
        }
        return totalRanges;
    }
}
