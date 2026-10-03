package Part2;

import Part1.BinaryNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Nested
    @DisplayName("Decryption tests")
    class DecryptionTests {
        @Test
        @DisplayName("Testing 2 levels")
        void testDecryptionLevels() {
            manager.getTree().add(new Task(100, "R", 10, "", 10));
            manager.getTree().add(new Task(50, "L2", 200 ^ 10, "", 20));
            manager.prepareData(manager.getTree().root, 0);
            assertEquals(10, manager.getTree().root.value.executionCost);
            assertEquals(200, manager.getTree().root.left.value.executionCost);
        }

        @Test
        @DisplayName("Empty tree")
        void testEmptyDecryption() {
            assertDoesNotThrow(() -> manager.prepareData(null, 0));
        }
    }

    @Nested
    @DisplayName("Sorting tests")
    class SortingTests {
        @BeforeEach
        void setupTree() {
            int[] ids = {50, 25, 75, 10, 30, 60, 90};
            for (int id : ids) manager.getTree().add(new Task(id, "T", 1, "", 0));
        }

        @Test
        @DisplayName("Ascending")
        void testAscending() {
            List<Integer> ids = manager.generateReport(true).stream()
                    .map(t -> t.priorityId).collect(Collectors.toList());
            assertEquals(List.of(10, 25, 30, 50, 60, 75, 90), ids);
        }

        @Test
        @DisplayName("Descending")
        void testDescending() {
            List<Integer> ids = manager.generateReport(false).stream()
                    .map(t -> t.priorityId).collect(Collectors.toList());
            assertEquals(List.of(90, 75, 60, 50, 30, 25, 10), ids);
        }

        @Test
        @DisplayName("Empty tree")
        void testEmptySorting() {
            manager = new TaskManager();
            assertTrue(manager.generateReport(true).isEmpty());
        }
    }

    @Nested
    @DisplayName("Total cost")
    class CostTests {
        @Test
        @DisplayName("All operators") // ((10 + 5) * (2 ^ 3)) - 20 = 100
        void testFourOperators() {
            Task rootT = new Task(100, "Sub", 0, "-", 0);
            BinaryNode<Task> root = new BinaryNode<>(rootT);
            BinaryNode<Task> leftMult = new BinaryNode<>(new Task(50, "Mult", 0, "*", 0));
            leftMult.left = new BinaryNode<>(new Task(25, "Add", 0, "+", 0));
            leftMult.left.left = new BinaryNode<>(new Task(10, "V1", 10, "", 0));
            leftMult.left.right = new BinaryNode<>(new Task(30, "V2", 5, "", 0));
            leftMult.right = new BinaryNode<>(new Task(75, "Pow", 0, "^", 0));
            leftMult.right.left = new BinaryNode<>(new Task(60, "V3", 2, "", 0));
            leftMult.right.right = new BinaryNode<>(new Task(90, "V4", 3, "", 0));
            root.left = leftMult;
            root.right = new BinaryNode<>(new Task(150, "V5", 20, "", 0));
            assertEquals(100, manager.evaluateFinalCost(root));
        }

        @Test
        @DisplayName("Nodes without 2 children")
        void testIncompleteNodes() {
            Task rootT = new Task(50, "Add", 50, "+", 0);
            BinaryNode<Task> root = new BinaryNode<>(rootT);
            root.left = new BinaryNode<>(new Task(25, "Leaf", 10, "", 0));

            assertEquals(50, manager.evaluateFinalCost(root));
        }

        @Test
        @DisplayName("Empty tree")
        void testEmptyCost() {
            assertEquals(0, manager.evaluateFinalCost(null));
        }
    }
}