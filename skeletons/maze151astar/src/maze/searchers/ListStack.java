package maze.searchers;

public class ListStack<E> implements Stack<E> {

    private ListNode<E> top;

    @Override
    public int size() {
        if (top == null) {
            return 0;
        } else {
            return top.size();
        }
    }

    @Override
    public int capacity() {
        return size();
    }

    @Override
    public E pop() {
        emptyCheck();
        E temp = top.getValue();
        top = top.getNext();
        return temp;
    }

    @Override
    public void push(E data) {
        top = new ListNode<E>(data, top);
    }

    @Override
    public E peek() {
        emptyCheck();
        return top.getValue();
    }

    @Override
    public String toString() {
        String s = "";
        for (ListNode<E> temp = top; temp != null; temp = temp.getNext()) {
            s = temp.getValue() + " " + s;
        }
        return s;
    }
}
