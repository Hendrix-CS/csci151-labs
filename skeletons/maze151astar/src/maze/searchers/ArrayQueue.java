package maze.searchers;

import maze.searchers.Queue;

public class ArrayQueue<E> implements Queue<E> {
    private E[] stuff;
    private int front;
    private int size;

    public ArrayQueue() {
        stuff = (E[])(new Object[8]);
    }

    @Override
    public void add(E data) {
        if (stuff.length == size) {
            resize();
        }
        stuff[(front + size) % stuff.length] = data;
        size++;
    }

    private void resize() {
        E[] stuff2 = (E[])(new Object[stuff.length * 2]);
        for (int i = 0; i < stuff.length; i++) {
            stuff2[i] = stuff[(front + i) % stuff.length];
        }
        stuff = stuff2;
        front = 0;
    }

    @Override
    public E remove() {
        emptyCheck();
        E temp = stuff[front];
        front = (front + 1) % stuff.length;
        size--;
        return temp;
    }

    @Override
    public E element() {
        emptyCheck();
        return stuff[front];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return stuff.length;
    }

}
