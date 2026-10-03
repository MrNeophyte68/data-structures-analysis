package Part2;
import Part1.AvlTree;
import Part1.BinaryNode;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final AvlTree<Task> tree = new AvlTree<>();

    /**
     * Prepares and unlocks task data by propagating decryption keys down the tree.
     * Each node is decrypted using its parent's key before the process continues
     * to its descendants.
     *
     * @param node The current node to decrypt
     * @param parentKey The decryption key provided by the parent node
     */
    public void prepareData(BinaryNode<Task> node, int parentKey) {
        //TODO
        if (node == null) return;
        Task task = node.value;
        task.executionCost = task.executionCost ^ parentKey;
        task.isDecrypted = true;
        int currentKey = task.decryptionKey;

        prepareData(node.left, currentKey);
        prepareData(node.right, currentKey);
    }

    /**
     * Generates a comprehensive list of tasks sorted by their priority levels.
     *
     * @param ascending If true, returns tasks from lowest to highest priority.
     * If false, returns tasks from highest to lowest priority.
     * @return A list containing all tasks in the requested order
     */
    public List<Task> generateReport(boolean ascending) {
        //TODO
        List<Task> list = new ArrayList<>();
        BinaryNode<Task> root = tree.root;

        if (ascending)
            buildAscending(root, list);
        else
            buildDescending(root, list);

        return list;
    }

    /**
     * Explores the tree to collect tasks in an increasing order of priority.
     *
     * @param node The starting node for the exploration
     * @param list The list where the collected tasks are stored
     */
    private void buildAscending(BinaryNode<Task> node, List<Task> list) {
        //TODO
        if (node == null) return;

        buildAscending(node.left, list);
        list.add(node.value);
        buildAscending(node.right, list);
    }

    /**
     * Explores the tree to collect tasks in a decreasing order of priority.
     *
     * @param node The starting node for the exploration
     * @param list The list where the collected tasks are stored
     */
    private void buildDescending(BinaryNode<Task> node, List<Task> list) {
        //TODO
        if (node == null) return;

        buildDescending(node.right, list);
        list.add(node.value);
        buildDescending(node.left, list);
    }

    /**
     * Evaluates the final execution cost of a task hierarchy.
     * For internal nodes with two children, the cost is derived by combining child
     * costs using the parent's operator. Otherwise, the node's intrinsic cost is used.
     *
     * @param node The root of the hierarchy to evaluate
     * @return The final calculated cost as an integer
     */
    public int evaluateFinalCost(BinaryNode<Task> node) {
        //TODO
        if (node == null) return 0;

        Task task = node.value;

        if (node.left != null && node.right != null) {
            int leftCost = evaluateFinalCost(node.left);
            int rightCost = evaluateFinalCost(node.right);
            return compute(leftCost, rightCost, task.operator);
        }

        return task.executionCost;
    }

    /**
     * Performs a mathematical operation on two input values.
     *
     * @param a The value from the left sub-hierarchy
     * @param b The value from the right sub-hierarchy
     * @param op The mathematical operator to apply (+, -, *, ^)
     * @return The result of the operation
     */
    private int compute(int a, int b, String op) {
        //TODO
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "^":
                int result = 1;
                for (int i = 0; i < b; i++) result *= a;
                return result;
            default:
                throw new IllegalArgumentException("Opérateur invalide: " + op);
        }
    }

    /**
     * Retrieves the underlying AVL tree storage.
     *
     * @return The AVL tree instance containing the tasks
     */
    public AvlTree<Task> getTree() { return tree; }
}