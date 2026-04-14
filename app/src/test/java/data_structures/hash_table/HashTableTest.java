package data_structures.hash_table;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class HashTableTest {

	HashTable<String, Integer> table;

	@BeforeEach
	void init() {
		table = new HashTable<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Put {

		@Test
		void puts_single_entry(SoftAssertions softly) {
			table.put("one", 1);

			softly.assertThat(table.size()).isEqualTo(1);
			softly.assertThat(table.get("one")).isEqualTo(1);
		}

		@Test
		void puts_multiple_entries(SoftAssertions softly) {
			table.put("one", 1);
			table.put("two", 2);
			table.put("three", 3);

			softly.assertThat(table.size()).isEqualTo(3);
			softly.assertThat(table.get("two")).isEqualTo(2);
		}

		@Test
		void replaces_existing_key(SoftAssertions softly) {
			table.put("key", 1);
			Integer old = table.put("key", 2);

			softly.assertThat(old).isEqualTo(1);
			softly.assertThat(table.get("key")).isEqualTo(2);
			softly.assertThat(table.size()).isEqualTo(1);
		}

		@Test
		void handles_null_key(SoftAssertions softly) {
			table.put(null, 42);

			softly.assertThat(table.get(null)).isEqualTo(42);
			softly.assertThat(table.size()).isEqualTo(1);
		}

		@Test
		void handles_null_value(SoftAssertions softly) {
			table.put("key", null);

			softly.assertThat(table.containsKey("key")).isTrue();
			softly.assertThat(table.get("key")).isNull();
		}

		@Test
		void handles_collision(SoftAssertions softly) {
			// These strings may hash to same bucket
			table.put("Aa", 1);
			table.put("BB", 2);

			softly.assertThat(table.get("Aa")).isEqualTo(1);
			softly.assertThat(table.get("BB")).isEqualTo(2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Get {

		@Test
		void returns_null_for_empty_table() {
			assertThat(table.get("key")).isNull();
		}

		@Test
		void returns_value_for_existing_key() {
			table.put("key", 42);

			assertThat(table.get("key")).isEqualTo(42);
		}

		@Test
		void returns_null_for_non_existing_key() {
			table.put("one", 1);

			assertThat(table.get("two")).isNull();
		}

		@Test
		void returns_null_value_when_stored() {
			table.put("key", null);

			assertThat(table.get("key")).isNull();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Remove {

		@Test
		void removes_existing_key(SoftAssertions softly) {
			table.put("key", 42);
			Integer removed = table.remove("key");

			softly.assertThat(removed).isEqualTo(42);
			softly.assertThat(table.containsKey("key")).isFalse();
			softly.assertThat(table.size()).isZero();
		}

		@Test
		void returns_null_for_non_existing_key() {
			assertThat(table.remove("key")).isNull();
		}

		@Test
		void removes_from_collision_chain(SoftAssertions softly) {
			table.put("Aa", 1);
			table.put("BB", 2);
			table.remove("Aa");

			softly.assertThat(table.containsKey("Aa")).isFalse();
			softly.assertThat(table.get("BB")).isEqualTo(2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class ContainsKey {

		@Test
		void returns_false_for_empty_table() {
			assertThat(table.containsKey("key")).isFalse();
		}

		@Test
		void returns_true_for_existing_key() {
			table.put("key", 42);

			assertThat(table.containsKey("key")).isTrue();
		}

		@Test
		void returns_false_after_removal() {
			table.put("key", 42);
			table.remove("key");

			assertThat(table.containsKey("key")).isFalse();
		}

		@Test
		void handles_null_key() {
			table.put(null, 42);

			assertThat(table.containsKey(null)).isTrue();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class ContainsValue {

		@Test
		void returns_false_for_empty_table() {
			assertThat(table.containsValue(42)).isFalse();
		}

		@Test
		void returns_true_for_existing_value() {
			table.put("key", 42);

			assertThat(table.containsValue(42)).isTrue();
		}

		@Test
		void returns_false_for_non_existing_value() {
			table.put("key", 42);

			assertThat(table.containsValue(99)).isFalse();
		}

		@Test
		void handles_null_value() {
			table.put("key", null);

			assertThat(table.containsValue(null)).isTrue();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Size_And_IsEmpty {

		@Test
		void empty_table_has_size_zero() {
			assertThat(table.size()).isZero();
		}

		@Test
		void isEmpty_returns_true_for_empty_table() {
			assertThat(table.isEmpty()).isTrue();
		}

		@Test
		void isEmpty_returns_false_after_put() {
			table.put("key", 1);

			assertThat(table.isEmpty()).isFalse();
		}

		@Test
		void size_updates_correctly(SoftAssertions softly) {
			table.put("a", 1);
			softly.assertThat(table.size()).isEqualTo(1);
			table.put("b", 2);
			softly.assertThat(table.size()).isEqualTo(2);
			table.remove("a");
			softly.assertThat(table.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Clear {

		@Test
		void clears_all_entries(SoftAssertions softly) {
			table.put("a", 1);
			table.put("b", 2);
			table.clear();

			softly.assertThat(table.isEmpty()).isTrue();
			softly.assertThat(table.size()).isZero();
			softly.assertThat(table.containsKey("a")).isFalse();
		}

		@Test
		void can_add_after_clear(SoftAssertions softly) {
			table.put("a", 1);
			table.clear();
			table.put("b", 2);

			softly.assertThat(table.get("b")).isEqualTo(2);
			softly.assertThat(table.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Resize {

		@Test
		void triggers_resize_on_load_factor_exceeded(SoftAssertions softly) {
			HashTable<Integer, Integer> smallTable = new HashTable<>(4);
			for (int i = 0; i < 10; i++) {
				smallTable.put(i, i * 10);
			}

			softly.assertThat(smallTable.size()).isEqualTo(10);
			for (int i = 0; i < 10; i++) {
				softly.assertThat(smallTable.get(i)).isEqualTo(i * 10);
			}
		}

		@Test
		void maintains_entries_after_resize(SoftAssertions softly) {
			for (int i = 0; i < 100; i++) {
				table.put("key" + i, i);
			}

			softly.assertThat(table.size()).isEqualTo(100);
			for (int i = 0; i < 100; i++) {
				softly.assertThat(table.get("key" + i)).isEqualTo(i);
			}
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void stress_test_1000_entries(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++) {
				table.put("key" + i, i);
			}

			softly.assertThat(table.size()).isEqualTo(1000);

			for (int i = 0; i < 1000; i++) {
				softly.assertThat(table.get("key" + i)).isEqualTo(i);
			}
		}

		@Test
		void mixed_operations(SoftAssertions softly) {
			table.put("a", 1);
			table.put("b", 2);
			table.put("c", 3);
			table.remove("b");
			table.put("d", 4);
			table.put("a", 10);

			softly.assertThat(table.size()).isEqualTo(3);
			softly.assertThat(table.get("a")).isEqualTo(10);
			softly.assertThat(table.containsKey("b")).isFalse();
			softly.assertThat(table.get("d")).isEqualTo(4);
		}
	}
}
