class Solution {
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        Map<Integer, String> id2name = new HashMap<>();
        Map<String, Integer> name2id = new HashMap<>();
        int index = 0;
        for (int i = 0; i < names.length; i++) {
            String name = names[i].substring(0, names[i].indexOf("("));
            if (!name2id.containsKey(name)) {
                name2id.put(name, index);
                id2name.put(index, name);
                index++;
            }
        }
        for (int i = 0; i < synonyms.length; i++) {
            String name1 = synonyms[i].substring(1, synonyms[i].indexOf(","));
            String name2 = synonyms[i].substring(synonyms[i].indexOf(",")+1, synonyms[i].length()-1);
            if (!name2id.containsKey(name1)) {
                name2id.put(name1, index);
                id2name.put(index, name1);
                index++;
            }
            if (!name2id.containsKey(name2)) {
                name2id.put(name2, index);
                id2name.put(index, name2);
                index++;
            }
        }
        UF uf = new UF(index, id2name);
        for (int i = 0; i < synonyms.length; i++) {
            String name1 = synonyms[i].substring(1, synonyms[i].indexOf(","));
            String name2 = synonyms[i].substring(synonyms[i].indexOf(",")+1, synonyms[i].length()-1);
            uf.union(name2id.get(name1), name2id.get(name2));
        }
        Map<String, Integer> map = new HashMap<>();
        for (String name : names) {
            String realName = name.substring(0, name.indexOf("("));
            int count = Integer.valueOf(name.substring(name.indexOf("(")+1, name.length()-1));
            String key = id2name.get(uf.find(name2id.get(realName)));
            map.put(key, map.getOrDefault(key, 0) + count);
        }
        String[] ans = new String[map.size()];
        index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans[index] = entry.getKey() + "(" + entry.getValue() + ")";
            index++;
        }
        return ans;
    }

    static class UF {
        int[] parent;
        int size;
        Map<Integer, String> map;
        UF(int n, Map<Integer, String> _map) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            size = n;
            map = _map;
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (map.get(rootX).compareTo(map.get(rootY)) > 0) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                }
                size--;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}

// 以下为用哈希表实现并查集
public String[] trulyMostPopular(String[] names, String[] synonyms) {
        // 并查集
        class UnionFind1 {
            private Map<String, String> map = new HashMap<>();
        
            public UnionFind1(String[] names) {
                for (String name : names) {
                    map.put(name, name);
                }
            }
            
            // 并
            void union(String name1, String name2) {
                String parent1 = find(name1);
                if (parent1 == null) {
                    parent1 = name1;
                }
                String parent2 = find(name2);
                if (parent2 == null) {
                    parent2 = name2;
                }
                if (!parent1.equals(parent2)) {
                    if (parent2.compareTo(parent1) > 0) {
                        map.put(parent2, parent1);
                    } else {
                        map.put(parent1, parent2);
                    }
                }
            }
            
            // 查
            String find(String name) {
                if (name == null) {
                    return null;
                }
                if (name.equals(map.get(name))) {
                    return name;
                }
                return find(map.get(name));
            }
        }

        // 初始化并查集
        String[] nameArray = Arrays.stream(names).map(ele -> ele.substring(0, ele.indexOf("("))).toArray(String[]::new);
        UnionFind1 uf = new UnionFind1(nameArray);

        // 合并
        for (String synonym : synonyms) {
            String[] array = synonym.replace('(', ' ').replace(')', ' ').trim().split(",");
            uf.union(array[0], array[1]);
        }

        // 计算数字
        Map<String, Integer> map = new HashMap<>();
        for (String item : names) {
            int index = item.indexOf("(");
            String name = item.substring(0, index);
            int num = Integer.parseInt(item.substring(index + 1, item.length() - 1));
            name = uf.find(name);
            map.put(name, map.getOrDefault(name, 0) + num);
        }
        return map.entrySet().stream().map(ele -> String.format("%s(%d)", ele.getKey(), ele.getValue())).toArray(String[]::new);
    }

作者：advancemn
链接：https://leetcode-cn.com/problems/baby-names-lcci/solution/bing-cha-ji-tong-yong-zuo-fa-shi-yong-ha-p9dg/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。