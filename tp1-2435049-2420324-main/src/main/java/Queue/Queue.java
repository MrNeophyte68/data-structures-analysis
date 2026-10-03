package Queue;

import List.List;

/**
 * La classe Queue implémente une file à partir d'une liste fournie.
 * Une file est une structure FIFO (First In First Out).
 * Lorsqu'on ajoute un élément, celui-ci est ajouté à l'arrière de la file.
 * Lorsqu'on retire un élément, on retire celui qui est à l'avant de la file.
 */
public class Queue {

    private final List list;

    public Queue(List list) {
        this.list = list;
    }

    /**
     * @return Le nombre d'éléments dans la file
     */
    public int size() {
        // TODO
        return list.size();
    }

    /**
     * @param value La valeur à insérer à l'arrière de la file
     */
    public void enqueue(int value) {
        // TODO
        list.insert(value, list.size());
    }

    /**
     * Retire l'élément à l'avant de la file
     *
     * @return L'élément retiré
     */
    public int dequeue() {
        // TODO
        return list.remove(0);
    }

    /**
     * @return L'élément à l'avant de la file
     */
    public int peek() {
        // TODO
        return list.get(0);
    }
}
