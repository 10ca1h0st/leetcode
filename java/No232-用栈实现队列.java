class MyQueue {

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!stack2.isEmpty()) {
            return stack2.pop();
        }
        int remove = 0;
        while(!stack1.isEmpty()) {
            remove = stack1.pop();
            stack2.push(remove);
        }
        return stack2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(!stack2.isEmpty()) {
            return stack2.peek();
        }
        int remove = 0;
        while(!stack1.isEmpty()) {
            remove = stack1.pop();
            stack2.push(remove);
        }
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */