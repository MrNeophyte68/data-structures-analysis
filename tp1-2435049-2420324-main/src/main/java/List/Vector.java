package List;

/**
 * La classe Vector implémente un tableau dynamique.
 * Elle commence avec un tableau de capacité 1, mais ne contient aucun élément.
 * Lorsqu'un élément est ajouté avec 'insert' et que le tableau est plein, sa capacité doit doubler.
 * Lorsque 'clear' est appelé, tous les éléments sont retirés du tableau et sa capacité doit revenir à 1.
 */
public class Vector implements List {

    /**
     * Le tableau utilisé par le vecteur pour enregistrer les éléments.
     */
    private int[] values = new int[1];

    /**
     * @return Le nombre d'éléments dans la liste
     */
    @Override
    public int size() {
        // TODO

        int num = 0;
        for(int x : values){
            if(x != 0){
                num++;
            }
        }

        return num;
    }

    /**
     * @param index L'index de l'élément à accéder
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

        return values[index];
    }

    /**
     * @param value La valeur que prendra l'élément
     * @param index L'index de l'élément à modifier
     * @throws IndexOutOfBoundsException Si l'index est hors de l'intervalle [0, size[
     */
    @Override
    public void set(int value, int index) throws IndexOutOfBoundsException {
        // TODO
        if (index < 0 || index > values.length-1)
        {
            throw new IndexOutOfBoundsException();
        }

        values[index] = value;
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
        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }

        if(values[values.length-1] != 0){
            int[] newValues = new int[values.length*2];
            for(int i = 0; i < values.length; ++i){
                newValues[i] = values[i];
            }
            values = newValues;
        }

        if(index == 0){
            int[] newValues = new int[values.length];
            for(int i = 0; i < this.size(); ++i){
                newValues[i+1] = values[i];
            }
            newValues[index] = value;
            values = newValues;
        }
        else if(index == this.size()){
            int[] newValues = new int[values.length];
            for(int i = 0; i < this.size(); ++i){
                newValues[i] = values[i];
            }
            newValues[index] = value;
            values = newValues;
        }
        else{
            int[] newValues = new int[values.length];
            for(int i = 0; i < index; ++i){
                newValues[i] = values[i];
            }
            newValues[index] = value;
            for(int i = index; i < this.size(); ++i){
                newValues[i+1] = values[i];
            }
            values = newValues;
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
        int numRemoved = 0;
        if (index < 0 || index > this.size())
        {
            throw new IndexOutOfBoundsException();
        }
        numRemoved = values[index];
        int[] newValues = new int[values.length];
        for(int i = 0; i < index; ++i){
            newValues[i] = values[i];
        }
        for(int i = index+1; i < this.size(); ++i){
            newValues[i-1] = values[i];
        }
        values = newValues;

        return numRemoved;
    }

    /**
     * Enlève tous les éléments de la liste
     */
    @Override
    public void clear() {
        // TODO
        int[] newValues = new int[1];
        values = newValues;
    }
}