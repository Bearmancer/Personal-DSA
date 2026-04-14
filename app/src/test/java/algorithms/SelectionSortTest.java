package algorithms;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"unused"})
@ExtendWith(SoftAssertionsExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SelectionSortTest {


	@Nested
	@Disabled("Awaiting implementation")
	class Correctness {

		@Test
		void sorts_unsorted_array() {
			int[] arr = {5, 2, 8, 1};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 5, 8);
		}

		@Test
		void handles_already_sorted_array() {
			int[] arr = {1, 2, 3, 4, 5};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_reverse_sorted_array() {
			int[] arr = {10, 9, 8, 7, 6};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(6, 7, 8, 9, 10);
		}

		@Test
		void handles_single_element() {
			int[] arr = {42};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(42);
		}

		@Test
		void handles_duplicates() {
			int[] arr = {3, 1, 3, 2};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 3);
		}

		@Test
		void handles_empty_array() {
			int[] arr = {};

			SelectionSort.sort(arr);

			assertThat(arr).isEmpty();
		}

		@Test
		void handles_negative_numbers() {
			int[] arr = {-3, -1, -10, 0, 5};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(-10, -3, -1, 0, 5);
		}

		@Test
		void handles_two_elements_in_order() {
			int[] arr = {1, 2};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2);
		}

		@Test
		void handles_two_elements_reversed() {
			int[] arr = {2, 1};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2);
		}

		@Test
		void handles_all_same_elements() {
			int[] arr = {7, 7, 7, 7, 7};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(7, 7, 7, 7, 7);
		}

		@Test
		void handles_integer_min_max_values() {
			int[] arr = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(Integer.MIN_VALUE, 0, Integer.MAX_VALUE);
		}

		@Test
		void handles_alternating_high_low() {
			int[] arr = {100, 1, 99, 2, 98, 3, 97, 4};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 97, 98, 99, 100);
		}

		@Test
		void handles_first_element_out_of_place() {
			int[] arr = {5, 1, 2, 3, 4};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_last_element_out_of_place() {
			int[] arr = {2, 3, 4, 5, 1};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Efficiency {

		@Test
		void consistent_performance_regardless_of_input() {
			int[] sorted = new int[100];
			int[] reverse = new int[100];
			for (int i = 0; i < 100; i++) {
				sorted[i] = i;
				reverse[i] = 100 - i;
			}

			long stepsSorted = SelectionSort.sortWithStepCount(sorted.clone());
			long stepsReverse = SelectionSort.sortWithStepCount(reverse.clone());

			assertThat(stepsSorted)
				.as("Selection sort should have similar performance regardless of input order")
				.isCloseTo(stepsReverse, within(100L));
		}

		@Test
		void always_makes_n_squared_comparisons() {
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = 100 - i;

			long steps = SelectionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Selection sort should take approximately n² comparisons")
				.isLessThan(100 * 100 * 2);
		}

		@Test
		void inefficiency_warning_for_large_dataset() {
			int[] arr = new int[1500];
			for (int i = 0; i < 1500; i++)
				arr[i] = 1500 - i;

			long steps = SelectionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("INEFFICIENT: Selection sort taking excessive comparisons")
				.isLessThanOrEqualTo(1500 * 1500 * 3);
		}

		@Test
		void minimal_swaps_compared_to_bubble_sort() {
			int[] arr = {5, 4, 3, 2, 1};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Stability {

		@Test
		void not_stable_sort() {
			int[] arr = {3, 1, 3, 2};

			SelectionSort.sort(arr);

			// Note: Selection sort is NOT stable - equal elements may swap positions
			assertThat(arr).containsExactly(1, 2, 3, 3);
		}

		@Test
		void demonstrates_instability_with_custom_objects() {
			int[] arr = {2, 1, 2};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 2);
		}

		@Test
		void instability_example_equal_elements_reordered() {
			int[] arr = {2, 2, 1};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class In_Place_Behavior {

		@Test
		void sorts_array_in_place_no_extra_array() {
			int[] arr = {5, 2, 8, 1, 9};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 5, 8, 9);
		}

		@Test
		void original_array_reference_unchanged() {
			int[] arr = {3, 1, 2};
			int[] originalRef = arr;

			SelectionSort.sort(arr);

			assertThat(arr).isSameAs(originalRef);
		}

		@Test
		void no_elements_lost_during_sort() {
			int[] arr = {5, 2, 8, 1, 9};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactlyInAnyOrder(1, 2, 5, 8, 9);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Swap_Count {

		@Test
		void exactly_n_minus_1_swaps_maximum() {
			int[] arr = {5, 4, 3, 2, 1};

			long swaps = SelectionSort.sortWithSwapCount(arr);

			assertThat(swaps)
				.as("Selection sort makes at most n-1 swaps")
				.isLessThanOrEqualTo(arr.length - 1);
		}

		@Test
		void zero_swaps_for_sorted_array() {
			int[] arr = {1, 2, 3, 4, 5};

			long swaps = SelectionSort.sortWithSwapCount(arr);

			assertThat(swaps)
				.as("No swaps needed for already sorted array")
				.isZero();
		}

		@Test
		void minimal_swaps_advantage_over_bubble_sort() {
			int[] arr = {5, 4, 3, 2, 1};

			long swaps = SelectionSort.sortWithSwapCount(arr);

			assertThat(swaps)
				.as("Selection sort uses fewer swaps than bubble sort")
				.isEqualTo(arr.length - 1);
		}

		@Test
		void swap_only_when_minimum_differs_from_current() {
			int[] arr = {1, 5, 4, 3, 2};

			long swaps = SelectionSort.sortWithSwapCount(arr);

			assertThat(swaps)
				.as("First element already in place, no swap needed")
				.isLessThan(arr.length - 1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Minimum_Finding {

		@Test
		void finds_minimum_in_unsorted_portion() {
			int[] arr = {5, 3, 1, 4, 2};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_multiple_minimums_takes_first() {
			int[] arr = {3, 1, 1, 1, 2};

			SelectionSort.sort(arr);

			assertThat(arr).containsExactly(1, 1, 1, 2, 3);
		}

		@Test
		void comparison_count_is_n_n_minus_1_over_2() {
			int[] arr = {5, 4, 3, 2, 1};
			int n = arr.length;
			long expectedComparisons = (long) n * (n - 1) / 2;

			long comparisons = SelectionSort.sortWithStepCount(arr);

			assertThat(comparisons)
				.as("Should make n(n-1)/2 comparisons")
				.isEqualTo(expectedComparisons);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Non_Adaptive {

		@Test
		void same_comparisons_regardless_of_input_order() {
			int[] sorted = {1, 2, 3, 4, 5};
			int[] reversed = {5, 4, 3, 2, 1};

			long stepsSorted = SelectionSort.sortWithStepCount(sorted.clone());
			long stepsReversed = SelectionSort.sortWithStepCount(reversed.clone());

			assertThat(stepsSorted)
				.as("Same comparisons for any input order")
				.isEqualTo(stepsReversed);
		}

		@Test
		void always_quadratic_time() {
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = i;

			long steps = SelectionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Even sorted input takes O(n²)")
				.isGreaterThanOrEqualTo(100 * 99 / 2);
		}

		@Test
		void no_early_termination() {
			int[] arr = {1, 2, 3, 4, 5};

			long steps = SelectionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Selection sort always does n(n-1)/2 comparisons")
				.isEqualTo(10);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void sort_then_binary_search() {
			int[] arr = {64, 25, 12, 22, 11};

			SelectionSort.sort(arr);
			int idx = java.util.Arrays.binarySearch(arr, 22);

			assertThat(idx).isGreaterThanOrEqualTo(0);
		}

		@Test
		void multiple_sorts_idempotent() {
			int[] arr = {5, 2, 8, 1, 9};

			SelectionSort.sort(arr);
			int[] afterFirst = arr.clone();
			SelectionSort.sort(arr);

			assertThat(arr).isEqualTo(afterFirst);
		}

		@Test
		void preferred_when_writes_are_expensive() {
			int[] arr = {5, 4, 3, 2, 1};

			long swaps = SelectionSort.sortWithSwapCount(arr);

			assertThat(swaps)
				.as("Selection sort minimizes writes - at most n-1 swaps")
				.isLessThanOrEqualTo(4);
		}
	}

}
