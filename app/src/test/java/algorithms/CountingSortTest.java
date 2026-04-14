package algorithms;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"unused"})
@ExtendWith(SoftAssertionsExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CountingSortTest {

	@Nested
	@Disabled("Awaiting implementation")
	class Correctness {

		@Test
		void sorts_unsorted_array() {
			int[] arr = {4, 2, 2, 8, 3, 3, 1};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 2, 3, 3, 4, 8);
		}

		@Test
		void handles_already_sorted_array() {
			int[] arr = {1, 2, 3, 4, 5};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_reverse_sorted_array() {
			int[] arr = {5, 4, 3, 2, 1};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_empty_array() {
			int[] arr = {};

			CountingSort.sort(arr);

			assertThat(arr).isEmpty();
		}

		@Test
		void handles_single_element() {
			int[] arr = {42};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(42);
		}

		@Test
		void handles_duplicates() {
			int[] arr = {3, 1, 3, 2, 3};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 3, 3);
		}

		@Test
		void handles_all_same_elements() {
			int[] arr = {7, 7, 7, 7, 7};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(7, 7, 7, 7, 7);
		}

		@Test
		void handles_two_elements() {
			int[] arr = {2, 1};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(1, 2);
		}

		@Test
		void handles_zeros() {
			int[] arr = {0, 3, 0, 1, 0};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(0, 0, 0, 1, 3);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Stability {

		@Test
		void preserves_relative_order_of_equal_elements() {
			int[] arr = {3, 1, 3, 2, 3};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 3, 3);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Range_Handling {

		@Test
		void handles_small_range() {
			int[] arr = {2, 1, 2, 1, 2};

			CountingSort.sort(arr);

			assertThat(arr).containsExactly(1, 1, 2, 2, 2);
		}

		@Test
		void handles_large_range_with_min_max() {
			int[] arr = {100, 1, 50, 25, 75};

			CountingSort.sort(arr, 1, 100);

			assertThat(arr).containsExactly(1, 25, 50, 75, 100);
		}

		@Test
		void handles_sparse_values() {
			int[] arr = {1, 100, 50};

			CountingSort.sort(arr, 1, 100);

			assertThat(arr).containsExactly(1, 50, 100);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Efficiency {

		@Test
		void linear_time_for_small_range(SoftAssertions softly) {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i % 10;

			long[] stats = CountingSort.sortWithStats(arr);

			softly.assertThat(stats[0]).as("Range").isEqualTo(10);
		}

		@Test
		void handles_large_array_efficiently() {
			int[] arr = new int[10000];
			for (int i = 0; i < 10000; i++)
				arr[i] = i % 100;

			CountingSort.sort(arr);

			for (int i = 0; i < 9999; i++)
				assertThat(arr[i]).isLessThanOrEqualTo(arr[i + 1]);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void stress_test_10000_elements() {
			int[] arr = new int[10000];
			for (int i = 0; i < 10000; i++)
				arr[i] = (i * 7) % 1000;

			CountingSort.sort(arr);

			for (int i = 0; i < 9999; i++)
				assertThat(arr[i]).isLessThanOrEqualTo(arr[i + 1]);
		}

		@Test
		void handles_sequential_values() {
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++)
				arr[i] = 100 - i;

			CountingSort.sort(arr);

			for (int i = 0; i < 100; i++)
				assertThat(arr[i]).isEqualTo(i + 1);
		}
	}
}
