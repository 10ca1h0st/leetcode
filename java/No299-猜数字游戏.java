class Solution {
    public String getHint(String secret, String guess) {
        int A = 0;
        int B = 0;
        int[] count = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            }
            count[secret.charAt(i)-'0']++;
        }
        for (char ch : guess.toCharArray()) {
            if (count[ch-'0'] > 0) {
                count[ch-'0']--;
                B++;
            }
        }
        B -= A;
        return String.valueOf(A)+"A"+String.valueOf(B)+"B";
    }
}