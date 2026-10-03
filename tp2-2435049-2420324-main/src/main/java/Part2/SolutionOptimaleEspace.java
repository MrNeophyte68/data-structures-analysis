package Part2;

import java.util.List;

public class SolutionOptimaleEspace implements Solution {
    @Override
    public Pair find(List<Integer> nums, int k) {
        // TODO
        int a, b;
        for (int i = 0; i < nums.size(); i++) {
            a = nums.get(i);
            for (int j = i + 1; j < nums.size(); j++) { // avoid checking same pair twice
                b = nums.get(j);
                if (a + b == k) return new Pair(a, b);
            }
        }
        return null;
    }
}
