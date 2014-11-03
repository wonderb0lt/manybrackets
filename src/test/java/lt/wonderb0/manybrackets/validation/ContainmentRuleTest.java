package lt.wonderb0.manybrackets.validation;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContainmentRuleTest extends RuleTest {
    @Test
    public void testOnlyBracesInParantheses() {
        assertThat(passed("({})"), is(true));
        assertThat(passed("([])"), is(false));
        assertThat(passed("(())"), is(false));
    }

    @Test
    public void testOnlyBracketsInBraces() {
        assertThat(passed("{[]}"), is(true));
        assertThat(passed("{{}}"), is(false));
        assertThat(passed("{()}"), is(false));
    }

    @Test
    public void testComplexContainment() {
        assertThat(passed("{[][()()]}"), is(true));
        assertThat(passed("[()()]"), is(true));
    }

    @Override
    protected Rule newRuleInstance() {
        return new ContainmentRule();
    }
}
