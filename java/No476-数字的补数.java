class Solution {
    public int findComplement(int num) {
        int bits = 0;
        int _num = num;
        while (num > 0) {
            bits++;
            num = num>>1;
        }
        return _num^((1<<bits)-1);
    }
}