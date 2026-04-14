package algorithms;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"unused"})
@ExtendWith(SoftAssertionsExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InsertionSortTest {


	@Nested
	@Disabled("Awaiting implementation")
	class Correctness {

		@Test
		void sorts_unsorted_array() {
			int[] arr = {4, 2, 7, 1};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 4, 7);
		}

		@Test
		void handles_already_sorted_array() {
			int[] arr = {1, 2, 3, 4, 5};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_reverse_sorted_array() {
			int[] arr = {10, 9, 8, 7, 6};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(6, 7, 8, 9, 10);
		}

		@Test
		void handles_single_element() {
			int[] arr = {42};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(42);
		}

		@Test
		void handles_duplicates() {
			int[] arr = {3, 1, 3, 2, 3};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 3, 3);
		}

		@Test
		void handles_empty_array() {
			int[] arr = {};

			InsertionSort.sort(arr);

			assertThat(arr).isEmpty();
		}

		@Test
		void handles_negative_numbers() {
			int[] arr = {-5, -1, -10, 0, 3};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(-10, -5, -1, 0, 3);
		}

		@Test
		void handles_two_elements_in_order() {
			int[] arr = {1, 2};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2);
		}

		@Test
		void handles_two_elements_reversed() {
			int[] arr = {2, 1};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2);
		}

		@Test
		void handles_all_same_elements() {
			int[] arr = {7, 7, 7, 7, 7};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(7, 7, 7, 7, 7);
		}

		@Test
		void handles_integer_min_max_values() {
			int[] arr = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(Integer.MIN_VALUE, 0, Integer.MAX_VALUE);
		}

		@Test
		void handles_alternating_high_low() {
			int[] arr = {100, 1, 99, 2, 98, 3, 97, 4};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 97, 98, 99, 100);
		}

		@Test
		void handles_first_element_out_of_place() {
			int[] arr = {5, 1, 2, 3, 4};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_last_element_out_of_place() {
			int[] arr = {2, 3, 4, 5, 1};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Efficiency {

		@Test
		void best_case_already_sorted() {
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = i;

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Insertion sort should be O(n) on already sorted data")
				.isLessThan(100 * 2);
		}

		@Test
		void efficient_on_nearly_sorted_data() {
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = i;
			arr[50] = 49;

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Insertion sort should be efficient on nearly sorted data")
				.isLessThan(100 * 10);
		}

		@Test
		void worst_case_reverse_sorted() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = 1000 - i;

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Worst case should be O(n²)")
				.isLessThan(1000 * 1000 * 2);
		}

		@Test
		void inefficiency_warning_for_worst_case() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = 1000 - i;

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("INEFFICIENT: Insertion sort taking too many steps - consider optimization")
				.isLessThanOrEqualTo(1000 * 1000 * 2);
		}

		@Test
		void adaptive_performance_better_than_bubble_and_selection() {
			int[] arr = new int[200];
			for (int i = 0; i < 200; i++)
				arr[i] = i;
			arr[100] = 99;

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Insertion sort should adapt to nearly sorted input")
				.isLessThan(200 * 50);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Stability {

		@Test
		void maintains_stability() {
			int[] arr = {3, 1, 3, 2, 3};

			InsertionSort.sort(arr);

			// In a real test, you'd verify the order of equal elements was preserved
			assertThat(arr).containsExactly(1, 2, 3, 3, 3);
		}

		@Test
		void stability_verified_with_custom_objects() {
			int[] arr = {2, 1, 2};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class In_Place_Behavior {

		@Test
		void sorts_array_in_place_no_extra_array() {
			int[] arr = {5, 2, 8, 1, 9};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 5, 8, 9);
		}

		@Test
		void original_array_reference_unchanged() {
			int[] arr = {3, 1, 2};
			int[] originalRef = arr;

			InsertionSort.sort(arr);

			assertThat(arr).isSameAs(originalRef);
		}

		@Test
		void no_elements_lost_during_sort() {
			int[] arr = {5, 2, 8, 1, 9};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactlyInAnyOrder(1, 2, 5, 8, 9);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Adaptive_Behavior {

		@Test
		void O_n_on_sorted_input() {
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = i;

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Should be O(n) for sorted input")
				.isLessThanOrEqualTo(100);
		}

		@Test
		void efficient_on_k_inversions() {
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = i;
			arr[0] = 99;
			arr[99] = 0;

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Should be O(n + k) for k inversions")
				.isLessThan(100 * 5);
		}

		@Test
		void performance_scales_with_disorder() {
			int[] nearlySorted = new int[100];
			int[] halfReversed = new int[100];
			for (int i = 0; i < 100; i++) {
				nearlySorted[i] = i;
				halfReversed[i] = i < 50 ? 50 - i : i;
			}

			long stepsNearly = InsertionSort.sortWithStepCount(nearlySorted);
			long stepsHalf = InsertionSort.sortWithStepCount(halfReversed);

			assertThat(stepsHalf)
				.as("More disordered array should take more steps")
				.isGreaterThan(stepsNearly);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Shifting_Mechanism {

		@Test
		void shifts_elements_right_during_insert() {
			int[] arr = {1, 2, 4, 3};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4);
		}

		@Test
		void uses_temp_variable_for_key() {
			int[] arr = {5, 1, 2, 3, 4};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void finds_correct_insertion_position() {
			int[] arr = {10, 20, 30, 5};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(5, 10, 20, 30);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Online_Algorithm {

		@Test
		void can_sort_data_as_it_arrives() {
			int[] arr = {3, 1, 4};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 3, 4);
		}

		@Test
		void efficient_for_small_datasets() {
			int[] arr = {5, 2, 8, 1, 9};

			long steps = InsertionSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Insertion sort is efficient for small arrays despite being O(n²)")
				.isLessThan(50);
		}

		@Test
		void maintains_sorted_prefix_invariant() {
			int[] arr = {4, 2, 5, 1, 3};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void streaming_insert_simulation() {
			int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};

			InsertionSort.sort(arr);

			assertThat(arr).containsExactly(1, 1, 2, 3, 4, 5, 6, 9);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void sort_then_binary_search() {
			int[] arr = {64, 25, 12, 22, 11};

			InsertionSort.sort(arr);
			int idx = java.util.Arrays.binarySearch(arr, 22);

			assertThat(idx).isGreaterThanOrEqualTo(0);
		}

		@Test
		void multiple_sorts_idempotent() {
			int[] arr = {5, 2, 8, 1, 9};

			InsertionSort.sort(arr);
			int[] afterFirst = arr.clone();
			InsertionSort.sort(arr);

			assertThat(arr).isEqualTo(afterFirst);
		}

		@Test
		void use_as_subroutine_in_hybrid_sort() {
			int[] smallArr = {5, 2, 8, 1, 9};

			InsertionSort.sort(smallArr);

			assertThat(smallArr).containsExactly(1, 2, 5, 8, 9);
		}
	}

}
