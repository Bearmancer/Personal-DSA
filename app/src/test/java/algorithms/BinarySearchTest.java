package algorithms;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"unused"})
@ExtendWith(SoftAssertionsExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BinarySearchTest {

	@Nested
	@Disabled("Awaiting implementation")
	class Search {

		@Nested
		class Found {

			@Test
			void finds_element_at_beginning() {
				int[] arr = {1, 2, 3, 4, 5};

				int result = BinarySearch.search(arr, 1);

				assertThat(result).isEqualTo(0);
			}

			@Test
			void finds_element_at_end() {
				int[] arr = {1, 2, 3, 4, 5};

				int result = BinarySearch.search(arr, 5);

				assertThat(result).isEqualTo(4);
			}

			@Test
			void finds_element_in_middle() {
				int[] arr = {1, 2, 3, 4, 5};

				int result = BinarySearch.search(arr, 3);

				assertThat(result).isEqualTo(2);
			}

			@Test
			void finds_element_in_single_element_array() {
				int[] arr = {42};

				int result = BinarySearch.search(arr, 42);

				assertThat(result).isEqualTo(0);
			}

			@Test
			void finds_element_in_two_element_array_first() {
				int[] arr = {1, 2};

				int result = BinarySearch.search(arr, 1);

				assertThat(result).isEqualTo(0);
			}

			@Test
			void finds_element_in_two_element_array_second() {
				int[] arr = {1, 2};

				int result = BinarySearch.search(arr, 2);

				assertThat(result).isEqualTo(1);
			}

			@Test
			void finds_negative_number() {
				int[] arr = {-10, -5, 0, 5, 10};

				int result = BinarySearch.search(arr, -5);

				assertThat(result).isEqualTo(1);
			}

			@Test
			void finds_zero() {
				int[] arr = {-10, -5, 0, 5, 10};

				int result = BinarySearch.search(arr, 0);

				assertThat(result).isEqualTo(2);
			}
		}

		@Nested
		class Not_Found {

			@Test
			void returns_negative_one_for_empty_array() {
				int[] arr = {};

				int result = BinarySearch.search(arr, 42);

				assertThat(result).isEqualTo(-1);
			}

			@Test
			void returns_negative_one_when_target_less_than_all() {
				int[] arr = {10, 20, 30, 40, 50};

				int result = BinarySearch.search(arr, 5);

				assertThat(result).isEqualTo(-1);
			}

			@Test
			void returns_negative_one_when_target_greater_than_all() {
				int[] arr = {10, 20, 30, 40, 50};

				int result = BinarySearch.search(arr, 100);

				assertThat(result).isEqualTo(-1);
			}

			@Test
			void returns_negative_one_for_gap_value() {
				int[] arr = {1, 3, 5, 7, 9};

				int result = BinarySearch.search(arr, 4);

				assertThat(result).isEqualTo(-1);
			}

			@Test
			void returns_negative_one_for_single_element_no_match() {
				int[] arr = {42};

				int result = BinarySearch.search(arr, 100);

				assertThat(result).isEqualTo(-1);
			}
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Efficiency {

		@Test
		void logarithmic_comparisons_for_large_array() {
			int[] arr = new int[1024];
			for (int i = 0; i < 1024; i++)
				arr[i] = i * 2;

			int[] result = BinarySearch.searchWithStepCount(arr, 1023);

			assertThat(result[1])
				.as("Binary search should take at most log2(n)+1 comparisons")
				.isLessThanOrEqualTo(11);
		}

		@Test
		void finds_first_element_in_few_steps() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			int[] result = BinarySearch.searchWithStepCount(arr, 0);

			assertThat(result[0]).isEqualTo(0);
			assertThat(result[1]).isLessThanOrEqualTo(10);
		}

		@Test
		void finds_last_element_in_few_steps() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			int[] result = BinarySearch.searchWithStepCount(arr, 999);

			assertThat(result[0]).isEqualTo(999);
			assertThat(result[1]).isLessThanOrEqualTo(10);
		}

		@Test
		void finds_middle_element_in_one_step() {
			int[] arr = {1, 2, 3, 4, 5, 6, 7};

			int[] result = BinarySearch.searchWithStepCount(arr, 4);

			assertThat(result[0]).isEqualTo(3);
			assertThat(result[1]).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Search_First {

		@Test
		void finds_first_occurrence_with_duplicates() {
			int[] arr = {1, 2, 2, 2, 3, 4, 5};

			int result = BinarySearch.searchFirst(arr, 2);

			assertThat(result).isEqualTo(1);
		}

		@Test
		void finds_first_when_all_same() {
			int[] arr = {5, 5, 5, 5, 5};

			int result = BinarySearch.searchFirst(arr, 5);

			assertThat(result).isEqualTo(0);
		}

		@Test
		void finds_unique_element() {
			int[] arr = {1, 2, 3, 4, 5};

			int result = BinarySearch.searchFirst(arr, 3);

			assertThat(result).isEqualTo(2);
		}

		@Test
		void returns_negative_one_when_not_found() {
			int[] arr = {1, 2, 4, 5};

			int result = BinarySearch.searchFirst(arr, 3);

			assertThat(result).isEqualTo(-1);
		}

		@Test
		void finds_first_at_beginning() {
			int[] arr = {1, 1, 1, 2, 3};

			int result = BinarySearch.searchFirst(arr, 1);

			assertThat(result).isEqualTo(0);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Search_Last {

		@Test
		void finds_last_occurrence_with_duplicates() {
			int[] arr = {1, 2, 2, 2, 3, 4, 5};

			int result = BinarySearch.searchLast(arr, 2);

			assertThat(result).isEqualTo(3);
		}

		@Test
		void finds_last_when_all_same() {
			int[] arr = {5, 5, 5, 5, 5};

			int result = BinarySearch.searchLast(arr, 5);

			assertThat(result).isEqualTo(4);
		}

		@Test
		void finds_unique_element() {
			int[] arr = {1, 2, 3, 4, 5};

			int result = BinarySearch.searchLast(arr, 3);

			assertThat(result).isEqualTo(2);
		}

		@Test
		void returns_negative_one_when_not_found() {
			int[] arr = {1, 2, 4, 5};

			int result = BinarySearch.searchLast(arr, 3);

			assertThat(result).isEqualTo(-1);
		}

		@Test
		void finds_last_at_end() {
			int[] arr = {1, 2, 3, 3, 3};

			int result = BinarySearch.searchLast(arr, 3);

			assertThat(result).isEqualTo(4);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Insertion_Point {

		@Test
		void returns_zero_for_empty_array() {
			int[] arr = {};

			int result = BinarySearch.findInsertionPoint(arr, 5);

			assertThat(result).isEqualTo(0);
		}

		@Test
		void returns_zero_when_less_than_first() {
			int[] arr = {10, 20, 30};

			int result = BinarySearch.findInsertionPoint(arr, 5);

			assertThat(result).isEqualTo(0);
		}

		@Test
		void returns_length_when_greater_than_last() {
			int[] arr = {10, 20, 30};

			int result = BinarySearch.findInsertionPoint(arr, 50);

			assertThat(result).isEqualTo(3);
		}

		@Test
		void returns_correct_middle_position() {
			int[] arr = {10, 20, 40, 50};

			int result = BinarySearch.findInsertionPoint(arr, 30);

			assertThat(result).isEqualTo(2);
		}

		@Test
		void returns_index_of_existing_element() {
			int[] arr = {10, 20, 30, 40, 50};

			int result = BinarySearch.findInsertionPoint(arr, 30);

			assertThat(result).isEqualTo(2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Edge_Cases {

		@Test
		void handles_integer_max_value() {
			int[] arr = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};

			int result = BinarySearch.search(arr, Integer.MAX_VALUE);

			assertThat(result).isEqualTo(2);
		}

		@Test
		void handles_integer_min_value() {
			int[] arr = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};

			int result = BinarySearch.search(arr, Integer.MIN_VALUE);

			assertThat(result).isEqualTo(0);
		}

		@Test
		void handles_large_array() {
			int[] arr = new int[100000];
			for (int i = 0; i < 100000; i++)
				arr[i] = i;

			int result = BinarySearch.search(arr, 99999);

			assertThat(result).isEqualTo(99999);
		}

		@Test
		void handles_consecutive_integers() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			for (int i = 0; i < 1000; i++) {
				int result = BinarySearch.search(arr, i);
				assertThat(result).isEqualTo(i);
			}
		}
	}
}
