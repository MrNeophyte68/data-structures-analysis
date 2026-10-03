package Part1;

import java.util.LinkedList;

public class HashMap<KeyType, DataType> {

    // DEFAULT_CAPACITY has to be a prime number
    private static final int DEFAULT_CAPACITY = 23;
    private static final float DEFAULT_LOAD_FACTOR = 0.5f;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private LinkedList<Node<KeyType, DataType>>[] map;
    private int size = 0;
    private final float loadFactor; // Compression factor

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * TODO
     * Define capacity, loadFactor and map
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            initialCapacity = DEFAULT_CAPACITY;
        }

        if (loadFactor <= 0 || loadFactor > 1) {
            loadFactor = DEFAULT_LOAD_FACTOR;
        }

        if (!isPrime(initialCapacity))
            {
                initialCapacity = nextPrime(initialCapacity);
            }

        this.loadFactor = loadFactor;
        map = new LinkedList[initialCapacity];
    }

    /**
     * Finds the index attached to a particular key
     * This is the hashing function ("Fonction de dispersement")
     *
     * @param key Value used to access to a particular instance of a DataType
     * @return Index value where this key should be placed in `map`
     */
    private int hash(KeyType key) {
        int keyHash = key.hashCode() % capacity();
        return Math.abs(keyHash);
    }

    /**
     * @return if it should be rehashed
     */
    private boolean needRehash() {
        return size > capacity() * loadFactor;
    }

    /**
     * @return Number of elements
     */
    public int size() {
        return size;
    }

    /**
     * @return Current reserved space
     */
    public int capacity() {
        return map.length;
    }

    /**
     * @return if it is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * TODO
     * Finds the next prime
     *
     * @param number number to start the search to the next prime
     * @return Next closest prime
     */
    private int nextPrime(int number) {
        number++;
        while (true)
        {
            if (isPrime(number))
            {
                return number;
            }
            number++;
        }
    }

    /**
     * TODO Worst Case : O(m + n)
     * m = Capacity of the hashmap
     * n = number of elements in the hashmap
     * Increases capacity to the next prime number after capacity * CAPACITY_INCREASE_FACTOR and
     * reassigns all contained values
     */
    private void rehash() {
        int newCapacity = nextPrime(capacity() * CAPACITY_INCREASE_FACTOR);
        LinkedList<Node<KeyType, DataType>>[] temp = map;
        map = new LinkedList[newCapacity];
        size = 0;
        for (LinkedList<Node<KeyType, DataType>> list : temp)
        {
            if (list != null)
            {
                for (Node<KeyType, DataType> node : list)
                {
                    put(node.key, node.data);
                }
            }
        }
    }

    /**
     * TODO Average Case : O(1)
     * Finds if the key is already assigned
     *
     * @param key Key which we want to know if exists already
     * @return if key is already used
     */
    public boolean containsKey(KeyType key) {
        int index = hash(key);
        if (map[index] == null)
        {
            return false;
        }

        for (Node<KeyType, DataType> node : map[index])
        {
            if (node.key.equals(key))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * TODO Worst Case : O(m + n)
     * m = Capacity of the hashmap
     * n = number of elements in the hashmap
     * Finds if the value is already present
     *
     * @param value Value which we want to know if exists already
     * @return if value is already present
     */
    public boolean containsValue(DataType value) {
        for (LinkedList<Node<KeyType, DataType>> list : map)
        {
            if (list != null) {
                for (Node<KeyType, DataType> node : list) {
                    if (node.data.equals(value)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * TODO Average Case : O(1)
     * Finds the value attached to a key
     *
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {
        int index = hash(key);
        if (map[index] != null) {
            for (Node<KeyType, DataType> node : map[index]) {
                if (node.key.equals(key)) {
                    return node.data;
                }
            }
        }
        return null;
    }

    /**
     * TODO Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key
     *
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {
        int index = hash(key);
        if (map[index] != null) {
            for (Node<KeyType, DataType> node : map[index]) {
                if (node.key.equals(key)) {
                    DataType oldData = node.data;
                    node.data = value;
                    return oldData;
                }
            }
        }
        else
        {
            map[index] = new LinkedList<>();
        }
        Node<KeyType, DataType> node = new Node<>(key, value);
        map[index].add(node);
        size++;
        if (needRehash())
        {
            rehash();
        }
        return null;
    }

    /**
     * TODO Average Case : O(1) , Worst case : O(n)
     * Assigns a value to a key if it's absent
     *
     * @param key Key which will have its value assigned if absent
     * @return Current DataType instance at key (null if absent)
     */
    public DataType putIfAbsent(KeyType key, DataType value) {
        if (containsKey(key))
        {
            return get(key);
        }

        put(key, value);
        return null;
    }

    /**
     * TODO Average Case : O(1)
     * Removes the node attached to a key
     *
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {
        int index = hash(key);
        if (map[index] != null) {

            for (Node<KeyType, DataType> node : map[index]) {
                if (node.key.equals(key)) {
                    DataType oldData = node.data;
                    map[index].remove(node);
                    size--;
                    return oldData;
                }
            }
        }
        return null;
    }

    /**
     * TODO
     * Removes all nodes
     */
    public void clear() {
        map = new LinkedList[capacity()];
        size = 0;
    }

    /**  Fonction créée pour savoir si un nombre est premier ou pas (utilisée dans le contructeur et nextPrime pour
     éviter la duplication e code).*/
    public boolean isPrime(int number)
    {
        if (number <= 1)
        {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++)
        {
            if (number % i == 0)
            {
                return false;
            }
        }
        return true;
    }
    static class Node<KeyType, DataType> {
        final KeyType key;
        DataType data;

        Node(KeyType key, DataType data) {
            this.key = key;
            this.data = data;
        }
    }
}