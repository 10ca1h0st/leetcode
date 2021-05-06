class Solution {
    public int[] decode(int[] encoded, int first) {
        int[] origin = new int[encoded.length + 1];
        origin[0] = first;
        for(int i = 1; i < origin.length; i++) {
            origin[i] = origin[i-1] ^ encoded[i-1];
        }
        return origin;
    }
}