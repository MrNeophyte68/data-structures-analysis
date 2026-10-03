package Part1;

public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    /**
     * Adds a new value to the AVL tree and balances it if necessary
     * @param value the value to add
     */
    @Override
    public void add(T value) {
        this.root = add(value, this.root);
    }

    protected BinaryNode<T> add(T value, BinaryNode<T> curNode) {
        // TODO
        if (curNode == null){
            return new BinaryNode<T>(value);
        }

        int compareValue = value.compareTo(curNode.value);

        if (compareValue < 0)
        {
            curNode.left = add(value, curNode.left);
        }
        else if (compareValue > 0)
        {
            curNode.right = add(value, curNode.right);
        }

        else
        {
            throw new RuntimeException("Value is duplicated");
        }

        return balance(curNode);
    }

    /**
     * Removes a value from the AVL tree and balances it if necessary
     * @param value the value to remove
     */
    @Override
    public void remove(T value) {
        this.root = remove(value, this.root);
    }

    protected BinaryNode<T> remove(T value, BinaryNode<T> curNode) {
        // TODO
        if (curNode == null) { return null; }

        int compareValue = value.compareTo(curNode.value);

        if (compareValue < 0)
        {
            curNode.left = remove(value, curNode.left);
        }
        else if (compareValue > 0)
        {
            curNode.right = remove(value, curNode.right);
        }
        else
        {
            if (curNode.left != null && curNode.right != null)
            {
                curNode.value = findMin(curNode.right).value;
                curNode.right = remove(curNode.value, curNode.right);
            }
            else if (curNode.left == null)
            {
                curNode = curNode.right;
            }
            else
            {
                curNode = curNode.left;
            }
        }

        return balance(curNode);
    }

    /**
     * Balances a node in the AVL tree if its balance factor is not in the range [-1, 1]
     * @param curNode the node to balance
     * @return curNode which might be updated depending on balancing operations
     */
    protected BinaryNode<T> balance(BinaryNode<T> curNode) {
        // TODO
        if (curNode == null) { return null; }

        if (getHeight(curNode.left) - getHeight(curNode.right) > 1)
        {
            if (getHeight(curNode.left.left) >= getHeight(curNode.left.right))
            {
                curNode = rotateRight(curNode);
            }
            else
            {
                curNode.left = rotateLeft(curNode.left);
                curNode = rotateRight(curNode);
            }
        }

        else if (getHeight(curNode.right) - getHeight(curNode.left) > 1)
        {
            if (getHeight(curNode.right.right) >= getHeight(curNode.right.left))
            {
                curNode = rotateLeft(curNode);
            }
            else
            {
                curNode.right = rotateRight(curNode.right);
                curNode = rotateLeft(curNode);
            }
        }

        updateHeight(curNode);
        return curNode;
    }


    /**
     * Performs a right rotation on a node in the AVL tree to balance it
     * @param curNode the node to rotate
     * @return the node that replaces the rotated node in the tree
     */
    protected BinaryNode<T> rotateRight(BinaryNode<T> curNode) {
        // TODO
        BinaryNode<T> otherNode = curNode.left;
        curNode.left = otherNode.right;
        otherNode.right = curNode;
        updateHeight(curNode);
        updateHeight(otherNode);
        return otherNode;
    }

    /**
     * Performs a left rotation on a node in the AVL tree to balance it
     * @param curNode the node to rotate
     * @return the node that replaces the rotated node in the tree
     */
    protected BinaryNode<T> rotateLeft(BinaryNode<T> curNode){
        // TODO
        BinaryNode<T> otherNode = curNode.right;
        curNode.right = otherNode.left;
        otherNode.left = curNode;
        updateHeight(curNode);
        updateHeight(otherNode);
        return otherNode;
    }

    /**
     * Calculates the balance factor of a node in the AVL tree
     * @param node the node to calculate the balance factor for
     * @return the balance factor of the node
     */
    private int balanceFactor(BinaryNode<T> node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    /**
     * Calculates the height of a node in the AVL tree
     * @param node the node to calculate the height for
     * @return the height of the node
     */
    private int getHeight(BinaryNode<T> node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Updates the height of a node in the AVL tree
     * @param node the node to update the height for
     */
    private void updateHeight(BinaryNode<T> node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}
