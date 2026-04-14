package data_structures.deque;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class LinkedDequeTest {

	LinkedDeque<Integer> deque;

	@BeforeEach
	void init() {
		deque = new LinkedDeque<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class AddFirst {

		@Test
		void to_empty_deque(SoftAssertions softly) {
			deque.addFirst(42);
			softly.assertThat(deque.peekFirst()).isEqualTo(42);
			softly.assertThat(deque.peekLast()).isEqualTo(42);
			softly.assertThat(deque.size()).isEqualTo(1);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			deque.addFirst(null);
			softly.assertThat(deque.isEmpty()).isFalse();
			softly.assertThat(deque.size()).isEqualTo(1);
			softly.assertThat(deque.peekFirst()).isNull();
		}

		@Test
		void to_single_element_deque(SoftAssertions softly) {
			deque.addFirst(1);
			deque.addFirst(2);
			softly.assertThat(deque.peekFirst()).isEqualTo(2);
			softly.assertThat(deque.peekLast()).isEqualTo(1);
			softly.assertThat(deque.size()).isEqualTo(2);
		}

		@Test
		void maintains_front_ordering(SoftAssertions softly) {
			deque.addFirst(1);
			deque.addFirst(2);
			deque.addFirst(3);
			softly.assertThat(deque.peekFirst()).isEqualTo(3);
			softly.assertThat(deque.peekLast()).isEqualTo(1);
		}

		@Test
		void handles_duplicate_elements() {
			deque.addFirst(1);
			deque.addFirst(1);
			deque.addFirst(1);
			assertThat(deque.size()).isEqualTo(3);
		}

		@Test
		void stress_1000_elements(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++)
				deque.addFirst(i);
			softly.assertThat(deque.size()).isEqualTo(1000);
			softly.assertThat(deque.peekFirst()).isEqualTo(999);
			softly.assertThat(deque.peekLast()).isEqualTo(0);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class AddLast {

		@Test
		void to_empty_deque(SoftAssertions softly) {
			deque.addLast(42);
			softly.assertThat(deque.peekFirst()).isEqualTo(42);
			softly.assertThat(deque.peekLast()).isEqualTo(42);
			softly.assertThat(deque.size()).isEqualTo(1);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			deque.addLast(null);
			softly.assertThat(deque.isEmpty()).isFalse();
			softly.assertThat(deque.size()).isEqualTo(1);
			softly.assertThat(deque.peekLast()).isNull();
		}

		@Test
		void to_single_element_deque(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			softly.assertThat(deque.peekFirst()).isEqualTo(1);
			softly.assertThat(deque.peekLast()).isEqualTo(2);
			softly.assertThat(deque.size()).isEqualTo(2);
		}

		@Test
		void maintains_rear_ordering(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			deque.addLast(3);
			softly.assertThat(deque.peekFirst()).isEqualTo(1);
			softly.assertThat(deque.peekLast()).isEqualTo(3);
		}

		@Test
		void handles_duplicate_elements() {
			deque.addLast(1);
			deque.addLast(1);
			deque.addLast(1);
			assertThat(deque.size()).isEqualTo(3);
		}

		@Test
		void stress_1000_elements(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++)
				deque.addLast(i);
			softly.assertThat(deque.size()).isEqualTo(1000);
			softly.assertThat(deque.peekFirst()).isEqualTo(0);
			softly.assertThat(deque.peekLast()).isEqualTo(999);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class RemoveFirst {

		@Test
		void throws_on_empty_deque() {
			assertThatThrownBy(() -> deque.removeFirst())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void single_element_becomes_empty() {
			deque.addFirst(42);
			deque.removeFirst();
			assertThat(deque.isEmpty()).isTrue();
		}

		@Test
		void returns_and_removes_element() {
			deque.addFirst(42);
			Integer removed = deque.removeFirst();
			assertThat(removed).isEqualTo(42);
		}

		@Test
		void can_remove_null_element() {
			deque.addFirst(null);
			Integer removed = deque.removeFirst();
			assertThat(removed).isNull();
		}

		@Test
		void removes_and_returns_front_element(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			Integer removed = deque.removeFirst();
			softly.assertThat(removed).isEqualTo(1);
			softly.assertThat(deque.peekFirst()).isEqualTo(2);
			softly.assertThat(deque.size()).isEqualTo(1);
		}

		@Test
		void consecutive_removes_clear_deque(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			deque.removeFirst();
			deque.removeFirst();
			softly.assertThat(deque.isEmpty()).isTrue();
		}

		@Test
		void order_preserved_large(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				deque.addLast(i);
			for (int i = 0; i < 100; i++)
				softly.assertThat(deque.removeFirst()).isEqualTo(i);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class RemoveLast {

		@Test
		void throws_on_empty_deque() {
			assertThatThrownBy(() -> deque.removeLast())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void single_element_becomes_empty() {
			deque.addLast(42);
			deque.removeLast();
			assertThat(deque.isEmpty()).isTrue();
		}

		@Test
		void returns_and_removes_element() {
			deque.addLast(42);
			Integer removed = deque.removeLast();
			assertThat(removed).isEqualTo(42);
		}

		@Test
		void can_remove_null_element() {
			deque.addLast(null);
			Integer removed = deque.removeLast();
			assertThat(removed).isNull();
		}

		@Test
		void removes_and_returns_rear_element(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			Integer removed = deque.removeLast();
			softly.assertThat(removed).isEqualTo(2);
			softly.assertThat(deque.peekLast()).isEqualTo(1);
			softly.assertThat(deque.size()).isEqualTo(1);
		}

		@Test
		void consecutive_removes_clear_deque(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			deque.removeLast();
			deque.removeLast();
			softly.assertThat(deque.isEmpty()).isTrue();
		}

		@Test
		void LIFO_order_large(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				deque.addLast(i);
			for (int i = 99; i >= 0; i--)
				softly.assertThat(deque.removeLast()).isEqualTo(i);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class PeekFirst {

		@Test
		void throws_on_empty_deque() {
			assertThatThrownBy(() -> deque.peekFirst())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void returns_front_element() {
			deque.addFirst(42);
			assertThat(deque.peekFirst()).isEqualTo(42);
		}

		@Test
		void returns_null_when_front_is_null() {
			deque.addFirst(null);
			assertThat(deque.peekFirst()).isNull();
		}

		@Test
		void does_not_remove_element(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			softly.assertThat(deque.peekFirst()).isEqualTo(1);
			softly.assertThat(deque.peekFirst()).isEqualTo(1);
			softly.assertThat(deque.size()).isEqualTo(2);
		}

		@Test
		void peek_between_adds(SoftAssertions softly) {
			deque.addFirst(1);
			softly.assertThat(deque.peekFirst()).isEqualTo(1);
			deque.addFirst(2);
			softly.assertThat(deque.peekFirst()).isEqualTo(2);
		}

		@Test
		void after_partial_remove(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				deque.addLast(i);
			for (int i = 0; i < 50; i++)
				deque.removeFirst();
			softly.assertThat(deque.peekFirst()).isEqualTo(50);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class PeekLast {

		@Test
		void throws_on_empty_deque() {
			assertThatThrownBy(() -> deque.peekLast())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void returns_rear_element() {
			deque.addLast(42);
			assertThat(deque.peekLast()).isEqualTo(42);
		}

		@Test
		void returns_null_when_rear_is_null() {
			deque.addLast(null);
			assertThat(deque.peekLast()).isNull();
		}

		@Test
		void does_not_remove_element(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			softly.assertThat(deque.peekLast()).isEqualTo(2);
			softly.assertThat(deque.peekLast()).isEqualTo(2);
			softly.assertThat(deque.size()).isEqualTo(2);
		}

		@Test
		void peek_between_adds(SoftAssertions softly) {
			deque.addLast(1);
			softly.assertThat(deque.peekLast()).isEqualTo(1);
			deque.addLast(2);
			softly.assertThat(deque.peekLast()).isEqualTo(2);
		}

		@Test
		void after_partial_remove(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				deque.addLast(i);
			for (int i = 0; i < 50; i++)
				deque.removeLast();
			softly.assertThat(deque.peekLast()).isEqualTo(49);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Utilities {

		@Test
		void size_returns_zero_for_new_deque() {
			assertThat(deque.size()).isZero();
		}

		@Test
		void size_increments_after_add(SoftAssertions softly) {
			deque.addFirst(1);
			softly.assertThat(deque.size()).isEqualTo(1);
			deque.addLast(2);
			softly.assertThat(deque.size()).isEqualTo(2);
		}

		@Test
		void size_decrements_after_remove(SoftAssertions softly) {
			deque.addFirst(1);
			deque.addFirst(2);
			deque.removeFirst();
			softly.assertThat(deque.size()).isEqualTo(1);
		}

		@Test
		void isEmpty_true_for_new_deque() {
			assertThat(deque.isEmpty()).isTrue();
		}

		@Test
		void isEmpty_false_after_add() {
			deque.addFirst(1);
			assertThat(deque.isEmpty()).isFalse();
		}

		@Test
		void isEmpty_true_after_clear(SoftAssertions softly) {
			deque.addFirst(1);
			deque.clear();
			softly.assertThat(deque.isEmpty()).isTrue();
		}

		@Test
		void contains_returns_true_for_existing_element() {
			deque.addFirst(1);
			deque.addLast(2);
			assertThat(deque.contains(1)).isTrue();
			assertThat(deque.contains(2)).isTrue();
		}

		@Test
		void contains_returns_false_for_non_existing_element() {
			deque.addFirst(1);
			assertThat(deque.contains(3)).isFalse();
		}

		@Test
		void contains_handles_null() {
			deque.addLast(null);
			assertThat(deque.contains(null)).isTrue();
		}

		@Test
		void clear_empties_deque(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				deque.addLast(i);
			deque.clear();
			softly.assertThat(deque.isEmpty()).isTrue();
			softly.assertThat(deque.size()).isZero();
		}

		@Test
		void can_add_after_clear(SoftAssertions softly) {
			deque.addFirst(1);
			deque.clear();
			deque.addLast(2);
			softly.assertThat(deque.peekFirst()).isEqualTo(2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void works_as_stack(SoftAssertions softly) {
			deque.addFirst(1);
			deque.addFirst(2);
			deque.addFirst(3);
			softly.assertThat(deque.removeFirst()).isEqualTo(3);
			softly.assertThat(deque.removeFirst()).isEqualTo(2);
			softly.assertThat(deque.removeFirst()).isEqualTo(1);
		}

		@Test
		void works_as_queue(SoftAssertions softly) {
			deque.addLast(1);
			deque.addLast(2);
			deque.addLast(3);
			softly.assertThat(deque.removeFirst()).isEqualTo(1);
			softly.assertThat(deque.removeFirst()).isEqualTo(2);
			softly.assertThat(deque.removeFirst()).isEqualTo(3);
		}

		@Test
		void addFirst_removeLast(SoftAssertions softly) {
			deque.addFirst(1);
			deque.addFirst(2);
			softly.assertThat(deque.removeLast()).isEqualTo(1);
			softly.assertThat(deque.removeLast()).isEqualTo(2);
		}

		@Test
		void stress_large_number_of_elements(SoftAssertions softly) {
			int count = 10000;
			for (int i = 0; i < count; i++)
				deque.addLast(i);
			softly.assertThat(deque.size()).isEqualTo(count);
			for (int i = 0; i < count; i++)
				softly.assertThat(deque.removeFirst()).isEqualTo(i);
			softly.assertThat(deque.isEmpty()).isTrue();
		}
	}
}
