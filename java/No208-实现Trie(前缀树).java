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
 
// 懒加载机制
 class Trie {

    static class TrieNode {
        // length == 26
        TrieNode[] children;

        // 目前节点保存的值，根节点不保存值
        String value;

        // 代表是否插入过这个节点
        boolean isLeaf;

        TrieNode(String v) {
            children = new TrieNode[26];
            value = v;
            isLeaf = true;
        }
    }

    TrieNode root;

    private TrieNode helper;

    static final char base = 'a';

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode(null);
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        helper = null;
        int i = _search(word);
        if(i == -1) {
            return;
        }
        if(i == word.length()) {
            helper.isLeaf = true;
            return;
        }
        char ch = word.charAt(i);
        TrieNode next = helper.children[ch-base];
        if(next == null) {
            helper.children[ch-base] = new TrieNode(word.substring(i, word.length()));
            return;
        }
        String v = next.value;
        // 找到word[i:-1]和v的公共前缀，然后从公共前缀处开始，分裂出两个子节点
        int j = i;
        int split = 0;
        for(; j < word.length(); split++, j++) {
            if(word.charAt(j) != v.charAt(split)) {
                break;
            }
        }
        if(j == word.length()) {
            next.value = word.substring(i, word.length());
            TrieNode origin = next.children[v.charAt(split)-base];
            next.children[v.charAt(split)-base] = new TrieNode(v.substring(split, v.length()));
            if(origin != null) {
                next.children[v.charAt(split)-base].children[v.charAt(split)-base] = origin;
            }
        }
        else {
            next.isLeaf = false;
            next.value = v.substring(0, split);

            TrieNode[] originChildren = next.children;
            next.children = new TrieNode[26];
            
            ch = v.charAt(split);
            next.children[ch-base] = new TrieNode(v.substring(split, v.length()));
            next.children[ch-base].children = originChildren;

            ch = word.charAt(j);
            next.children[ch-base] = new TrieNode(word.substring(j, word.length()));
        }
    }

    // 在insert的时候使用，返回不匹配时遍历到的word的索引，且用helper保存不匹配时的TrieNode
    // helper代表在当前节点的孩子中，找不到匹配word[i:]的孩子
    // 返回-1代表搜索成功
    // 返回word.length()时，只需要把helper的isLeaf属性设为true即可
    private int _search(String word) {
        int i = 0;
        char ch = word.charAt(i);
        TrieNode cur = root;
        TrieNode next = null;
        while(i < word.length()) {
            ch = word.charAt(i);
            next = cur.children[ch-base];
            if(next == null) {
                helper = cur;
                return i;
            }
            String v = next.value;
            if(i + v.length() > word.length() ||
			!v.equals(word.substring(i, i+v.length()))) {
                helper = cur;
                return i;
            }
            i += v.length();
            cur = next;
        }
        if(!cur.isLeaf) {
            helper = cur;
            return word.length();
        }
        return -1;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        int i = 0;
        char ch = word.charAt(i);
        TrieNode cur = root;
        TrieNode next = null;
        while(i < word.length()) {
            ch = word.charAt(i);
            next = cur.children[ch-base];
            if(next == null) {
                return false;
            }
            String v = next.value;
            if(i + v.length() > word.length()) {
                return false;
            }
            if(!v.equals(word.substring(i, i+v.length()))) {
                return false;
            }
            i += v.length();
            cur = next;
        }
        if(!cur.isLeaf) {
            return false;
        }
        return true;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String word) {
        int i = 0;
        char ch = word.charAt(i);
        TrieNode cur = root;
        TrieNode next = null;
        while(i < word.length()) {
            ch = word.charAt(i);
            next = cur.children[ch-base];
            if(next == null) {
                return false;
            }
            String v = next.value;
            if(i + v.length() >= word.length()) {
                if(v.startsWith(word.substring(i, word.length()))) {
                    return true;
                }
                return false;
            }
            else {
                // i + v.length() < word.length()
                if(!v.equals(word.substring(i, i+v.length()))) {
                    return false;
                }
            }
            i += v.length();
            cur = next;
        }
        return true;
    }
}