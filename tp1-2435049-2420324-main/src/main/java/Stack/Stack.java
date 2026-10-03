package Stack;

import List.List;

/**
 * La classe Stack implémente une pile à partir d'une liste fournie.
 * Une pile est une structure LIFO (Last In First Out).
 * Lorsqu'on ajoute un élément, celui-ci est ajouté sur le dessus de la pile.
 * Lorsqu'on retire un élément, on retire celui qui est sur le dessus (le dernier ajouté)
 */
public class Stack {

    private final List list;

    public Stack(List list) {
        this.list = list;
    }

    /**
     * @return Le nombre d'éléments dans la pile
     */
    public int size() {
        // TODO
        return list.size();
    }

    /**
     * @param value Valeur à ajouter sur le dessus de la pile
     */
    public void push(int value) {
        // TODO
        list.insert(value, list.size());
    }

    /**
     * Retire la valeur sur le dessus de la pile
     *
     * @return La valeur retirée
     */
    public int pop() {
        // TODO
        return list.remove(list.size()-1);
    }

    /**
     * @return La valeur sur le dessus de la pile
     */
    public int peek() {
        // TODO
        return list.get(list.size()-1);
    }
}
