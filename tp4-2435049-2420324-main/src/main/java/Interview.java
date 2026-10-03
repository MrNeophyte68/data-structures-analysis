import java.util.*;

public final class Interview {

    /** Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     *  Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     *
     *  n étant la taille de `text`
     *  m étant le nombre de lettres différentes dans `text`
     *  k étant le paramètre `k` en entrée
     *
     ** TODO Time Complexity  : Worst case O( max(k log m, n) )
     ** TODO Space Complexity : Worst Case O(m)
     *
     * @param text String in which to find the `k`th most frequent letter
     * @param k Value inclusively between 0 and m - 1
     * @return `k`th most frequent character within `text`
     */
    static public Character findKth(String text, int k) {

        if (text == null || text.isEmpty()) return null;

        // 1. Compter les fréquences -> O(n)
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 2. Construire un heap -> O(m)
        ArrayList<LetterFrequency> heap = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            heap.add(new LetterFrequency(entry.getKey(), entry.getValue()));
        }

        // MAX HEAP (important pour "plus fréquent")
        Heap.heapify(heap, Comparator.reverseOrder());

        // 3. Extraire k+1 fois -> O(k log m)
        LetterFrequency current = null;
        for (int i = 0; i <= k; i++) {
            current = Heap.pop(heap, Comparator.reverseOrder());
        }

        // 4. Retourner le caractère
        return current.character;
    };

    static private class LetterFrequency implements Comparable<LetterFrequency> {
        Integer frequency;
        Character character;

        public LetterFrequency(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }
        @Override
        public int compareTo(LetterFrequency other) {
            return this.frequency.compareTo(other.frequency);;
        }
    }
}