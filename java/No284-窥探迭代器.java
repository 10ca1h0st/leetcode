// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> it;
    Integer now;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    it = iterator;
        now = null;
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if (now == null) {
            now = it.next();
        }
        return now;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (now != null) {
            Integer r = now;
            now = null;
            return r;
        }
        return it.next();
	}
	
	@Override
	public boolean hasNext() {
	    if (now != null) {
            return true;
        }
        return it.hasNext();
	}
}