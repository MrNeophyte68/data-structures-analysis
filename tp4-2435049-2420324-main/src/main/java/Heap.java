import java.util.*;

public class Heap {
    /**
     * TODO Worst Case O(1)
     *
     * @param childIndex Index associated to child of the parent that will be returned
     * @return Index of the parent of `childIndex`
     */
    private static int parent(int childIndex) {
        return (childIndex % 2 == 0) ? (childIndex - 2) / 2 : (childIndex - 1) / 2;
    }

    /**
     * TODO Worst Case O(1)
     *
     * @param parentIndex Index associated to the parent of the left child that will be returned
     * @return Index of the left child of `parentIndex`
     */
    private static int left(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * TODO Worst Case O(1)
     *
     * @param parentIndex Index associated to the parent of the right child that will be returned
     * @return Index of the right child of `parentIndex`
     */
    private static int right(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     * TODO Worst Case O(1)
     * <p>
     * Swap value at `firstIndex` and `secondIndex`
     * Value initially at `firstIndex` will now be at `secondIndex`
     * Value initially at `secondIndex` will now be at `firstIndex`
     *
     * @param heap        The heap in which to swap the elements
     * @param firstIndex  Index of the first element to be swapped
     * @param secondIndex Index of the second element to be swapped
     */
    private static <T> void swap(ArrayList<T> heap, int firstIndex, int secondIndex) {
        if(heap == null) return;
        var t = heap.get(firstIndex);
        heap.set(firstIndex, heap.get(secondIndex));
        heap.set(secondIndex, t);
    }

    /**
     * TODO Worst Case O(log n)
     *   HAS TO BE RECURSIVE
     * <p>
     * Move the value at `childIndex` towards the root (index 0) until it respects min heap
     *
     * @param heap       A valid min heap except for the element at childIndex
     * @param childIndex The element to percolate
     * @param comparator The comparator to use (if comparator.compare(a, b) < 0 then a should be above b in the heap)
     */
    private static <T> void percolateUp(ArrayList<T> heap, int childIndex, Comparator<? super T> comparator) {
        if(heap == null || childIndex == 0) return;
        int parentIndex = parent(childIndex);
        if(comparator.compare( heap.get( parentIndex ), heap.get(childIndex) ) > 0) {
            swap(heap, childIndex, parentIndex);
            percolateUp(heap, parentIndex, comparator);
        }
    }

    private static <T extends Comparable<? super T>> void percolateUp(ArrayList<T> heap, int childIndex) {
        percolateUp(heap, childIndex, Comparator.naturalOrder());
    }

    /**
     * TODO Worst Case O(log n)
     *   HAS TO BE RECURSIVE
     * <p>
     * Move the value at `parentIndex` towards the leaves prioritizing the left until it respects min heap
     *
     * @param heap        A valid min heap for indices in [0, end[ except for the element at parentIndex
     * @param parentIndex The element to percolate
     * @param end         The index of the last element in the heap
     * @param comparator  The comparator to use (if comparator.compare(a, b) < 0 then a should be above b in the heap)
     */
    private static <T> void percolateDown(ArrayList<T> heap, int parentIndex, int end, Comparator<? super T> comparator) {
        if (heap == null) return;

        int leftChild = left(parentIndex);
        int rightChild = right(parentIndex);

        if (leftChild >= end) return;

        int smallestChild = leftChild;

        if (rightChild < end && comparator.compare(heap.get(rightChild), heap.get(leftChild)) < 0) {
            smallestChild = rightChild;
        }

        if (comparator.compare(heap.get(parentIndex), heap.get(smallestChild)) > 0) {
            swap(heap, parentIndex, smallestChild);

            percolateDown(heap, smallestChild, end, comparator);
        }
    }

    private static <T extends Comparable<? super T>> void percolateDown(ArrayList<T> heap, int parentIndex) {
        percolateDown(heap, parentIndex, heap.size());
    }

    private static <T extends Comparable<? super T>> void percolateDown(ArrayList<T> heap, int parentIndex, int end) {
        percolateDown(heap, parentIndex, end, Comparator.naturalOrder());
    }

    private static <T> void percolateDown(ArrayList<T> heap, int parentIndex, Comparator<? super T> comparator) {
        percolateDown(heap, parentIndex, heap.size(), comparator);
    }

    /**
     * TODO Worst Case O(n)
     *   See here for complexity explanation :
     *   https://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity/18295327#18295327
     * <p>
     * Rearrange elements within `heap` to respect min heap
     *
     * @param list       A list of data in no particular order
     * @param comparator The comparator to use (if comparator.compare(a, b) < 0 then a should be above b in the heap)
     */
    public static <T> void heapify(ArrayList<T> list, Comparator<? super T> comparator) {
        if (list == null) return;

        for (int i = list.size() / 2 - 1; i >= 0; i--) {
            percolateDown(list, i, list.size(), comparator);
        }
    }

    public static <T extends Comparable<? super T>> void heapify(ArrayList<T> list) {
        heapify(list, Comparator.naturalOrder());
    }

    /**
     * TODO Worst Case O(log n)
     * <p>
     * Adds `element` to `heap` while making sure `heap` still respects min heap
     *
     * @param heap       A valid min heap
     * @param element    Value to add within `heap`
     * @param comparator The comparator to use (if comparator.compare(a, b) < 0 then a should be above b in the heap)
     */
    public static <T> void push(ArrayList<T> heap, T element, Comparator<? super T> comparator) {
        if(heap == null) return;
        heap.add(element);
        percolateUp(heap, heap.size() - 1, comparator);
    }

    public static <T extends Comparable<? super T>> void push(ArrayList<T> heap, T element) {
        push(heap, element, Comparator.naturalOrder());
    }

    /**
     * TODO Worst Case O(log n)
     * <p>
     * Removes the min element from `heap` while making sure `heap` still respects min heap
     *
     * @param heap       A valid min heap
     * @param comparator The comparator to use (if comparator.compare(a, b) < 0 then a should be above b in the heap)
     * @return Min element within heap
     */
    public static <T> T pop(ArrayList<T> heap, Comparator<? super T> comparator) {
        if (heap == null) return null;

        T min = heap.get(0);

        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));

        heap.remove(lastIndex);

        if (!heap.isEmpty()) {
            percolateDown(heap, 0, comparator);
        }

        return min;
    }

    public static <T extends Comparable<? super T>> T pop(ArrayList<T> heap) {
        return pop(heap, Comparator.naturalOrder());
    }

    /**
     * TODO Worst Case O(n log n)
     * <p>
     * Sorts `list` in place according to the natural ordering of `T`.
     *
     * @param list       A list of data in no particular order
     * @param comparator The comparator to use (if comparator.compare(a, b) < 0 then a should be before b in the sorted list)
     */
    public static <T> void sort(ArrayList<T> list, Comparator<? super T> comparator) {
        if(list == null) return;
        heapify(list, comparator.reversed());
        for (int end = list.size() - 1; end > 0; end--) {
            swap(list, 0, end);
            percolateDown(list, 0, end, comparator.reversed());
        }

    }

    public static <T extends Comparable<? super T>> void sort(ArrayList<T> list) {
        sort(list, Comparator.naturalOrder());
    }
}
