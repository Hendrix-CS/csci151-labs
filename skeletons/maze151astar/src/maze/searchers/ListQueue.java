package maze.searchers;

public class ListQueue<E> implements Queue<E> {

    private ListNode<E> front;
    private ListNode<E> back;

    @Override
    public void add(E data) {
        ListNode<E> temp = new ListNode<E>(data, null);
        if (isEmpty()) {
            front = temp;
        } else {
            back.setNext(temp);
        }
        back = temp;
    }

    @Override
    public E remove() {
        emptyCheck();
        E temp = front.getValue();
        front = front.getNext();
        return temp;
    }

    @Override
    public E element() {
        emptyCheck();
        return front.getValue();
    }

    @Override
    public int size() {
        if (front == null) {
            return 0;
        } else {
            return front.size();
        }
    }

    @Override
    public int capacity() {
        return size();
    }

}
