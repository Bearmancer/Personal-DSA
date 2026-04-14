package data_structures.tree;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class BinarySearchTreeTest {

	BinarySearchTree<Integer> tree;

	@BeforeEach
	void init() {
		tree = new BinarySearchTree<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Insert {

		@Test
		void inserts_into_empty_tree(SoftAssertions softly) {
			tree.insert(10);

			softly.assertThat(tree.size()).isEqualTo(1);
			softly.assertThat(tree.contains(10)).isTrue();
		}

		@Test
		void inserts_smaller_to_left(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);

			softly.assertThat(tree.size()).isEqualTo(2);
			softly.assertThat(tree.contains(5)).isTrue();
		}

		@Test
		void inserts_larger_to_right(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(15);

			softly.assertThat(tree.size()).isEqualTo(2);
			softly.assertThat(tree.contains(15)).isTrue();
		}

		@Test
		void builds_balanced_tree(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.insert(3);
			tree.insert(7);

			softly.assertThat(tree.size()).isEqualTo(5);
			softly.assertThat(tree.height()).isEqualTo(2);
		}

		@Test
		void handles_duplicates() {
			tree.insert(10);
			tree.insert(10);

			assertThat(tree.size()).isEqualTo(1);
		}

		@Test
		void throws_on_null() {
			assertThatThrownBy(() -> tree.insert(null))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Contains {

		@Test
		void returns_false_for_empty_tree() {
			assertThat(tree.contains(10)).isFalse();
		}

		@Test
		void returns_true_for_root() {
			tree.insert(10);

			assertThat(tree.contains(10)).isTrue();
		}

		@Test
		void returns_true_for_left_child() {
			tree.insert(10);
			tree.insert(5);

			assertThat(tree.contains(5)).isTrue();
		}

		@Test
		void returns_true_for_right_child() {
			tree.insert(10);
			tree.insert(15);

			assertThat(tree.contains(15)).isTrue();
		}

		@Test
		void returns_false_for_non_existing() {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);

			assertThat(tree.contains(7)).isFalse();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Remove {

		@Test
		void removes_leaf_node(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			boolean removed = tree.remove(5);

			softly.assertThat(removed).isTrue();
			softly.assertThat(tree.contains(5)).isFalse();
			softly.assertThat(tree.size()).isEqualTo(1);
		}

		@Test
		void removes_node_with_one_child(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(3);
			boolean removed = tree.remove(5);

			softly.assertThat(removed).isTrue();
			softly.assertThat(tree.contains(3)).isTrue();
			softly.assertThat(tree.size()).isEqualTo(2);
		}

		@Test
		void removes_node_with_two_children(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.insert(3);
			tree.insert(7);
			boolean removed = tree.remove(5);

			softly.assertThat(removed).isTrue();
			softly.assertThat(tree.contains(3)).isTrue();
			softly.assertThat(tree.contains(7)).isTrue();
		}

		@Test
		void removes_root(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			boolean removed = tree.remove(10);

			softly.assertThat(removed).isTrue();
			softly.assertThat(tree.contains(5)).isTrue();
			softly.assertThat(tree.contains(15)).isTrue();
		}

		@Test
		void returns_false_for_non_existing() {
			tree.insert(10);

			assertThat(tree.remove(5)).isFalse();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class FindMin_And_FindMax {

		@Test
		void findMin_throws_on_empty() {
			assertThatThrownBy(() -> tree.findMin())
				.isInstanceOf(IllegalStateException.class);
		}

		@Test
		void findMax_throws_on_empty() {
			assertThatThrownBy(() -> tree.findMax())
				.isInstanceOf(IllegalStateException.class);
		}

		@Test
		void findMin_returns_smallest(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.insert(3);

			softly.assertThat(tree.findMin()).isEqualTo(3);
		}

		@Test
		void findMax_returns_largest(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.insert(20);

			softly.assertThat(tree.findMax()).isEqualTo(20);
		}

		@Test
		void findMin_and_findMax_same_for_single_element() {
			tree.insert(10);

			assertThat(tree.findMin()).isEqualTo(tree.findMax());
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Height {

		@Test
		void empty_tree_has_height_negative_one() {
			assertThat(tree.height()).isEqualTo(-1);
		}

		@Test
		void single_node_has_height_zero() {
			tree.insert(10);

			assertThat(tree.height()).isZero();
		}

		@Test
		void balanced_tree_height(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);

			softly.assertThat(tree.height()).isEqualTo(1);
		}

		@Test
		void unbalanced_tree_height(SoftAssertions softly) {
			tree.insert(1);
			tree.insert(2);
			tree.insert(3);
			tree.insert(4);

			softly.assertThat(tree.height()).isEqualTo(3);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Traversals {

		@Test
		void inOrder_returns_sorted_order() {
			tree.insert(5);
			tree.insert(3);
			tree.insert(7);
			tree.insert(1);
			tree.insert(4);

			assertThat(tree.inOrderTraversal()).containsExactly(1, 3, 4, 5, 7);
		}

		@Test
		void preOrder_returns_root_first() {
			tree.insert(5);
			tree.insert(3);
			tree.insert(7);

			assertThat(tree.preOrderTraversal()).containsExactly(5, 3, 7);
		}

		@Test
		void postOrder_returns_root_last() {
			tree.insert(5);
			tree.insert(3);
			tree.insert(7);

			assertThat(tree.postOrderTraversal()).containsExactly(3, 7, 5);
		}

		@Test
		void levelOrder_returns_by_level() {
			tree.insert(5);
			tree.insert(3);
			tree.insert(7);
			tree.insert(1);
			tree.insert(4);

			assertThat(tree.levelOrderTraversal()).containsExactly(5, 3, 7, 1, 4);
		}

		@Test
		void empty_tree_traversal_returns_empty() {
			assertThat(tree.inOrderTraversal()).isEmpty();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Size_And_IsEmpty {

		@Test
		void empty_tree_has_size_zero() {
			assertThat(tree.size()).isZero();
		}

		@Test
		void isEmpty_returns_true_for_empty() {
			assertThat(tree.isEmpty()).isTrue();
		}

		@Test
		void isEmpty_returns_false_after_insert() {
			tree.insert(10);

			assertThat(tree.isEmpty()).isFalse();
		}

		@Test
		void size_updates_on_insert_and_remove(SoftAssertions softly) {
			tree.insert(10);
			softly.assertThat(tree.size()).isEqualTo(1);
			tree.insert(5);
			softly.assertThat(tree.size()).isEqualTo(2);
			tree.remove(10);
			softly.assertThat(tree.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Clear {

		@Test
		void clears_all_nodes(SoftAssertions softly) {
			tree.insert(10);
			tree.insert(5);
			tree.insert(15);
			tree.clear();

			softly.assertThat(tree.isEmpty()).isTrue();
			softly.assertThat(tree.size()).isZero();
		}

		@Test
		void can_insert_after_clear(SoftAssertions softly) {
			tree.insert(10);
			tree.clear();
			tree.insert(20);

			softly.assertThat(tree.contains(20)).isTrue();
			softly.assertThat(tree.size()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void stress_test_1000_elements(SoftAssertions softly) {
			for (int i = 0; i < 1000; i++) {
				tree.insert(i);
			}

			softly.assertThat(tree.size()).isEqualTo(1000);
			softly.assertThat(tree.findMin()).isEqualTo(0);
			softly.assertThat(tree.findMax()).isEqualTo(999);
		}

		@Test
		void random_order_maintains_bst_property(SoftAssertions softly) {
			int[] values = {50, 25, 75, 10, 30, 60, 90, 5, 15};
			for (int v : values) {
				tree.insert(v);
			}

			Object[] sorted = tree.inOrderTraversal();
			for (int i = 0; i < sorted.length - 1; i++) {
				softly.assertThat((Integer) sorted[i]).isLessThan((Integer) sorted[i + 1]);
			}
		}
	}
}
