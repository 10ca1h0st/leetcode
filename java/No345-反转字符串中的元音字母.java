class Solution {
    public String reverseVowels(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Set<Character> set = new HashSet<>(List.of('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'));
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) {
                sb.append(stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}