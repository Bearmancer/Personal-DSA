package algorithms;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"unused"})
@ExtendWith(SoftAssertionsExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LinearSearchTest {

	@Nested
	@Disabled("Awaiting implementation")
	class Search {

		@Nested
		class Found {

			@Test
			void finds_element_at_beginning() {
				int[] arr = {1, 2, 3, 4, 5};

				int result = LinearSearch.search(arr, 1);

				assertThat(result).isEqualTo(0);
			}

			@Test
			void finds_element_at_end() {
				int[] arr = {1, 2, 3, 4, 5};

				int result = LinearSearch.search(arr, 5);

				assertThat(result).isEqualTo(4);
			}

			@Test
			void finds_element_in_middle() {
				int[] arr = {1, 2, 3, 4, 5};

				int result = LinearSearch.search(arr, 3);

				assertThat(result).isEqualTo(2);
			}

			@Test
			void finds_element_in_single_element_array() {
				int[] arr = {42};

				int result = LinearSearch.search(arr, 42);

				assertThat(result).isEqualTo(0);
			}

			@Test
			void finds_first_occurrence_of_duplicate() {
				int[] arr = {1, 2, 3, 2, 5};

				int result = LinearSearch.search(arr, 2);

				assertThat(result).isEqualTo(1);
			}

			@Test
			void finds_in_unsorted_array() {
				int[] arr = {5, 2, 8, 1, 9};

				int result = LinearSearch.search(arr, 8);

				assertThat(result).isEqualTo(2);
			}
		}

		@Nested
		class Not_Found {

			@Test
			void returns_negative_one_for_empty_array() {
				int[] arr = {};

				int result = LinearSearch.search(arr, 42);

				assertThat(result).isEqualTo(-1);
			}

			@Test
			void returns_negative_one_when_not_present() {
				int[] arr = {1, 2, 3, 4, 5};

				int result = LinearSearch.search(arr, 99);

				assertThat(result).isEqualTo(-1);
			}

			@Test
			void returns_negative_one_for_single_element_no_match() {
				int[] arr = {42};

				int result = LinearSearch.search(arr, 100);

				assertThat(result).isEqualTo(-1);
			}
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Efficiency {

		@Test
		void best_case_first_element() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			int[] result = LinearSearch.searchWithStepCount(arr, 0);

			assertThat(result[0]).isEqualTo(0);
			assertThat(result[1]).isEqualTo(1);
		}

		@Test
		void worst_case_last_element() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			int[] result = LinearSearch.searchWithStepCount(arr, 999);

			assertThat(result[0]).isEqualTo(999);
			assertThat(result[1]).isEqualTo(1000);
		}

		@Test
		void worst_case_not_found() {
			int[] arr = new int[1000];
			for (int i = 0; i < 1000; i++)
				arr[i] = i;

			int[] result = LinearSearch.searchWithStepCount(arr, 9999);

			assertThat(result[0]).isEqualTo(-1);
			assertThat(result[1]).isEqualTo(1000);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class FindAll {

		@Test
		void returns_empty_array_when_not_found() {
			int[] arr = {1, 2, 3, 4, 5};

			int[] result = LinearSearch.findAll(arr, 99);

			assertThat(result).isEmpty();
		}

		@Test
		void returns_single_index() {
			int[] arr = {1, 2, 3, 4, 5};

			int[] result = LinearSearch.findAll(arr, 3);

			assertThat(result).containsExactly(2);
		}

		@Test
		void returns_all_indices() {
			int[] arr = {1, 2, 2, 3, 2, 5};

			int[] result = LinearSearch.findAll(arr, 2);

			assertThat(result).containsExactly(1, 2, 4);
		}

		@Test
		void handles_empty_array() {
			int[] arr = {};

			int[] result = LinearSearch.findAll(arr, 5);

			assertThat(result).isEmpty();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Count {

		@Test
		void returns_zero_when_not_found() {
			int[] arr = {1, 2, 3, 4, 5};

			int count = LinearSearch.count(arr, 99);

			assertThat(count).isZero();
		}

		@Test
		void returns_one_for_unique_element() {
			int[] arr = {1, 2, 3, 4, 5};

			int count = LinearSearch.count(arr, 3);

			assertThat(count).isEqualTo(1);
		}

		@Test
		void returns_count_for_duplicates() {
			int[] arr = {1, 2, 2, 3, 2, 5};

			int count = LinearSearch.count(arr, 2);

			assertThat(count).isEqualTo(3);
		}

		@Test
		void handles_all_same_elements() {
			int[] arr = {5, 5, 5, 5, 5};

			int count = LinearSearch.count(arr, 5);

			assertThat(count).isEqualTo(5);
		}

		@Test
		void handles_empty_array() {
			int[] arr = {};

			int count = LinearSearch.count(arr, 5);

			assertThat(count).isZero();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void works_with_negative_numbers() {
			int[] arr = {-5, -3, 0, 3, 5};

			assertThat(LinearSearch.search(arr, -3)).isEqualTo(1);
		}

		@Test
		void works_with_large_array() {
			int[] arr = new int[10000];
			for (int i = 0; i < 10000; i++)
				arr[i] = i * 2;

			assertThat(LinearSearch.search(arr, 9998)).isEqualTo(4999);
		}
	}
}
