package data_structures.queue;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class LinkedQueueTest {

	LinkedQueue<Integer> queue;

	@BeforeEach
	void init() {
		queue = new LinkedQueue<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Enqueue {

		@Test
		void to_empty_queue(SoftAssertions softly) {
			queue.enqueue(42);
			softly.assertThat(queue.peek()).isEqualTo(42);
			softly.assertThat(queue.size()).isEqualTo(1);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			queue.enqueue(null);
			softly.assertThat(queue.isEmpty()).isFalse();
			softly.assertThat(queue.size()).isEqualTo(1);
			softly.assertThat(queue.peek()).isNull();
		}

		@Test
		void to_single_element_queue(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			softly.assertThat(queue.peek()).isEqualTo(1);
			softly.assertThat(queue.size()).isEqualTo(2);
		}

		@Test
		void after_dequeue_to_empty(SoftAssertions softly) {
			queue.enqueue(1);
			queue.dequeue();
			queue.enqueue(2);
			softly.assertThat(queue.peek()).isEqualTo(2);
			softly.assertThat(queue.size()).isEqualTo(1);
		}

		@Test
		void maintains_FIFO_order(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			softly.assertThat(queue.peek()).isEqualTo(1);
		}

		@Test
		void handles_duplicate_elements() {
			queue.enqueue(1);
			queue.enqueue(1);
			queue.enqueue(1);
			assertThat(queue.size()).isEqualTo(3);
		}

		@Test
		void stress_1000_elements(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++)
				queue.enqueue(i);
			softly.assertThat(queue.size()).isEqualTo(1000);
			softly.assertThat(queue.peek()).isEqualTo(0);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Dequeue {

		@Test
		void throws_on_empty_queue() {
			assertThatThrownBy(() -> queue.dequeue())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void single_element_becomes_empty() {
			queue.enqueue(42);
			queue.dequeue();
			assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void returns_and_removes_element() {
			queue.enqueue(42);
			Integer dequeued = queue.dequeue();
			assertThat(dequeued).isEqualTo(42);
		}

		@Test
		void can_dequeue_null_element() {
			queue.enqueue(null);
			Integer dequeued = queue.dequeue();
			assertThat(dequeued).isNull();
		}

		@Test
		void removes_and_returns_front_element(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			Integer dequeued = queue.dequeue();
			softly.assertThat(dequeued).isEqualTo(1);
			softly.assertThat(queue.peek()).isEqualTo(2);
			softly.assertThat(queue.size()).isEqualTo(1);
		}

		@Test
		void consecutive_dequeues_clear_queue(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.dequeue();
			queue.dequeue();
			softly.assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void FIFO_order_large(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				queue.enqueue(i);
			for (int i = 0; i < 100; i++)
				softly.assertThat(queue.dequeue()).isEqualTo(i);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Peek {

		@Test
		void throws_on_empty_queue() {
			assertThatThrownBy(() -> queue.peek())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void returns_front_element() {
			queue.enqueue(42);
			assertThat(queue.peek()).isEqualTo(42);
		}

		@Test
		void returns_null_when_front_is_null() {
			queue.enqueue(null);
			assertThat(queue.peek()).isNull();
		}

		@Test
		void does_not_remove_element(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			softly.assertThat(queue.peek()).isEqualTo(1);
			softly.assertThat(queue.peek()).isEqualTo(1);
			softly.assertThat(queue.size()).isEqualTo(2);
		}

		@Test
		void peek_between_enqueues(SoftAssertions softly) {
			queue.enqueue(1);
			softly.assertThat(queue.peek()).isEqualTo(1);
			queue.enqueue(2);
			softly.assertThat(queue.peek()).isEqualTo(1);
		}

		@Test
		void after_partial_dequeue(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				queue.enqueue(i);
			for (int i = 0; i < 50; i++)
				queue.dequeue();
			softly.assertThat(queue.peek()).isEqualTo(50);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Utilities {

		@Test
		void size_returns_zero_for_new_queue() {
			assertThat(queue.size()).isZero();
		}

		@Test
		void size_increments_after_enqueue() {
			queue.enqueue(1);
			assertThat(queue.size()).isEqualTo(1);
		}

		@Test
		void size_decrements_after_dequeue() {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.dequeue();
			assertThat(queue.size()).isEqualTo(1);
		}

		@Test
		void isEmpty_true_for_new_queue() {
			assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void isEmpty_false_after_enqueue() {
			queue.enqueue(1);
			assertThat(queue.isEmpty()).isFalse();
		}

		@Test
		void isEmpty_true_after_removal_of_all_elements() {
			queue.enqueue(1);
			queue.dequeue();
			assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void contains_returns_true_for_existing_element(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			softly.assertThat(queue.contains(1)).isTrue();
			softly.assertThat(queue.contains(2)).isTrue();
			softly.assertThat(queue.contains(3)).isTrue();
		}

		@Test
		void contains_returns_false_for_non_existing_element() {
			queue.enqueue(1);
			assertThat(queue.contains(3)).isFalse();
		}

		@Test
		void contains_handles_null() {
			queue.enqueue(1);
			queue.enqueue(null);
			assertThat(queue.contains(null)).isTrue();
		}

		@Test
		void toArray_converts_front_to_rear(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			Object[] array = queue.toArray();
			softly.assertThat(array).containsExactly(1, 2, 3);
		}

		@Test
		void toArray_empty_returns_empty_array() {
			Object[] array = queue.toArray();
			assertThat(array).isEmpty();
		}

		@Test
		void iterator_from_front_to_rear(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			java.util.Iterator<Integer> iter = queue.iterator();
			softly.assertThat(iter.hasNext()).isTrue();
			softly.assertThat(iter.next()).isEqualTo(1);
			softly.assertThat(iter.next()).isEqualTo(2);
			softly.assertThat(iter.next()).isEqualTo(3);
			softly.assertThat(iter.hasNext()).isFalse();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void strict_FIFO_ordering(SoftAssertions softly) {
			for (int i = 1; i <= 5; i++)
				queue.enqueue(i);
			for (int i = 1; i <= 5; i++)
				softly.assertThat(queue.dequeue()).isEqualTo(i);
		}

		@Test
		void FIFO_with_interleaved_operations(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			softly.assertThat(queue.dequeue()).isEqualTo(1);
			queue.enqueue(3);
			softly.assertThat(queue.dequeue()).isEqualTo(2);
			softly.assertThat(queue.dequeue()).isEqualTo(3);
		}

		@Test
		void stress_large_number_of_elements(SoftAssertions softly) {
			int count = 10000;
			for (int i = 0; i < count; i++)
				queue.enqueue(i);
			softly.assertThat(queue.size()).isEqualTo(count);
			for (int i = 0; i < count; i++)
				softly.assertThat(queue.dequeue()).isEqualTo(i);
			softly.assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void alternating_enqueue_dequeue(SoftAssertions softly) {
			for (int i = 0; i < 100; i++) {
				queue.enqueue(i);
				softly.assertThat(queue.dequeue()).isEqualTo(i);
			}
			softly.assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void handles_String_type(SoftAssertions softly) {
			LinkedQueue<String> stringQueue = new LinkedQueue<>();
			stringQueue.enqueue("first");
			stringQueue.enqueue("second");
			softly.assertThat(stringQueue.dequeue()).isEqualTo("first");
			softly.assertThat(stringQueue.dequeue()).isEqualTo("second");
		}
	}
}
