package data_structures.dynamic_array;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class DynamicArrayTest {

	DynamicArray<Integer> array;

	@BeforeEach
	void setUp() {
		array = new DynamicArray<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Constructor {

		@Test
		void default_creates_empty_array(SoftAssertions softly) {
			DynamicArray<Integer> arr = new DynamicArray<>();

			softly.assertThat(arr.size()).isZero();
			softly.assertThat(arr.isEmpty()).isTrue();
		}

		@Test
		void default_has_default_capacity() {
			DynamicArray<Integer> arr = new DynamicArray<>();

			assertThat(arr.capacity()).isEqualTo(10);
		}

		@Test
		void capacity_creates_array_with_specified_capacity(SoftAssertions softly) {
			DynamicArray<Integer> arr = new DynamicArray<>(20);

			softly.assertThat(arr.capacity()).isEqualTo(20);
			softly.assertThat(arr.size()).isZero();
		}

		@Test
		void capacity_throws_on_negative() {
			assertThatThrownBy(() -> new DynamicArray<Integer>(-1))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void capacity_throws_on_zero() {
			assertThatThrownBy(() -> new DynamicArray<Integer>(0))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void array_creates_from_existing_array(SoftAssertions softly) {
			Integer[] source = {1, 2, 3, 4, 5};
			DynamicArray<Integer> arr = new DynamicArray<>(source);

			softly.assertThat(arr.size()).isEqualTo(5);
			softly.assertThat(arr.get(0)).isEqualTo(1);
			softly.assertThat(arr.get(4)).isEqualTo(5);
		}

		@Test
		void array_throws_on_null() {
			assertThatThrownBy(() -> new DynamicArray<Integer>((Integer[]) null))
				.isInstanceOf(NullPointerException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Add {

		@Test
		void to_empty_array(SoftAssertions softly) {
			array.add(42);

			softly.assertThat(array.size()).isEqualTo(1);
			softly.assertThat(array.get(0)).isEqualTo(42);
		}

		@Test
		void addFirst_to_empty_array(SoftAssertions softly) {
			array.addFirst(42);

			softly.assertThat(array.size()).isEqualTo(1);
			softly.assertThat(array.getFirst()).isEqualTo(42);
		}

		@Test
		void addAt_index_0_empty_array(SoftAssertions softly) {
			array.addAt(0, 42);

			softly.assertThat(array.size()).isEqualTo(1);
			softly.assertThat(array.get(0)).isEqualTo(42);
		}

		@Test
		void add_to_single_element_array(SoftAssertions softly) {
			array.add(1);
			array.add(2);

			softly.assertThat(array.size()).isEqualTo(2);
			softly.assertThat(array.getLast()).isEqualTo(2);
		}

		@Test
		void addFirst_to_single_element_array(SoftAssertions softly) {
			array.add(1);
			array.addFirst(0);

			softly.assertThat(array.size()).isEqualTo(2);
			softly.assertThat(array.getFirst()).isEqualTo(0);
		}

		@Test
		void add_maintains_order(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.add(3);

			softly.assertThat(array.get(0)).isEqualTo(1);
			softly.assertThat(array.get(1)).isEqualTo(2);
			softly.assertThat(array.get(2)).isEqualTo(3);
		}

		@Test
		void addAt_shifts_elements_right(SoftAssertions softly) {
			array.add(1);
			array.add(3);
			array.addAt(1, 2);

			softly.assertThat(array.get(0)).isEqualTo(1);
			softly.assertThat(array.get(1)).isEqualTo(2);
			softly.assertThat(array.get(2)).isEqualTo(3);
		}

		@Test
		void triggers_resize_when_full(SoftAssertions softly) {
			DynamicArray<Integer> arr = new DynamicArray<>(2);
			arr.add(1);
			arr.add(2);
			arr.add(3);

			softly.assertThat(arr.size()).isEqualTo(3);
			softly.assertThat(arr.capacity()).isGreaterThanOrEqualTo(3);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			array.add(null);

			softly.assertThat(array.size()).isEqualTo(1);
			softly.assertThat(array.get(0)).isNull();
		}

		@Test
		void handles_duplicate_values(SoftAssertions softly) {
			array.add(42);
			array.add(42);
			array.add(42);

			softly.assertThat(array.size()).isEqualTo(3);
		}

		@Test
		void addAt_throws_on_negative_index() {
			assertThatThrownBy(() -> array.addAt(-1, 42))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void addAt_throws_on_index_greater_than_size() {
			assertThatThrownBy(() -> array.addAt(1, 42))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void stress_1000_elements(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++)
				array.add(i);

			softly.assertThat(array.size()).isEqualTo(1000);
			softly.assertThat(array.getFirst()).isEqualTo(0);
			softly.assertThat(array.getLast()).isEqualTo(999);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Remove {

		@Test
		void removeFirst_throws_on_empty() {
			assertThatThrownBy(() -> array.removeFirst())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void removeLast_throws_on_empty() {
			assertThatThrownBy(() -> array.removeLast())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void removeAt_throws_on_empty() {
			assertThatThrownBy(() -> array.removeAt(0))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void removeFirst_single_element(SoftAssertions softly) {
			array.add(42);
			Integer removed = array.removeFirst();

			softly.assertThat(removed).isEqualTo(42);
			softly.assertThat(array.isEmpty()).isTrue();
		}

		@Test
		void removeLast_single_element(SoftAssertions softly) {
			array.add(42);
			Integer removed = array.removeLast();

			softly.assertThat(removed).isEqualTo(42);
			softly.assertThat(array.isEmpty()).isTrue();
		}

		@Test
		void removeFirst_leaves_remaining(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			Integer removed = array.removeFirst();

			softly.assertThat(removed).isEqualTo(1);
			softly.assertThat(array.getFirst()).isEqualTo(2);
		}

		@Test
		void removeAt_middle_shifts_left(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.add(3);
			Integer removed = array.removeAt(1);

			softly.assertThat(removed).isEqualTo(2);
			softly.assertThat(array.get(1)).isEqualTo(3);
		}

		@Test
		void removeValue_returns_true_when_found(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.add(3);
			boolean removed = array.removeValue(2);

			softly.assertThat(removed).isTrue();
			softly.assertThat(array.size()).isEqualTo(2);
		}

		@Test
		void removeValue_returns_false_when_not_found() {
			array.add(1);
			boolean removed = array.removeValue(99);

			assertThat(removed).isFalse();
		}

		@Test
		void clear_removes_all_elements(SoftAssertions softly) {
			for (int i = 0; i < 50; i++)
				array.add(i);
			array.clear();

			softly.assertThat(array.isEmpty()).isTrue();
			softly.assertThat(array.size()).isZero();
		}

		@Test
		void removeAt_throws_on_negative_index() {
			array.add(1);
			assertThatThrownBy(() -> array.removeAt(-1))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Get {

		@Test
		void get_throws_on_empty() {
			assertThatThrownBy(() -> array.get(0))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void getFirst_throws_on_empty() {
			assertThatThrownBy(() -> array.getFirst())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void getLast_throws_on_empty() {
			assertThatThrownBy(() -> array.getLast())
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void get_returns_element(SoftAssertions softly) {
			array.add(42);

			softly.assertThat(array.get(0)).isEqualTo(42);
		}

		@Test
		void getFirst_same_as_getLast_single_element(SoftAssertions softly) {
			array.add(42);

			softly.assertThat(array.getFirst()).isEqualTo(array.getLast());
		}

		@Test
		void get_at_various_indices(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				array.add(i);

			softly.assertThat(array.get(0)).isEqualTo(0);
			softly.assertThat(array.get(50)).isEqualTo(50);
			softly.assertThat(array.get(99)).isEqualTo(99);
		}

		@Test
		void throws_on_negative_index() {
			array.add(1);
			assertThatThrownBy(() -> array.get(-1))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void throws_on_index_equal_to_size() {
			array.add(1);
			assertThatThrownBy(() -> array.get(1))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Set {

		@Test
		void set_at_valid_index(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.set(1, 99);

			softly.assertThat(array.get(1)).isEqualTo(99);
		}

		@Test
		void set_does_not_change_size(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			int sizeBefore = array.size();
			array.set(1, 99);

			softly.assertThat(array.size()).isEqualTo(sizeBefore);
		}

		@Test
		void set_with_null_value(SoftAssertions softly) {
			array.add(1);
			array.set(0, null);

			softly.assertThat(array.get(0)).isNull();
		}

		@Test
		void setFirst_replaces_first_element(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.setFirst(99);

			softly.assertThat(array.getFirst()).isEqualTo(99);
		}

		@Test
		void setLast_replaces_last_element(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.setLast(99);

			softly.assertThat(array.getLast()).isEqualTo(99);
		}

		@Test
		void throws_on_empty_array() {
			assertThatThrownBy(() -> array.set(0, 42))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}

		@Test
		void throws_on_negative_index() {
			array.add(1);
			assertThatThrownBy(() -> array.set(-1, 42))
				.isInstanceOf(IndexOutOfBoundsException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Search {

		@Test
		void indexOf_returns_first_occurrence(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.add(1);

			softly.assertThat(array.indexOf(1)).isEqualTo(0);
		}

		@Test
		void indexOf_returns_negative_one_when_not_found() {
			array.add(1);

			assertThat(array.indexOf(99)).isEqualTo(-1);
		}

		@Test
		void indexOf_finds_null_value(SoftAssertions softly) {
			array.add(1);
			array.add(null);
			array.add(2);

			softly.assertThat(array.indexOf(null)).isEqualTo(1);
		}

		@Test
		void lastIndexOf_returns_last_occurrence(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.add(1);

			softly.assertThat(array.lastIndexOf(1)).isEqualTo(2);
		}

		@Test
		void lastIndexOf_returns_negative_one_when_not_found() {
			array.add(1);

			assertThat(array.lastIndexOf(99)).isEqualTo(-1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Utilities {

		@Test
		void size_returns_element_count(SoftAssertions softly) {
			softly.assertThat(array.size()).isZero();
			array.add(1);
			softly.assertThat(array.size()).isEqualTo(1);
			array.add(2);
			softly.assertThat(array.size()).isEqualTo(2);
		}

		@Test
		void isEmpty_returns_true_when_empty(SoftAssertions softly) {
			softly.assertThat(array.isEmpty()).isTrue();
			array.add(1);
			softly.assertThat(array.isEmpty()).isFalse();
		}

		@Test
		void contains_returns_true_when_present() {
			array.add(42);

			assertThat(array.contains(42)).isTrue();
		}

		@Test
		void contains_returns_false_when_absent() {
			array.add(42);

			assertThat(array.contains(99)).isFalse();
		}

		@Test
		void contains_handles_null() {
			array.add(null);

			assertThat(array.contains(null)).isTrue();
		}

		@Test
		void toArray_returns_copy(SoftAssertions softly) {
			array.add(1);
			array.add(2);
			array.add(3);
			Object[] result = array.toArray();

			softly.assertThat(result).containsExactly(1, 2, 3);
		}

		@Test
		void toArray_empty_returns_empty_array() {
			Object[] result = array.toArray();

			assertThat(result).isEmpty();
		}

		@Test
		void ensureCapacity_increases_capacity(SoftAssertions softly) {
			array.ensureCapacity(100);

			softly.assertThat(array.capacity()).isGreaterThanOrEqualTo(100);
		}

		@Test
		void trimToSize_reduces_capacity_to_size(SoftAssertions softly) {
			for (int i = 0; i < 5; i++)
				array.add(i);
			array.trimToSize();

			softly.assertThat(array.capacity()).isEqualTo(5);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void add_remove_interleaved(SoftAssertions softly) {
			for (int i = 0; i < 50; i++) {
				array.add(i);
				array.add(i + 100);
				array.removeFirst();
			}

			softly.assertThat(array.size()).isEqualTo(50);
		}

		@Test
		void stress_mixed_operations(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				array.add(i);
			for (int i = 0; i < 25; i++)
				array.removeFirst();
			for (int i = 0; i < 25; i++)
				array.removeLast();

			softly.assertThat(array.size()).isEqualTo(50);
		}

		@Test
		void rebuild_after_clear(SoftAssertions softly) {
			for (int i = 0; i < 100; i++)
				array.add(i);
			array.clear();
			for (int i = 0; i < 50; i++)
				array.add(i + 1000);

			softly.assertThat(array.getFirst()).isEqualTo(1000);
			softly.assertThat(array.getLast()).isEqualTo(1049);
		}

		@Test
		void random_access_after_modifications(SoftAssertions softly) {
			for (int i = 0; i < 20; i++)
				array.add(i);
			array.removeAt(10);
			array.addAt(5, 999);

			softly.assertThat(array.indexOf(999)).isEqualTo(5);
			softly.assertThat(array.size()).isEqualTo(20);
		}
	}
}
