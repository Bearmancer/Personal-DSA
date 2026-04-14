package algorithms;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"unused"})
@ExtendWith(SoftAssertionsExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MergeSortTest {

	@Nested
	@Disabled("Awaiting implementation")
	class Correctness {

		@Test
		void sorts_unsorted_array() {
			int[] arr = {5, 2, 8, 1, 9};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 5, 8, 9);
		}

		@Test
		void handles_already_sorted_array() {
			int[] arr = {1, 2, 3, 4, 5};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_reverse_sorted_array() {
			int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		}

		@Test
		void handles_empty_array() {
			int[] arr = {};

			MergeSort.sort(arr);

			assertThat(arr).isEmpty();
		}

		@Test
		void handles_single_element() {
			int[] arr = {42};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(42);
		}

		@Test
		void handles_duplicates() {
			int[] arr = {3, 1, 3, 2, 3};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 3, 3);
		}

		@Test
		void handles_negative_numbers() {
			int[] arr = {-5, -1, -10, 0, 3};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(-10, -5, -1, 0, 3);
		}

		@Test
		void handles_two_elements_in_order() {
			int[] arr = {1, 2};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2);
		}

		@Test
		void handles_two_elements_reversed() {
			int[] arr = {2, 1};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2);
		}

		@Test
		void handles_all_same_elements() {
			int[] arr = {7, 7, 7, 7, 7};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(7, 7, 7, 7, 7);
		}

		@Test
		void handles_integer_min_max_values() {
			int[] arr = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(Integer.MIN_VALUE, 0, Integer.MAX_VALUE);
		}

		@Test
		void handles_alternating_high_low() {
			int[] arr = {100, 1, 99, 2, 98, 3, 97, 4};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 97, 98, 99, 100);
		}

		@Test
		void handles_power_of_two_size() {
			int[] arr = {8, 4, 2, 6, 1, 5, 3, 7};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);
		}

		@Test
		void handles_non_power_of_two_size() {
			int[] arr = {5, 3, 1, 4, 2};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Efficiency {

		@Test
		void consistent_performance_sorted_input() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			long steps = MergeSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Merge sort always O(n log n), even on sorted input")
				.isLessThan(1000 * 20);
		}

		@Test
		void consistent_performance_reverse_sorted() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = 1000 - i;

			long steps = MergeSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Merge sort takes O(n log n) for reversed array")
				.isLessThan(1000 * 20);
		}

		@Test
		void handles_large_dataset_efficiently() {
			int[] arr = new int[10000];
			for (int i = 0; i < 10000; i++)
				arr[i] = (i * 37 + 13) % 10000;

			long steps = MergeSort.sortWithStepCount(arr);

			assertThat(steps)
				.as("Merge sort should handle large datasets efficiently at O(n log n)")
				.isLessThan(10000 * 20);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Stability {

		@Test
		void preserves_order_of_equal_elements() {
			int[] arr = {3, 1, 3, 2, 3};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 3, 3);
		}

		@Test
		void stable_with_duplicates() {
			int[] arr = {2, 1, 2, 1, 2};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 1, 2, 2, 2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Divide_And_Conquer {

		@Test
		void correctly_divides_odd_length_array() {
			int[] arr = {5, 3, 1, 4, 2};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void correctly_divides_even_length_array() {
			int[] arr = {6, 4, 2, 5, 3, 1};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5, 6);
		}

		@Test
		void handles_three_elements() {
			int[] arr = {3, 1, 2};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Merge_Operation {

		@Test
		void merges_two_sorted_halves() {
			int[] arr = {1, 3, 5, 2, 4, 6};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5, 6);
		}

		@Test
		void handles_unequal_halves() {
			int[] arr = {4, 5, 6, 1, 2};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 4, 5, 6);
		}

		@Test
		void handles_one_half_empty_after_merge() {
			int[] arr = {1, 2, 3, 7, 8, 9};

			MergeSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 7, 8, 9);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void stress_test_1000_elements() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = 1000 - i;

			MergeSort.sort(arr);

			for (int i = 0; i < 1000; i++)
				assertThat(arr[i]).isEqualTo(i + 1);
		}

		@Test
		void random_order_stress_test() {
			int[] arr = new int[500];
			for (int i = 0; i < 500; i++)
				arr[i] = (i * 73 + 17) % 500;

			MergeSort.sort(arr);

			for (int i = 0; i < 499; i++)
				assertThat(arr[i]).isLessThanOrEqualTo(arr[i + 1]);
		}

		@Test
		void handles_already_sorted_large_array() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			MergeSort.sort(arr);

			for (int i = 0; i < 1000; i++)
				assertThat(arr[i]).isEqualTo(i);
		}
	}
}
