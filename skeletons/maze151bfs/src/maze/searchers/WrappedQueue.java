package maze.searchers;

public class WrappedQueue<E> implements Searcher<E> {
	private Queue<E> queue;
	
	public WrappedQueue(Queue<E> queue) {
		this.queue = queue;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public int capacity() {
		return queue.capacity();
	}

	@Override
	public E remove() {
		return queue.remove();
	}

	@Override
	public void add(E data) {
		queue.add(data);
	}

	@Override
	public E element() {
		return queue.element();
	}

}
