package algorithms;

/**
 * Quick Sort Algorithm Implementation.
 *
 * <h2>Intended Goal</h2>
 * <p>
 * Quick sort is a divide-and-conquer algorithm that picks a "pivot" element and partitions
 * the array around it, placing smaller elements before and larger elements after.
 * </p>
 *
 * <h2>Characteristics</h2>
 * <ul>
 *   <li><b>Time Complexity:</b> O(n log n) average, O(n²) worst case</li>
 *   <li><b>Space Complexity:</b> O(log n)</li>
 *   <li><b>Stability:</b> Not stable</li>
 *   <li><b>In-place:</b> Yes</li>
 * </ul>
 *
 * <h2>Visualization</h2>
 * <pre>
 * [3, 6, 8, 10, 1, 2, 1] pivot = 1 (last element)
 *  ^                 ^
 *  i                 j
 *
 * After partition: [1, 1, 8, 10, 6, 2, 3] with pivot in final position
 * </pre>
 */
public class QuickSort {

	/**
	 * Sorts the given array in ascending order using quick sort.
	 *
	 * @param arr the array to sort
	 */
	public static void sort(int[] arr) {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	/**
	 * Sorts the given array and returns the number of comparison operations performed.
	 *
	 * @param arr the array to sort
	 * @return the number of comparisons performed during partitioning
	 */
	public static long sortWithStepCount(int[] arr) {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}
}
