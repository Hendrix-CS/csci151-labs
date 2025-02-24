package maze.searchers;

public class ListStack<E> implements Stack<E> {

    // TODO Step 2 Add private Node for top
    private ListNode<E> top;

    @Override
    public void push(E data) {
        // TODO Step 2 Implement ListStack
        ListNode<E> temp = new ListNode<E>(data);
        temp.setNext(top);
        top = temp;
    }

    @Override
    public E pop() {
        // TODO Step 2 Implement ListStack
        return null;
    }

    @Override
    public E peek() {
        // TODO Step 2 Implement ListStack
        return null;
    }

    @Override
    public int size() {
        // TODO Step 2 Implement ListStack
        return 0;
    }

    @Override
    public String toString() {
        // TODO Step 2 Implement ListStack
        return "";
    }

    @Override
    public int capacity() {
        return size();
    }

}
