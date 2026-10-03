package Part2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.*;
import java.util.stream.IntStream;

public abstract class SolutionTest {

    protected Solution solution = makeInstance();

    abstract Solution makeInstance();

    @BeforeEach
    public void setup() {
        this.solution = makeInstance();
    }

    @Test
    public void testEmpty() {
        assertThat(solution.find(List.of(), 0), nullValue());
    }

    @Test
    public void testSimple() {
        assertThat(solution.find(List.of(1, 2), 3), is(new Pair(1, 2)));
    }

    @Test
    public void testNoMatch() {
        assertThat(solution.find(List.of(1, 2, 3), 7), nullValue());
    }

    @Test
    public void testMultiplePairs() {
        assertThat(solution.find(List.of(1, 2, 3, 4), 5), anyOf(is(new Pair(2, 3)), is(new Pair(1, 4))));
    }

    @Test
    public void testMultiplePairsUnsorted() {
        assertThat(solution.find(List.of(4, 1, 3, 2), 5), anyOf(is(new Pair(2, 3)), is(new Pair(1, 4))));
    }

    @Test
    public void testDuplicates() {
        assertThat(solution.find(List.of(2, 2, 3), 4), is(new Pair(2, 2)));
    }

    @Test
    public void testNegativeNumbers() {
        assertThat(solution.find(List.of(-1, 1, 2, 3), 2), is(new Pair(-1, 3)));
    }

    @Test
    public void testZeroSum() {
        assertThat(solution.find(List.of(-2, 0, 2), 0), is(new Pair(-2, 2)));
    }

    @Test
    public void testSingleElement() {
        assertThat(solution.find(List.of(1), 1), nullValue());
    }

    @Test
    public void testAllSameElements() {
        assertThat(solution.find(List.of(2, 2, 2, 2), 4), is(new Pair(2, 2)));
    }

    @Test
    public void testAllZeros() {
        assertThat(solution.find(List.of(0, 0, 0), 0), is(new Pair(0, 0)));
    }

    @Test
    public void testNegativeTarget() {
        assertThat(solution.find(List.of(-5, -3, -1, 0), -8), is(new Pair(-5, -3)));
    }

    @Test
    public void testNegativeTargetUnsorted() {
        assertThat(solution.find(List.of(0, -3, -1, -5), -8), is(new Pair(-5, -3)));
    }

    @Test
    public void testPairWithItselfNotAllowed() {
        assertThat(solution.find(List.of(2), 4), nullValue());
    }

    @Nested
    class LargeList {
        List<Integer> nums;

        @BeforeEach
        void setup() {
            nums = IntStream.range(0, 100_000).boxed().toList();
        }

        @Test
        public void testNoSolution() {
            assertThat(solution.find(nums, 199_999), nullValue());
        }

        @Test
        public void testHasSolution() {
            assertThat(solution.find(nums, 199_997), is(new Pair(99_999, 99_998)));
        }

        @Nested
        class Unsorted{
            @BeforeEach
            void setup() {
                nums = new ArrayList<>(nums);
                Collections.shuffle(nums);
            }

            @Test
            public void testNoSolution() {
                assertThat(solution.find(nums, 199_999), nullValue());
            }

            @Test
            public void testHasSolution() {
                assertThat(solution.find(nums, 199_997), is(new Pair(99_999, 99_998)));
            }
        }
    }

}
