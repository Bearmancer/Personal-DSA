package data_structures.stack;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class ArrayStackTest {

	ArrayStack<Integer> stack;

	@BeforeEach
	void setUp() {
		stack = new ArrayStack<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Constructor {

		@Test
		void default_creates_empty_stack(SoftAssertions softly) {
			ArrayStack<Integer> s = new ArrayStack<>();
			softly.assertThat(s.size()).isZero();
			softly.assertThat(s.isEmpty()).isTrue();
		}

		@Test
		void capacity_creates_stack_with_specified_capacity(SoftAssertions softly) {
			ArrayStack<Integer> s = new ArrayStack<>(20);
			softly.assertThat(s.size()).isZero();
		}

		@Test
		void capacity_throws_on_negative() {
			assertThatThrownBy(() -> new ArrayStack<Integer>(-1))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void capacity_throws_on_zero() {
			assertThatThrownBy(() -> new ArrayStack<Integer>(0))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Push {

		@Test
		void to_empty_stack(SoftAssertions softly) {
			stack.push(42);
			softly.assertThat(stack.size()).isEqualTo(1);
			softly.assertThat(stack.peek()).isEqualTo(42);
		}

		@Test
		void to_single_element_stack(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			softly.assertThat(stack.size()).isEqualTo(2);
			softly.assertThat(stack.peek()).isEqualTo(2);
		}

		@Test
		void maintains_LIFO_order(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			softly.assertThat(stack.pop()).isEqualTo(3);
			softly.assertThat(stack.pop()).isEqualTo(2);
			softly.assertThat(stack.pop()).isEqualTo(1);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			stack.push(null);
			softly.assertThat(stack.size()).isEqualTo(1);
			softly.assertThat(stack.peek()).isNull();
		}

		@Test
		void handles_duplicate_values(SoftAssertions softly) {
			stack.push(42);
			stack.push(42);
			stack.push(42);
			softly.assertThat(stack.size()).isEqualTo(3);
		}

		@Test
		void triggers_resize_when_full(SoftAssertions softly) {
			ArrayStack<Integer> s = new ArrayStack<>(2);
			s.push(1);
			s.push(2);
			s.push(3);
			softly.assertThat(s.size()).isEqualTo(3);
		}

		@Test
		void stress_1000_elements(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++)
				stack.push(i);
			softly.assertThat(stack.size()).isEqualTo(1000);
			softly.assertThat(stack.peek()).isEqualTo(999);
		}

		@Test
		void pushAll_collection(SoftAssertions softly) {
			stack.pushAll(Arrays.asList(1, 2, 3));
			softly.assertThat(stack.size()).isEqualTo(3);
			softly.assertThat(stack.peek()).isEqualTo(3);
		}

		@Test
		void pushAll_empty_collection_no_change(SoftAssertions softly) {
			stack.push(1);
			stack.pushAll(Collections.emptyList());
			softly.assertThat(stack.size()).isEqualTo(1);
		}

		@Test
		void pushAll_throws_on_null_collection() {
			assertThatThrownBy(() -> stack.pushAll(null))
				.isInstanceOf(NullPointerException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Pop {

		@Test
		void throws_on_empty_stack() {
			assertThatThrownBy(() -> stack.pop())
				.isInstanceOf(IllegalStateException.class);
		}

		@Test
		void removes_only_element(SoftAssertions softly) {
			stack.push(42);
			Integer popped = stack.pop();
			softly.assertThat(popped).isEqualTo(42);
			softly.assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void removes_top_element(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			Integer popped = stack.pop();
			softly.assertThat(popped).isEqualTo(2);
			softly.assertThat(stack.peek()).isEqualTo(1);
		}

		@Test
		void consecutive_pops_until_empty(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				stack.push(i);
			for (int i = 0; i < 100; i++)
				stack.pop();
			softly.assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void popN_elements(SoftAssertions softly) {
			for (int i = 0; i < 10; i++)
				stack.push(i);
			List<Integer> popped = stack.popN(5);
			softly.assertThat(popped).containsExactly(9, 8, 7, 6, 5);
			softly.assertThat(stack.size()).isEqualTo(5);
		}

		@Test
		void popN_throws_when_exceeds_size() {
			stack.push(1);
			assertThatThrownBy(() -> stack.popN(5))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void popN_zero_returns_empty_list(SoftAssertions softly) {
			stack.push(1);
			List<Integer> popped = stack.popN(0);
			softly.assertThat(popped).isEmpty();
			softly.assertThat(stack.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Peek {

		@Test
		void throws_on_empty_stack() {
			assertThatThrownBy(() -> stack.peek())
				.isInstanceOf(IllegalStateException.class);
		}

		@Test
		void returns_element_without_removing(SoftAssertions softly) {
			stack.push(42);
			softly.assertThat(stack.peek()).isEqualTo(42);
			softly.assertThat(stack.size()).isEqualTo(1);
		}

		@Test
		void returns_top_element_without_removing(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			softly.assertThat(stack.peek()).isEqualTo(3);
			softly.assertThat(stack.size()).isEqualTo(3);
		}

		@Test
		void multiple_peeks_same_result(SoftAssertions softly) {
			stack.push(42);
			softly.assertThat(stack.peek()).isEqualTo(stack.peek());
			softly.assertThat(stack.size()).isEqualTo(1);
		}

		@Test
		void peekAt_distance_from_top(SoftAssertions softly) {
			for (int i = 0; i < 10; i++)
				stack.push(i);
			softly.assertThat(stack.peekAt(0)).isEqualTo(9);
			softly.assertThat(stack.peekAt(1)).isEqualTo(8);
			softly.assertThat(stack.peekAt(2)).isEqualTo(7);
			softly.assertThat(stack.size()).isEqualTo(10);
		}

		@Test
		void peekAt_throws_when_distance_exceeds_size() {
			stack.push(1);
			assertThatThrownBy(() -> stack.peekAt(5))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Search {

		@Test
		void returns_position_from_top(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			softly.assertThat(stack.search(3)).isEqualTo(1);
			softly.assertThat(stack.search(2)).isEqualTo(2);
			softly.assertThat(stack.search(1)).isEqualTo(3);
		}

		@Test
		void finds_first_occurrence_of_duplicate(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			stack.push(1);
			softly.assertThat(stack.search(1)).isEqualTo(1);
		}

		@Test
		void finds_null_value(SoftAssertions softly) {
			stack.push(1);
			stack.push(null);
			stack.push(2);
			softly.assertThat(stack.search(null)).isEqualTo(2);
		}

		@Test
		void returns_negative_one_when_not_found() {
			stack.push(1);
			assertThat(stack.search(99)).isEqualTo(-1);
		}

		@Test
		void returns_negative_one_on_empty_stack() {
			assertThat(stack.search(1)).isEqualTo(-1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Utilities {

		@Test
		void size_returns_element_count(SoftAssertions softly) {
			softly.assertThat(stack.size()).isZero();
			stack.push(1);
			softly.assertThat(stack.size()).isEqualTo(1);
			stack.push(2);
			softly.assertThat(stack.size()).isEqualTo(2);
		}

		@Test
		void isEmpty_returns_true_when_empty(SoftAssertions softly) {
			softly.assertThat(stack.isEmpty()).isTrue();
			stack.push(1);
			softly.assertThat(stack.isEmpty()).isFalse();
		}

		@Test
		void contains_returns_true_when_present() {
			stack.push(42);
			assertThat(stack.contains(42)).isTrue();
		}

		@Test
		void contains_returns_false_when_absent() {
			stack.push(42);
			assertThat(stack.contains(99)).isFalse();
		}

		@Test
		void clear_removes_all_elements(SoftAssertions softly) {
			for (int i = 0; i < 50; i++)
				stack.push(i);
			stack.clear();
			softly.assertThat(stack.isEmpty()).isTrue();
			softly.assertThat(stack.size()).isZero();
		}

		@Test
		void clear_on_empty_stack() {
			stack.clear();
			assertThat(stack.isEmpty()).isTrue();
		}

		@Test
		void toArray_returns_elements_bottom_to_top(SoftAssertions softly) {
			stack.push(1);
			stack.push(2);
			stack.push(3);
			Object[] result = stack.toArray();
			softly.assertThat(result).containsExactly(1, 2, 3);
		}

		@Test
		void toArray_empty_returns_empty_array() {
			Object[] result = stack.toArray();
			assertThat(result).isEmpty();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void push_pop_interleaved(SoftAssertions softly) {
			for (int i = 0; i < 50; i++) {
				stack.push(i);
				stack.push(i + 100);
				stack.pop();
			}
			softly.assertThat(stack.size()).isEqualTo(50);
		}

		@Test
		void stress_mixed_operations(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				stack.push(i);
			for (int i = 0; i < 50; i++)
				stack.pop();
			for (int i = 0; i < 25; i++)
				stack.push(i + 1000);
			softly.assertThat(stack.size()).isEqualTo(75);
			softly.assertThat(stack.peek()).isEqualTo(1024);
		}

		@Test
		void rebuild_after_clear(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				stack.push(i);
			stack.clear();
			for (int i = 0; i < 50; i++)
				stack.push(i + 1000);
			softly.assertThat(stack.peek()).isEqualTo(1049);
			softly.assertThat(stack.size()).isEqualTo(50);
		}

		@Test
		void LIFO_verification(SoftAssertions softly) {
			List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
			for (Integer i : input)
				stack.push(i);
			List<Integer> output = new ArrayList<>();
			while (!stack.isEmpty())
				output.add(stack.pop());
			softly.assertThat(output).containsExactly(5, 4, 3, 2, 1);
		}

		@Test
		void reverse_collection_pattern(SoftAssertions softly) {
			int[] original = {1, 2, 3, 4, 5};
			for (int val : original)
				stack.push(val);
			int[] reversed = new int[original.length];
			for (int i = 0; i < reversed.length; i++)
				reversed[i] = stack.pop();
			softly.assertThat(reversed).containsExactly(5, 4, 3, 2, 1);
		}
	}
}
