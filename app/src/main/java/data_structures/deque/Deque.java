package data_structures.deque;

/**
 * A double-ended queue (Deque) interface that supports element insertion and removal at both ends.
 *
 * <h2>Intended Goal</h2>
 * <p>
 * A deque is a linear data structure that generalizes both stacks and queues. Unlike a regular queue
 * where elements are added at one end and removed from the other, or a stack where elements are added
 * and removed from the same end, a deque allows insertion and removal at <b>both</b> the front and the rear.
 * </p>
 *
 * <h2>Core Operations</h2>
 * <ul>
 *   <li><b>addFirst</b>: Insert an element at the front of the deque</li>
 *   <li><b>addLast</b>: Insert an element at the rear of the deque</li>
 *   <li><b>removeFirst</b>: Remove and return the element at the front</li>
 *   <li><b>removeLast</b>: Remove and return the element at the rear</li>
 *   <li><b>peekFirst</b>: View the front element without removing it</li>
 *   <li><b>peekLast</b>: View the rear element without removing it</li>
 * </ul>
 *
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Implementing undo/redo functionality (operations from both ends)</li>
 *   <li>Sliding window algorithms</li>
 *   <li>Work-stealing algorithms in parallel computing</li>
 *   <li>Browser history navigation (back/forward)</li>
 *   <li>Palindrome checking</li>
 * </ul>
 *
 * <h2>Time Complexity Goals</h2>
 * <p>
 * All insertion and removal operations should be O(1) for an efficient implementation.
 * The size() and isEmpty() operations should also be O(1).
 * </p>
 *
 * @param <T> the type of elements held in this deque
 */
public interface Deque<T> {

	/**
	 * Inserts the specified element at the front of this deque.
	 *
	 * @param value the element to add
	 */
	void addFirst(T value);

	/**
	 * Inserts the specified element at the end of this deque.
	 *
	 * @param value the element to add
	 */
	void addLast(T value);

	/**
	 * Removes and returns the first element of this deque.
	 *
	 * @return the first element of this deque
	 * @throws IndexOutOfBoundsException if this deque is empty
	 */
	T removeFirst();

	/**
	 * Removes and returns the last element of this deque.
	 *
	 * @return the last element of this deque
	 * @throws IndexOutOfBoundsException if this deque is empty
	 */
	T removeLast();

	/**
	 * Retrieves, but does not remove, the first element of this deque.
	 *
	 * @return the first element of this deque
	 * @throws IndexOutOfBoundsException if this deque is empty
	 */
	T peekFirst();

	/**
	 * Retrieves, but does not remove, the last element of this deque.
	 *
	 * @return the last element of this deque
	 * @throws IndexOutOfBoundsException if this deque is empty
	 */
	T peekLast();

	/**
	 * Returns the number of elements in this deque.
	 *
	 * @return the number of elements in this deque
	 */
	int size();

	/**
	 * Returns true if this deque contains no elements.
	 *
	 * @return true if this deque is empty
	 */
	boolean isEmpty();

	/**
	 * Returns true if this deque contains the specified element.
	 *
	 * @param value the element to search for
	 * @return true if this deque contains the element
	 */
	boolean contains(T value);

	/**
	 * Removes all elements from this deque.
	 */
	void clear();
}
