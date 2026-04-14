package data_structures.queue;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class ArrayQueueTest {

	ArrayQueue<Integer> queue;

	@BeforeEach
	void setUp() {
		queue = new ArrayQueue<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Constructor {

		@Test
		void default_creates_empty_queue(SoftAssertions softly) {
			ArrayQueue<Integer> q = new ArrayQueue<>();
			softly.assertThat(q.size()).isZero();
			softly.assertThat(q.isEmpty()).isTrue();
		}

		@Test
		void capacity_creates_queue_with_specified_capacity(SoftAssertions softly) {
			ArrayQueue<Integer> q = new ArrayQueue<>(20);
			softly.assertThat(q.size()).isZero();
		}

		@Test
		void capacity_throws_on_negative() {
			assertThatThrownBy(() -> new ArrayQueue<Integer>(-1))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void capacity_throws_on_zero() {
			assertThatThrownBy(() -> new ArrayQueue<Integer>(0))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Enqueue {

		@Test
		void to_empty_queue(SoftAssertions softly) {
			queue.enqueue(42);
			softly.assertThat(queue.size()).isEqualTo(1);
			softly.assertThat(queue.peek()).isEqualTo(42);
		}

		@Test
		void to_single_element_queue(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			softly.assertThat(queue.size()).isEqualTo(2);
			softly.assertThat(queue.peek()).isEqualTo(1);
		}

		@Test
		void maintains_FIFO_order(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			softly.assertThat(queue.dequeue()).isEqualTo(1);
			softly.assertThat(queue.dequeue()).isEqualTo(2);
			softly.assertThat(queue.dequeue()).isEqualTo(3);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			queue.enqueue(null);
			softly.assertThat(queue.size()).isEqualTo(1);
			softly.assertThat(queue.peek()).isNull();
		}

		@Test
		void handles_duplicate_values(SoftAssertions softly) {
			queue.enqueue(42);
			queue.enqueue(42);
			queue.enqueue(42);
			softly.assertThat(queue.size()).isEqualTo(3);
		}

		@Test
		void triggers_resize_when_full(SoftAssertions softly) {
			ArrayQueue<Integer> q = new ArrayQueue<>(2);
			q.enqueue(1);
			q.enqueue(2);
			q.enqueue(3);
			softly.assertThat(q.size()).isEqualTo(3);
		}

		@Test
		void handles_circular_buffer_resize(SoftAssertions softly) {
			ArrayQueue<Integer> q = new ArrayQueue<>(3);
			q.enqueue(1);
			q.enqueue(2);
			q.dequeue();
			q.enqueue(3);
			q.enqueue(4);
			softly.assertThat(q.peek()).isEqualTo(2);
			softly.assertThat(q.size()).isEqualTo(3);
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
		void removes_only_element(SoftAssertions softly) {
			queue.enqueue(42);
			Integer dequeued = queue.dequeue();
			softly.assertThat(dequeued).isEqualTo(42);
			softly.assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void removes_front_element(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			Integer dequeued = queue.dequeue();
			softly.assertThat(dequeued).isEqualTo(1);
			softly.assertThat(queue.peek()).isEqualTo(2);
		}

		@Test
		void consecutive_dequeues_until_empty(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				queue.enqueue(i);
			for (int i = 0; i < 100; i++)
				queue.dequeue();
			softly.assertThat(queue.isEmpty()).isTrue();
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
		void returns_element_without_removing(SoftAssertions softly) {
			queue.enqueue(42);
			softly.assertThat(queue.peek()).isEqualTo(42);
			softly.assertThat(queue.size()).isEqualTo(1);
		}

		@Test
		void returns_front_element_without_removing(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			softly.assertThat(queue.peek()).isEqualTo(1);
			softly.assertThat(queue.size()).isEqualTo(3);
		}

		@Test
		void multiple_peeks_same_result(SoftAssertions softly) {
			queue.enqueue(42);
			softly.assertThat(queue.peek()).isEqualTo(queue.peek());
			softly.assertThat(queue.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Utilities {

		@Test
		void size_returns_element_count(SoftAssertions softly) {
			softly.assertThat(queue.size()).isZero();
			queue.enqueue(1);
			softly.assertThat(queue.size()).isEqualTo(1);
			queue.enqueue(2);
			softly.assertThat(queue.size()).isEqualTo(2);
		}

		@Test
		void isEmpty_returns_true_when_empty(SoftAssertions softly) {
			softly.assertThat(queue.isEmpty()).isTrue();
			queue.enqueue(1);
			softly.assertThat(queue.isEmpty()).isFalse();
		}

		@Test
		void contains_returns_true_when_present() {
			queue.enqueue(42);
			assertThat(queue.contains(42)).isTrue();
		}

		@Test
		void contains_returns_false_when_absent() {
			queue.enqueue(42);
			assertThat(queue.contains(99)).isFalse();
		}

		@Test
		void clear_removes_all_elements(SoftAssertions softly) {
			for (int i = 0; i < 50; i++)
				queue.enqueue(i);
			queue.clear();
			softly.assertThat(queue.isEmpty()).isTrue();
			softly.assertThat(queue.size()).isZero();
		}

		@Test
		void clear_on_empty_queue() {
			queue.clear();
			assertThat(queue.isEmpty()).isTrue();
		}

		@Test
		void toArray_returns_elements_front_to_rear(SoftAssertions softly) {
			queue.enqueue(1);
			queue.enqueue(2);
			queue.enqueue(3);
			Object[] result = queue.toArray();
			softly.assertThat(result).containsExactly(1, 2, 3);
		}

		@Test
		void toArray_empty_returns_empty_array() {
			Object[] result = queue.toArray();
			assertThat(result).isEmpty();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void enqueue_dequeue_interleaved(SoftAssertions softly) {
			for (int i = 0; i < 50; i++) {
				queue.enqueue(i);
				queue.enqueue(i + 100);
				queue.dequeue();
			}
			softly.assertThat(queue.size()).isEqualTo(50);
		}

		@Test
		void stress_mixed_operations(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				queue.enqueue(i);
			for (int i = 0; i < 50; i++)
				queue.dequeue();
			for (int i = 0; i < 25; i++)
				queue.enqueue(i + 1000);
			softly.assertThat(queue.size()).isEqualTo(75);
			softly.assertThat(queue.peek()).isEqualTo(50);
		}

		@Test
		void rebuild_after_clear(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				queue.enqueue(i);
			queue.clear();
			for (int i = 0; i < 50; i++)
				queue.enqueue(i + 1000);
			softly.assertThat(queue.peek()).isEqualTo(1000);
			softly.assertThat(queue.size()).isEqualTo(50);
		}

		@Test
		void FIFO_verification(SoftAssertions softly) {
			List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
			for (Integer i : input)
				queue.enqueue(i);
			List<Integer> output = new ArrayList<>();
			while (!queue.isEmpty())
				output.add(queue.dequeue());
			softly.assertThat(output).containsExactly(1, 2, 3, 4, 5);
		}

		@Test
		void circular_buffer_wraparound(SoftAssertions softly) {
			ArrayQueue<Integer> q = new ArrayQueue<>(5);
			for (int i = 0; i < 5; i++)
				q.enqueue(i);
			q.dequeue();
			q.dequeue();
			q.enqueue(5);
			q.enqueue(6);
			softly.assertThat(q.size()).isEqualTo(5);
			softly.assertThat(q.peek()).isEqualTo(2);
		}
	}
}
