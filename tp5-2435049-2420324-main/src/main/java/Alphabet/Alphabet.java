package Alphabet;
import java.util.ArrayList;

public class Alphabet {

    /**
     * TODO
     * From the words contained in the dictionary of a fictitious language, find the lexical order of
     * the symbols composing the language.
     *
     * @param dictionary Contains all the word of a language
     * @return The lexicalOrder of the symbols composing this language
     */
    public static ArrayList<Character> lexicalOrder(String[] dictionary) {
        ArrayList<Character> lexicalOrder = new ArrayList<>();
        Graph<Character> graph = new Graph<Character>();

        //first part, compares a pair of words (current and the one next to it)
        //minlength is there so that the shortest word does not go out of bounds as we compare both words char by char
        //as soon as we find two chars that are not similar then the vertices will be bound by an edge
        //==============================================================================================================
        //time complexity, let N = number of words in dictionary
        //L = average length of a word
        //C = total number of unique characters (vertices)
        //E = number of edges (relations between characters)
        //for (int i = 0; i < dictionary.length - 1; i++) gives us a O(N-1), and inner loop for is O(L) therefore the complexity
        //of the first part would be O( (N-1) * L) -> O(N*L)
        for (int i = 0; i < dictionary.length - 1; i++) {
            String word1 = dictionary[i];
            String word2 = dictionary[i+1];
            int minLength = Math.min(word1.length(), word2.length());

            for (int j = 0; j < minLength; j++) {
                char letter1 = word1.charAt(j);
                char letter2 = word2.charAt(j);
                if (letter1 != letter2) {
                    graph.connect(letter1, letter2);
                    break;
                }
            }
        }

        //second part, we iterate over each vertex of the graph, if the vertex has an indegree of 0
        //we place it first inside the queue
        //==============================================================================================================
        //for (Vertex<Character> vertex : graph.vertices) gives us O(C)
        ArrayList<Vertex<Character>> queue = new ArrayList<>();
        for (Vertex<Character> vertex : graph.vertices) {
            if (vertex.indegree == 0) {
                queue.add(vertex);
            }
        }

        //third part we sort in a topological order using DFS sorting
        //we add the value (known as label) of the vertices from second part to the lexical order
        //then we look for the vertices that were connected to current vertex (ex. e->a, we look for a, e.g. its neighbours)
        //by the process of DFS sorting we can reduce its neighbours indegree by one if we find a neighbour with indegree of 0,
        //we add to queue, we repeat third part until we find the sorted lexical order
        //==============================================================================================================
        //the inner for loop is of O(E) and the while will loop over each vertices,
        //since every edge and vertex are only visited once we get O(C+E)
        int index = 0;
        while (index < queue.size()) {
            Vertex<Character> current = queue.get(index++);
            lexicalOrder.add(current.label);

            for (Vertex<Character> neighbour : current.toVertex) {
                neighbour.indegree--;
                if (neighbour.indegree == 0) {
                    queue.add(neighbour);
                }
            }
        }
        //time complexity, thus the total complexity would be O( N*L + C + E + C) -> O(N*L + C + E)
        return lexicalOrder;
    }
}


