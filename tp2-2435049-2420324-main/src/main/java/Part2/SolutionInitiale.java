package Part2;

import java.util.List;

public class SolutionInitiale implements Solution {
    @Override
    public Pair find(List<Integer> nums, int k) {
        // TODO
        int a, b;
        for(int i = 0; i < nums.size(); i++){
            a = nums.get(i);
            for(int j = 0; j < nums.size(); j++){
                if(j == i) continue;
                b = nums.get(j);
                if(a + b == k) return new Pair(a, b);
            }
        }
        return null;
    }
}
