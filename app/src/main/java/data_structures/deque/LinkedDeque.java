package data_structures.deque;

/**
 * A doubly-linked list implementation of the Deque interface.
 *
 * <h2>Intended Goal</h2>
 * <p>
 * Implement a deque using a doubly-linked list where each node has references to both
 * its predecessor and successor. This enables O(1) insertion and removal at both ends.
 * </p>
 *
 * <h2>Implementation Hints</h2>
 * <ul>
 *   <li>Use a doubly-linked list with both head and tail pointers</li>
 *   <li>Each node should have prev and next references</li>
 *   <li>Handle edge cases when the deque becomes empty or has a single element</li>
 *   <li>Consider using sentinel nodes to simplify boundary conditions</li>
 * </ul>
 *
 * @param <T> the type of elements held in this deque
 */
@SuppressWarnings({"ConstantConditions", "ReturnOfNull"})
public class LinkedDeque<T> implements Deque<T> {

	// TODO

	@Override
	public void addFirst(T value) {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void addLast(T value) {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public T removeFirst() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public T removeLast() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public T peekFirst() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public T peekLast() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int size() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean isEmpty() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean contains(T value) {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public void clear() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public String toString() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}
}
