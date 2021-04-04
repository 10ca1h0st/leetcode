class Solution {
    public int numRabbits(int[] answers) {
        if(answers.length == 0) {
            return 0;
        }
        Arrays.sort(answers);
        int res = 0;
        int prev = answers[0];
        int part = prev + 1;
        for(int i = 1; i < answers.length; i++) {
            part--;
            if(prev != answers[i]) {
                res += prev + 1;
                prev = answers[i];
                part = prev + 1;
            }
            else if(part == 0) {
                res += prev + 1;
                prev = answers[i];
                part = prev + 1;
            }
        }
        res += prev + 1;
        return res;
    }
}