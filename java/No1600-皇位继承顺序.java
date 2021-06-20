class ThroneInheritance {

    static class MultiTree {
        String parent;
        Queue<MultiTree> children;

        MultiTree(String name) {
            parent = name;
            children = new LinkedList<>();
        }

        String getName() {
            return parent;
        }

        Queue<MultiTree> getChildren() {
            return children;
        }

        MultiTree birth(String childname) {
            MultiTree child = new MultiTree(childname);
            children.offer(child);
            return child;
        }
    }

    MultiTree root;
    List<String> order;
    Set<String> deadMen;
    boolean changed;

    Map<String, MultiTree> cache;

    public ThroneInheritance(String kingName) {
        root = new MultiTree(kingName);
        order = new ArrayList<>();
        order.add(kingName);
        deadMen = new HashSet<>();
        changed = false;

        cache = new HashMap<>();
        cache.put(kingName, root);
    }
    
    public void birth(String parentName, String childName) {
        MultiTree parent = cache.get(parentName);
        MultiTree child = parent.birth(childName);
        cache.put(childName, child);
        changed = true;
    }

    MultiTree findNode(MultiTree node, String name) {
        if(node.getName().equals(name)) {
            return node;
        }
        for(MultiTree child : node.getChildren()) {
            if(child.getName().equals(name)) {
                return child;
            }
            MultiTree res = findNode(child, name);
            if(res != null) {
                return res;
            }
        }
        return null;
    }
    
    public void death(String name) {
        deadMen.add(name);
        cache.remove(name);
        changed = true;
    }
    
    public List<String> getInheritanceOrder() {
        if(!changed) {
            return order;
        }
        order = new ArrayList<>();
        dfs(root);
        changed = false;
        return order;
    }

    void dfs(MultiTree root) {
        if(root == null) {
            return;
        }
        if(!deadMen.contains(root.getName())) {
            order.add(root.getName());
        }
        for(MultiTree child : root.getChildren()) {
            dfs(child);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */