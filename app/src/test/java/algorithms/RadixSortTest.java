package algorithms;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"unused"})
@ExtendWith(SoftAssertionsExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RadixSortTest {

	@Nested
	@Disabled("Awaiting implementation")
	class Correctness {

		@Test
		void sorts_unsorted_array() {
			int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(2, 24, 45, 66, 75, 90, 170, 802);
		}

		@Test
		void handles_already_sorted_array() {
			int[] arr = {1, 2, 3, 4, 5};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void handles_reverse_sorted_array() {
			int[] arr = {500, 400, 300, 200, 100};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(100, 200, 300, 400, 500);
		}

		@Test
		void handles_empty_array() {
			int[] arr = {};

			RadixSort.sort(arr);

			assertThat(arr).isEmpty();
		}

		@Test
		void handles_single_element() {
			int[] arr = {42};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(42);
		}

		@Test
		void handles_duplicates() {
			int[] arr = {333, 111, 333, 222, 333};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(111, 222, 333, 333, 333);
		}

		@Test
		void handles_all_same_elements() {
			int[] arr = {777, 777, 777, 777, 777};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(777, 777, 777, 777, 777);
		}

		@Test
		void handles_single_digit_numbers() {
			int[] arr = {5, 2, 8, 1, 9, 3};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(1, 2, 3, 5, 8, 9);
		}

		@Test
		void handles_different_digit_counts() {
			int[] arr = {1, 10, 100, 1000, 5, 55, 555};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(1, 5, 10, 55, 100, 555, 1000);
		}

		@Test
		void handles_zeros() {
			int[] arr = {0, 10, 0, 5, 0};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(0, 0, 0, 5, 10);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Stability {

		@Test
		void preserves_relative_order_of_equal_elements() {
			int[] arr = {333, 111, 333, 222, 333};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(111, 222, 333, 333, 333);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Digit_Processing {

		@Test
		void correctly_processes_ones_digit() {
			int[] arr = {21, 12, 33, 14, 25};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(12, 14, 21, 25, 33);
		}

		@Test
		void correctly_processes_tens_digit() {
			int[] arr = {110, 120, 130, 115, 125};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(110, 115, 120, 125, 130);
		}

		@Test
		void counts_correct_passes(SoftAssertions softly) {
			int[] arr = {999, 1, 50};
			int passes = RadixSort.sortWithPassCount(arr);

			softly.assertThat(passes).isEqualTo(3);
			softly.assertThat(arr).containsExactly(1, 50, 999);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Different_Bases {

		@Test
		void sorts_with_base_10() {
			int[] arr = {170, 45, 75, 90};

			RadixSort.sort(arr, 10);

			assertThat(arr).containsExactly(45, 75, 90, 170);
		}

		@Test
		void sorts_with_base_256() {
			int[] arr = {170, 45, 75, 90, 802, 24};

			RadixSort.sort(arr, 256);

			assertThat(arr).containsExactly(24, 45, 75, 90, 170, 802);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Efficiency {

		@Test
		void handles_large_numbers() {
			int[] arr = {999999, 111111, 555555, 333333};

			RadixSort.sort(arr);

			assertThat(arr).containsExactly(111111, 333333, 555555, 999999);
		}

		@Test
		void handles_large_array() {
			int[] arr = new int[10000];
			for (int i = 0; i < 10000; i++)
				arr[i] = (i * 7) % 10000;

			RadixSort.sort(arr);

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
				arr[i] = 10000 - i;

			RadixSort.sort(arr);

			for (int i = 0; i < 10000; i++)
				assertThat(arr[i]).isEqualTo(i + 1);
		}

		@Test
		void handles_max_integer_values() {
			int[] arr = {Integer.MAX_VALUE / 2, 1, Integer.MAX_VALUE / 4};

			RadixSort.sort(arr);

			assertThat(arr[0]).isLessThan(arr[1]);
			assertThat(arr[1]).isLessThan(arr[2]);
		}
	}
}
