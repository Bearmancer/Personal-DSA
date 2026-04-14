package data_structures.stack;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class LinkedStackTest {

	LinkedStack<Integer> stack;

	@BeforeEach
	void init() {
		stack = new LinkedStack<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Push {

		@Test
		void to_empty_stack(SoftAssertions softly) {
			stack.push(42);
			softly.assertThat(stack.peek()).isEqualTo(42);
			softly.assertThat(stack.size()).isEqualTo(1);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			stack.push(null);
			softly.assertThat(stack.isEmpty()).isFalse();
			softly.assertThat(stack.size()).isEqualTo(1);
			softly.assertThat(stack.peek()).isNull();
		}

		@Test
		void to_single_element_stack(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			softly.assertThat(stack.peek()).isEqualTo(2);
			softly.assertThat(stack.size()).isEqualTo(2);
		}

		@Test
		void maintains_LIFO_order(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			softly.assertThat(stack.peek()).isEqualTo(3);
		}

		@Test
		void handles_duplicate_elements() {
			stack.push(1);
			stack.push(1);
			stack.push(1);
			assertThat(stack.size()).isEqualTo(3);
		}

		@Test
		void stress_1000_elements(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++)
				stack.push(i);
			softly.assertThat(stack.size()).isEqualTo(1000);
			softly.assertThat(stack.peek()).isEqualTo(999);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Pop {

		@Test
		void throws_on_empty_stack() {
			assertThatThrownBy(() -> stack.pop())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void single_element_becomes_empty() {
			stack.push(42);
			stack.pop();
			assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void returns_and_removes_element() {
			stack.push(42);
			Integer popped = stack.pop();
			assertThat(popped).isEqualTo(42);
		}

		@Test
		void can_pop_null_element() {
			stack.push(null);
			Integer popped = stack.pop();
			assertThat(popped).isNull();
		}

		@Test
		void removes_and_returns_top_element(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			Integer popped = stack.pop();
			softly.assertThat(popped).isEqualTo(2);
			softly.assertThat(stack.peek()).isEqualTo(1);
			softly.assertThat(stack.size()).isEqualTo(1);
		}

		@Test
		void consecutive_pops_clear_stack(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			stack.pop();
			stack.pop();
			softly.assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void LIFO_order_large(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				stack.push(i);
			for (int i = 99; i >= 0; i--)
				softly.assertThat(stack.pop()).isEqualTo(i);
		}

		@Test
		void push_after_pop_to_empty(SoftAssertions softly) {
			stack.push(1);
			stack.pop();
			stack.push(2);
			softly.assertThat(stack.peek()).isEqualTo(2);
			softly.assertThat(stack.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Peek {

		@Test
		void throws_on_empty_stack() {
			assertThatThrownBy(() -> stack.peek())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void returns_top_element() {
			stack.push(42);
			assertThat(stack.peek()).isEqualTo(42);
		}

		@Test
		void returns_null_when_top_is_null() {
			stack.push(null);
			assertThat(stack.peek()).isNull();
		}

		@Test
		void does_not_remove_element(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			softly.assertThat(stack.peek()).isEqualTo(2);
			softly.assertThat(stack.peek()).isEqualTo(2);
			softly.assertThat(stack.size()).isEqualTo(2);
		}

		@Test
		void peek_always_returns_most_recent(SoftAssertions softly) {
			for (int i = 0; i < 100; i++) {
				stack.push(i);
				softly.assertThat(stack.peek()).isEqualTo(i);
			}
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Search {

		@Test
		void returns_minus_one_for_empty_stack() {
			assertThat(stack.search(1)).isEqualTo(-1);
		}

		@Test
		void find_only_element() {
			stack.push(42);
			assertThat(stack.search(42)).isEqualTo(1);
		}

		@Test
		void returns_minus_one_for_non_existent() {
			stack.push(42);
			assertThat(stack.search(99)).isEqualTo(-1);
		}

		@Test
		void find_element_at_top() {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			assertThat(stack.search(3)).isEqualTo(1);
		}

		@Test
		void find_element_in_middle() {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			assertThat(stack.search(2)).isEqualTo(2);
		}

		@Test
		void find_element_at_bottom() {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			assertThat(stack.search(1)).isEqualTo(3);
		}

		@Test
		void find_first_occurrence_from_top() {
			stack.push(1);
			stack.push(2);
			stack.push(1);
			assertThat(stack.search(1)).isEqualTo(1);
		}

		@Test
		void search_for_null_value() {
			stack.push(1);
			stack.push(null);
			stack.push(2);
			assertThat(stack.search(null)).isEqualTo(2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Utilities {

		@Test
		void size_returns_zero_for_new_stack() {
			assertThat(stack.size()).isZero();
		}

		@Test
		void size_increments_after_push() {
			stack.push(1);
			assertThat(stack.size()).isEqualTo(1);
		}

		@Test
		void size_decrements_after_pop() {
			stack.push(1);
			stack.push(2);
			stack.pop();
			assertThat(stack.size()).isEqualTo(1);
		}

		@Test
		void isEmpty_true_for_new_stack() {
			assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void isEmpty_false_after_push() {
			stack.push(1);
			assertThat(stack.isEmpty()).isFalse();
		}

		@Test
		void isEmpty_true_after_removal_of_all_elements() {
			stack.push(1);
			stack.pop();
			assertThat(stack.isEmpty()).isTrue();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void strict_LIFO_ordering(SoftAssertions softly) {
			for (int i = 1; i <= 5; i++)
				stack.push(i);
			for (int i = 5; i >= 1; i--)
				softly.assertThat(stack.pop()).isEqualTo(i);
		}

		@Test
		void LIFO_with_interleaved_operations(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			softly.assertThat(stack.pop()).isEqualTo(2);
			stack.push(3);
			softly.assertThat(stack.pop()).isEqualTo(3);
			softly.assertThat(stack.pop()).isEqualTo(1);
		}

		@Test
		void stress_large_number_of_elements(SoftAssertions softly) {
			int count = 10000;
			for (int i = 0; i < count; i++)
				stack.push(i);
			softly.assertThat(stack.size()).isEqualTo(count);
			for (int i = count - 1; i >= 0; i--)
				softly.assertThat(stack.pop()).isEqualTo(i);
			softly.assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void alternating_push_pop(SoftAssertions softly) {
			for (int i = 0; i < 100; i++) {
				stack.push(i);
				softly.assertThat(stack.pop()).isEqualTo(i);
			}
			softly.assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void handles_String_type(SoftAssertions softly) {
			LinkedStack<String> stringStack = new LinkedStack<>();
			stringStack.push("hello");
			stringStack.push("world");
			softly.assertThat(stringStack.pop()).isEqualTo("world");
			softly.assertThat(stringStack.pop()).isEqualTo("hello");
		}
	}
}
