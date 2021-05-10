class Solution {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        // 设perm = [p1, p2, ..., pn]
        // encoded = [e1, e2, ..., en-1]
        // p1^pn = e1^e2^...^en-1
        // p1^pn-1 = e1^e2^...^en-2
        // ...
        // p1^p2 = e1
        // 因为n为奇数，所以
        // (p1^pn)^(p1^pn-1)^...^(p1^p2) = p2^p3^...^pn
        // 因为p1到pn是前n个正整数，因此我们就可以求出p1的值
        int withoutP1 = encoded[0];
        int xor = encoded[0];
        for(int i = 1; i < encoded.length; i++) {
            // e1^e2^...ei+1
            xor = xor ^ encoded[i];
            withoutP1 = withoutP1 ^ xor;
        }
        int allXor = 1;
        for(int i = 2; i <= n; i++) {
            allXor = allXor ^ i;
        }
        int p1 = allXor ^ withoutP1;
        int[] perm = new int[n];
        perm[0] = p1;
        for(int i = 1; i < n; i++) {
            perm[i] = encoded[i-1] ^ perm[i-1];
        }
        return perm;
    }
}