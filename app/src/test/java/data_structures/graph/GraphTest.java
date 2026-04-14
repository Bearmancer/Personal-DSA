package data_structures.graph;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings({"ConstantConditions", "unused"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(SoftAssertionsExtension.class)
class GraphTest {

	Graph<Integer> graph;

	@BeforeEach
	void init() {
		graph = new Graph<>();
	}

	@Nested
	@Disabled("Awaiting implementation")
	class AddVertex {

		@Test
		void adds_single_vertex(SoftAssertions softly) {
			boolean added = graph.addVertex(1);

			softly.assertThat(added).isTrue();
			softly.assertThat(graph.hasVertex(1)).isTrue();
			softly.assertThat(graph.vertexCount()).isEqualTo(1);
		}

		@Test
		void adds_multiple_vertices(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addVertex(3);

			softly.assertThat(graph.vertexCount()).isEqualTo(3);
		}

		@Test
		void returns_false_for_duplicate(SoftAssertions softly) {
			graph.addVertex(1);
			boolean added = graph.addVertex(1);

			softly.assertThat(added).isFalse();
			softly.assertThat(graph.vertexCount()).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class AddEdge {

		@Test
		void adds_edge_between_vertices(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			boolean added = graph.addEdge(1, 2);

			softly.assertThat(added).isTrue();
			softly.assertThat(graph.hasEdge(1, 2)).isTrue();
		}

		@Test
		void undirected_edge_is_bidirectional(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addEdge(1, 2);

			softly.assertThat(graph.hasEdge(1, 2)).isTrue();
			softly.assertThat(graph.hasEdge(2, 1)).isTrue();
		}

		@Test
		void directed_edge_is_unidirectional(SoftAssertions softly) {
			Graph<Integer> directed = new Graph<>(true);
			directed.addVertex(1);
			directed.addVertex(2);
			directed.addEdge(1, 2);

			softly.assertThat(directed.hasEdge(1, 2)).isTrue();
			softly.assertThat(directed.hasEdge(2, 1)).isFalse();
		}

		@Test
		void throws_for_non_existing_vertex() {
			graph.addVertex(1);

			assertThatThrownBy(() -> graph.addEdge(1, 2))
				.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void updates_edge_count(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addVertex(3);
			graph.addEdge(1, 2);
			graph.addEdge(2, 3);

			softly.assertThat(graph.edgeCount()).isEqualTo(2);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class RemoveVertex {

		@Test
		void removes_vertex(SoftAssertions softly) {
			graph.addVertex(1);
			boolean removed = graph.removeVertex(1);

			softly.assertThat(removed).isTrue();
			softly.assertThat(graph.hasVertex(1)).isFalse();
		}

		@Test
		void removes_associated_edges(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addVertex(3);
			graph.addEdge(1, 2);
			graph.addEdge(1, 3);
			graph.removeVertex(1);

			softly.assertThat(graph.hasEdge(2, 1)).isFalse();
			softly.assertThat(graph.edgeCount()).isZero();
		}

		@Test
		void returns_false_for_non_existing() {
			assertThat(graph.removeVertex(99)).isFalse();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class RemoveEdge {

		@Test
		void removes_edge(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addEdge(1, 2);
			boolean removed = graph.removeEdge(1, 2);

			softly.assertThat(removed).isTrue();
			softly.assertThat(graph.hasEdge(1, 2)).isFalse();
		}

		@Test
		void undirected_removes_both_directions(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addEdge(1, 2);
			graph.removeEdge(1, 2);

			softly.assertThat(graph.hasEdge(1, 2)).isFalse();
			softly.assertThat(graph.hasEdge(2, 1)).isFalse();
		}

		@Test
		void returns_false_for_non_existing() {
			graph.addVertex(1);
			graph.addVertex(2);

			assertThat(graph.removeEdge(1, 2)).isFalse();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class GetNeighbors {

		@Test
		void returns_empty_for_isolated_vertex() {
			graph.addVertex(1);

			assertThat(graph.getNeighbors(1)).isEmpty();
		}

		@Test
		void returns_all_neighbors(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addVertex(3);
			graph.addEdge(1, 2);
			graph.addEdge(1, 3);

			softly.assertThat(graph.getNeighbors(1)).containsExactlyInAnyOrder(2, 3);
		}

		@Test
		void throws_for_non_existing_vertex() {
			assertThatThrownBy(() -> graph.getNeighbors(99))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Degree {

		@Test
		void returns_zero_for_isolated_vertex() {
			graph.addVertex(1);

			assertThat(graph.degree(1)).isZero();
		}

		@Test
		void returns_correct_degree(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addVertex(3);
			graph.addEdge(1, 2);
			graph.addEdge(1, 3);

			softly.assertThat(graph.degree(1)).isEqualTo(2);
			softly.assertThat(graph.degree(2)).isEqualTo(1);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class BFS {

		@Test
		void traverses_single_vertex() {
			graph.addVertex(1);

			assertThat(graph.bfs(1)).containsExactly(1);
		}

		@Test
		void traverses_in_level_order(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addVertex(3);
			graph.addVertex(4);
			graph.addEdge(1, 2);
			graph.addEdge(1, 3);
			graph.addEdge(2, 4);

			Object[] result = graph.bfs(1);
			softly.assertThat(result[0]).isEqualTo(1);
			softly.assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4);
		}

		@Test
		void throws_for_non_existing_start() {
			assertThatThrownBy(() -> graph.bfs(99))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class DFS {

		@Test
		void traverses_single_vertex() {
			graph.addVertex(1);

			assertThat(graph.dfs(1)).containsExactly(1);
		}

		@Test
		void traverses_depth_first(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addVertex(3);
			graph.addVertex(4);
			graph.addEdge(1, 2);
			graph.addEdge(1, 3);
			graph.addEdge(2, 4);

			Object[] result = graph.dfs(1);
			softly.assertThat(result[0]).isEqualTo(1);
			softly.assertThat(result).containsExactlyInAnyOrder(1, 2, 3, 4);
		}

		@Test
		void throws_for_non_existing_start() {
			assertThatThrownBy(() -> graph.dfs(99))
				.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Clear {

		@Test
		void clears_all_vertices_and_edges(SoftAssertions softly) {
			graph.addVertex(1);
			graph.addVertex(2);
			graph.addEdge(1, 2);
			graph.clear();

			softly.assertThat(graph.vertexCount()).isZero();
			softly.assertThat(graph.edgeCount()).isZero();
		}
	}

	@Nested
	@Disabled("Awaiting implementation")
	class Integration {

		@Test
		void builds_complete_graph(SoftAssertions softly) {
			for (int i = 1; i <= 5; i++) {
				graph.addVertex(i);
			}
			for (int i = 1; i <= 5; i++) {
				for (int j = i + 1; j <= 5; j++) {
					graph.addEdge(i, j);
				}
			}

			softly.assertThat(graph.vertexCount()).isEqualTo(5);
			softly.assertThat(graph.edgeCount()).isEqualTo(10);
		}

		@Test
		void builds_linear_graph(SoftAssertions softly) {
			for (int i = 1; i <= 5; i++) {
				graph.addVertex(i);
			}
			for (int i = 1; i < 5; i++) {
				graph.addEdge(i, i + 1);
			}

			Object[] bfs = graph.bfs(1);
			softly.assertThat(bfs).containsExactly(1, 2, 3, 4, 5);
		}
	}
}
