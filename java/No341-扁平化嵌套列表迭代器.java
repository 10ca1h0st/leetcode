/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    private List<NestedInteger> list;
    private Queue<Integer> iter;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = nestedList;
        iter = new ArrayDeque<>();
        fill();
    }

    @Override
    public Integer next() {
        return iter.poll();
    }

    @Override
    public boolean hasNext() {
        if(!iter.isEmpty()) {
            return true;
        }
        fill();
        if(iter.isEmpty()) {
            return false;
        }
        return true;
    }

    // 当iter为空时，填充iter
    private void fill() {
        if(!iter.isEmpty()) {
            return;
        }
        if(list.isEmpty()) {
            return;
        }
        NestedInteger e = null;
        while(iter.isEmpty() && !list.isEmpty()) {
            e = list.remove(0);
            if(e.isInteger()) {
                iter.offer(e.getInteger());
            }
            else {
                putListIntoQueue(e.getList());
            }
        }
    }

    // 将l展平，并将所有元素放到iter中
    private void putListIntoQueue(List<NestedInteger> l) {
        for(NestedInteger i : l) {
            if(i.isInteger()) {
                iter.offer(i.getInteger());
            }
            else {
                putListIntoQueue(i.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */