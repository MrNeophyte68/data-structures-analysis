package List;

/**
 * La classe LinkedList implémente une liste simplement chainée.
 * La classe Node représente un noeud de la liste.
 * L'usage de null permet d'indiquer la fin de la liste.
 * Les appels à 'insert' vont créer des nouveaux noeuds.
 * Les appels à 'remove' vont retirer des noeuds.
 * Dans les deux cas, l'ordre des noeuds de la liste doit être préservé
 */
public class LinkedList implements List {

    private Node head = null;

    private static class Node {
        int value;
        Node next = null;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * @return Le nombre d'éléments dans la liste
     */
    @Override
    public int size() {
        // TODO
        int size = 0;
        Node current = head;
        while (current != null)
        {
            size++;
            current = current.next;
        }
        return size;
    }

    /**
     * @
     * param index L'index de l'élément à accéder
     * @return La valeur de l'élément à l'index donné
     * @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size[
     */
    @Override
    public int get(int index) throws IndexOutOfBoundsException {
        // TODO
        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
        return current.value;
    }

    /**
     * @param value La valeur que prendra l'élément
     * @param index L'index de l'élément à modifier
     * @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size[
     */
    @Override
    public void set(int value, int index) throws IndexOutOfBoundsException {
        // TODO
        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }

        Node current = head;
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
        current.value = value;
    }

    /// Exemples :
    /// <code>list.insert(1, 0); // insère 1 au début de la liste </code>
    /// <code>list.insert(2, list.size()); // insère 2 à la fin de la liste </code>
    /// <code>list.insert(3, list.size()+1); // Erreur </code>
    ///
    /// @param value Valeur de l'élément à insérer dans la liste
    /// @param index L'index que prendra l'élément une fois inséré
    /// @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size]
    @Override
    public void insert(int value, int index) throws IndexOutOfBoundsException {
        // TODO
        Node previous = head;
        Node current = head;
        Node newNode = new Node(value);

        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0)
        {
            //Node newNode = new Node(value);
            newNode.next = current;
            head = newNode;
        }

        else
        {
            //Node newNode = new Node(value);
            for (int i = 0; i < index; i++)
            {
                previous = current;
                current = current.next;
            }

            newNode.next = current;
            previous.next = newNode;
        }
    }

    /**
     * @param index L'index de l'élément à retirer
     * @return L'élément retiré
     * @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size[
     */
    @Override
    public int remove(int index) throws IndexOutOfBoundsException {
        // TODO
        Node previous = null;
        Node current = head;

        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0)
        {
            int value = head.value;
            head = head.next;
            return value;
        }

        for (int i = 0; i < index; i++)
        {
            previous = current;
            current = current.next;
        }
        previous.next = current.next;

        return current.value;
    }

    /**
     * Enlève tous les éléments de la liste
     */
    @Override
    public void clear() {
        // TODO
        head = null;
    }
}
