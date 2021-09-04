/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        int r = rand49();
        while (r > 40) {
            r = rand49();
        }
        return r % 10 + 1;
    }

    // 1...49
    public int rand49() {
        return 7 * (rand7() - 1) + rand7();
    }
}