package Part2;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class SolutionOptimaleTemps implements Solution {
    @Override
    public Pair find(List<Integer> nums, int k) {
        // TODO
        List<Integer> sortedNums = new ArrayList<>(nums);
        Collections.sort(sortedNums);
        int ai = sortedNums.size() - 1;
        int bi = 0;
        while(bi < ai){
            int sum = sortedNums.get(ai) + sortedNums.get(bi);

            if(sum == k) return new Pair(sortedNums.get(ai), sortedNums.get(bi));
            else if(sum < k) bi++;
            else ai--;

        }
        return null;
    }
}
