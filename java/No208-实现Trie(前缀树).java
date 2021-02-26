class Trie {

    public Trie[] children;
    // 是否插入过以此字符结尾的单词
    public boolean end;

    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        end = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie cur = this;
        for(char ch : word.toCharArray()) {
            if(cur.children[ch-'a'] == null) {
                cur.children[ch-'a'] = new Trie();
            }
            cur = cur.children[ch-'a'];
        }
        cur.end = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie cur = this;
        for(char ch : word.toCharArray()) {
            if(cur.children[ch-'a'] == null) {
                return false;
            }
            cur = cur.children[ch-'a'];
        }
        return cur.end;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie cur = this;
        for(char ch : prefix.toCharArray()) {
            if(cur.children[ch-'a'] == null) {
                return false;
            }
            cur = cur.children[ch-'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */