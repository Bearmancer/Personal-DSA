package data_structures.heap;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class MinHeapTest {

	MinHeap<Integer> heap;

	@BeforeEach
	void init() {
		heap = new MinHeap<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Insert {

		@Test
		void inserts_into_empty_heap(SoftAssertions softly) {
			heap.insert(10);

			softly.assertThat(heap.size()).isEqualTo(1);
			softly.assertThat(heap.peekMin()).isEqualTo(10);
		}

		@Test
		void smaller_element_becomes_root(SoftAssertions softly) {
			heap.insert(10);
			heap.insert(5);

			softly.assertThat(heap.peekMin()).isEqualTo(5);
		}

		@Test
		void larger_element_stays_below(SoftAssertions softly) {
			heap.insert(5);
			heap.insert(10);

			softly.assertThat(heap.peekMin()).isEqualTo(5);
		}

		@Test
		void maintains_heap_property(SoftAssertions softly) {
			heap.insert(10);
			heap.insert(5);
			heap.insert(15);
			heap.insert(3);

			softly.assertThat(heap.peekMin()).isEqualTo(3);
		}

		@Test
		void handles_duplicates(SoftAssertions softly) {
			heap.insert(5);
			heap.insert(5);
			heap.insert(5);

			softly.assertThat(heap.size()).isEqualTo(3);
			softly.assertThat(heap.peekMin()).isEqualTo(5);
		}

		@Test
		void throws_on_null() {
			assertThatThrownBy(() -> heap.insert(null))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class ExtractMin {

		@Test
		void throws_on_empty_heap() {
			assertThatThrownBy(() -> heap.extractMin())
				.isInstanceOf(IllegalStateException.class);
		}

		@Test
		void extracts_only_element(SoftAssertions softly) {
			heap.insert(10);
			Integer min = heap.extractMin();

			softly.assertThat(min).isEqualTo(10);
			softly.assertThat(heap.isEmpty()).isTrue();
		}

		@Test
		void extracts_minimum(SoftAssertions softly) {
			heap.insert(10);
			heap.insert(5);
			heap.insert(15);
			Integer min = heap.extractMin();

			softly.assertThat(min).isEqualTo(5);
			softly.assertThat(heap.peekMin()).isEqualTo(10);
		}

		@Test
		void maintains_heap_after_extract(SoftAssertions softly) {
			heap.insert(3);
			heap.insert(5);
			heap.insert(7);
			heap.insert(10);
			heap.extractMin();

			softly.assertThat(heap.peekMin()).isEqualTo(5);
		}

		@Test
		void extracts_in_sorted_order(SoftAssertions softly) {
			heap.insert(5);
			heap.insert(3);
			heap.insert(7);
			heap.insert(1);

			softly.assertThat(heap.extractMin()).isEqualTo(1);
			softly.assertThat(heap.extractMin()).isEqualTo(3);
			softly.assertThat(heap.extractMin()).isEqualTo(5);
			softly.assertThat(heap.extractMin()).isEqualTo(7);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class PeekMin {

		@Test
		void throws_on_empty_heap() {
			assertThatThrownBy(() -> heap.peekMin())
				.isInstanceOf(IllegalStateException.class);
		}

		@Test
		void returns_minimum_without_removing(SoftAssertions softly) {
			heap.insert(10);
			heap.insert(5);

			softly.assertThat(heap.peekMin()).isEqualTo(5);
			softly.assertThat(heap.peekMin()).isEqualTo(5);
			softly.assertThat(heap.size()).isEqualTo(2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Size_And_IsEmpty {

		@Test
		void empty_heap_has_size_zero() {
			assertThat(heap.size()).isZero();
		}

		@Test
		void isEmpty_returns_true_for_empty() {
			assertThat(heap.isEmpty()).isTrue();
		}

		@Test
		void isEmpty_returns_false_after_insert() {
			heap.insert(10);

			assertThat(heap.isEmpty()).isFalse();
		}

		@Test
		void size_updates_correctly(SoftAssertions softly) {
			heap.insert(10);
			softly.assertThat(heap.size()).isEqualTo(1);
			heap.insert(5);
			softly.assertThat(heap.size()).isEqualTo(2);
			heap.extractMin();
			softly.assertThat(heap.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Clear {

		@Test
		void clears_all_elements(SoftAssertions softly) {
			heap.insert(10);
			heap.insert(5);
			heap.clear();

			softly.assertThat(heap.isEmpty()).isTrue();
			softly.assertThat(heap.size()).isZero();
		}

		@Test
		void can_insert_after_clear(SoftAssertions softly) {
			heap.insert(10);
			heap.clear();
			heap.insert(20);

			softly.assertThat(heap.peekMin()).isEqualTo(20);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Heapify {

		@Test
		void builds_heap_from_array(SoftAssertions softly) {
			Integer[] arr = {5, 3, 8, 1, 2};
			MinHeap<Integer> heapFromArray = new MinHeap<>(arr);

			softly.assertThat(heapFromArray.size()).isEqualTo(5);
			softly.assertThat(heapFromArray.peekMin()).isEqualTo(1);
		}

		@Test
		void extracts_in_sorted_order_after_heapify(SoftAssertions softly) {
			Integer[] arr = {5, 3, 8, 1, 2};
			MinHeap<Integer> heapFromArray = new MinHeap<>(arr);

			softly.assertThat(heapFromArray.extractMin()).isEqualTo(1);
			softly.assertThat(heapFromArray.extractMin()).isEqualTo(2);
			softly.assertThat(heapFromArray.extractMin()).isEqualTo(3);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void stress_test_1000_elements(SoftAssertions softly) {
			for (int i = 1000; i > 0; i--) {
				heap.insert(i);
			}

			softly.assertThat(heap.size()).isEqualTo(1000);
			softly.assertThat(heap.peekMin()).isEqualTo(1);

			for (int i = 1; i <= 1000; i++) {
				softly.assertThat(heap.extractMin()).isEqualTo(i);
			}
		}

		@Test
		void mixed_insert_extract(SoftAssertions softly) {
			heap.insert(5);
			heap.insert(3);
			softly.assertThat(heap.extractMin()).isEqualTo(3);
			heap.insert(1);
			heap.insert(4);
			softly.assertThat(heap.extractMin()).isEqualTo(1);
			softly.assertThat(heap.extractMin()).isEqualTo(4);
			softly.assertThat(heap.extractMin()).isEqualTo(5);
		}
	}
}
