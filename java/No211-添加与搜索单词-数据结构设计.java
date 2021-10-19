class WordDictionary {

    private PrefixTrie root;

    public WordDictionary() {
        root = new PrefixTrie();
    }
    
    public void addWord(String word) {
        PrefixTrie node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch-'a'] == null) {
                node.children[ch-'a'] = new PrefixTrie();
            }
            node = node.children[ch-'a'];
        }
        node.leaf = true;
    }
    
    public boolean search(String word) {
        PrefixTrie node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                for (int j = 0; j < 26; j++) {
                    if (node.children[j] != null) {
                        if (get(word, i+1, node.children[j])) {
                            return true;
                        }
                    }
                }
                return false;
            }
            if (node.children[ch-'a'] == null) {
                return false;
            }
            node = node.children[ch-'a'];
        }
        return node.leaf;
    }

    // 从word[index]开始，并以start为根节点，搜索
    private boolean get(String word, int index, PrefixTrie start) {
        if (index == word.length()) {
            return start.leaf;
        }
        PrefixTrie node = start;
        for (int i = index; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch == '.') {
                for (int j = 0; j < 26; j++) {
                    if (node.children[j] != null) {
                        if (get(word, i+1, node.children[j])) {
                            return true;
                        }
                    }
                }
                return false;
            }
            if (node.children[ch-'a'] == null) {
                return false;
            }
            node = node.children[ch-'a'];
        }
        return node.leaf;
    }
}

class PrefixTrie {
    boolean leaf;
    PrefixTrie[] children;

    PrefixTrie() {
        children = new PrefixTrie[26];
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */