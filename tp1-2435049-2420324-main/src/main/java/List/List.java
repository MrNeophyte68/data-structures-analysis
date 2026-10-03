package List;

/**
 * Représente une liste d'éléments indexée de 0 à size()-1
 */
public interface List {

    /**
     * @return Le nombre d'éléments dans la liste
     */
    int size();

    /**
     * @param index L'index de l'élément à accéder
     * @return La valeur de l'élément à l'index donné
     * @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size[
     */
    int get(int index) throws IndexOutOfBoundsException;

    /**
     * @param value La valeur que prendra l'élément
     * @param index L'index de l'élément à modifier
     * @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size[
     */
    void set(int value, int index) throws IndexOutOfBoundsException;

    /// Exemples :
    /// <code>list.insert(1, 0); // insère 1 au début de la liste </code>
    /// <code>list.insert(2, list.size()); // insère 2 à la fin de la liste </code>
    /// <code>list.insert(3, list.size()+1); // Erreur </code>
    ///
    /// @param value Valeur de l'élément à insérer dans la liste
    /// @param index L'index que prendra l'élément une fois inséré
    /// @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size]
    void insert(int value, int index) throws IndexOutOfBoundsException;

    /**
     * @param index L'index de l'élément à retirer
     * @return L'élément retiré
     * @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size[
     */
    int remove(int index) throws IndexOutOfBoundsException;

    /**
     * Enlève tous les éléments de la liste
     */
    void clear();
}
