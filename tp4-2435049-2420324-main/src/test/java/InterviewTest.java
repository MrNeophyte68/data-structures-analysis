import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class InterviewTest {
    @Test
    void interview() {
        assertThat(1+1, equalTo(2));
        assertThat(!!true, is(true));
    }
}