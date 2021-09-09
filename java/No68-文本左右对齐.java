class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> matrix = new ArrayList<>();
        int i = 0;
        int curRowLen = 0;
        // list.get(i)表示第i行的字符串最后一个单词在words中的索引
        List<Integer> list = new ArrayList<>();
        while (i < words.length) {
            String cur = words[i];
            int len = curRowLen + cur.length() + 1;
            if (len < maxWidth) {
                curRowLen = len;
            } else if (len == maxWidth) {
                list.add(i);
                curRowLen = 0;
            } else if (len == maxWidth+1) {
                list.add(i);
                curRowLen = 0;
            } else {
                list.add(i-1);
                curRowLen = 0;
                i--;
            }
            i++;
        }
        if (curRowLen > 0) {
            list.add(words.length-1);
        }
        List<String> ans = new ArrayList<>();
        int start = 0;
        for (int end : list) {
            StringBuilder sb = new StringBuilder();
            if (end == words.length-1) {
                // 最后一行
                int len = 0;
                for (int j = start; j <= end; j++) {
                    len += words[j].length();
                }
                int space = maxWidth - len;
                for (int j = start; j < end; j++) {
                    sb.append(words[j]);
                    sb.append(' ');
                    space--;
                }
                sb.append(words[end]);
                while (space > 0) {
                    sb.append(' ');
                    space--;
                }
            } else {
                // 当前行有word[start]到words[end]组成
                int len = 0;
                for (int j = start; j <= end; j++) {
                    len += words[j].length();
                }
                int space = maxWidth - len;
                if (end == start) {
                    sb.append(words[end]);
                    while (space > 0) {
                        sb.append(' ');
                        space--;
                    }
                } else {
                    // 每一个单词后面都会跟的空格数量
                    int everySpace = space / (end-start);
                    int addition = space % (end-start);
                    for (int j = start; j < end; j++) {
                        sb.append(words[j]);
                        if (addition > 0) {
                            int k = 0;
                            while (k <= everySpace) {
                                sb.append(' ');
                                k++;
                            }
                            addition--;
                        } else {
                            int k = 0;
                            while (k < everySpace) {
                                sb.append(' ');
                                k++;
                            }
                        }
                    }
                    sb.append(words[end]);
                }
            }
            start = end+1;
            ans.add(sb.toString());
        }
        return ans;
    }
}