package maze.searchers;

public class ListNode<E> {

    private E value;
    private ListNode<E> next;

    public ListNode(E item, ListNode<E> next) {
        this.value = item;
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E item) {
        this.value = item;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setNext(ListNode<E> next) {
        this.next = next;
    }

    public int size() {
        if (next == null) {
            return 1;
        } else {
            return 1 + next.size();
        }
    }
}
