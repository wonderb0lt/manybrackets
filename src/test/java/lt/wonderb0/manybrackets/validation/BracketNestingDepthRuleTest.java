package lt.wonderb0.manybrackets.validation;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BracketNestingDepthRuleTest extends RuleTest {
    @Test
    public void testSingularBracket() {
        assertThat(passed("[]"), is(true));
    }

    @Test
    public void testMultipleBracketsNested() {
        assertThat(passed("[[[]]]"), is(true));
    }

    @Test
    public void testListOfNestedTokensIsValid() {
        assertThat(passed("[()()]"), is(true));
    }

    @Test
    public void testNonBracketTokensAreIgnored() {
        assertThat(passed("{}"), is(true)); // Should not break otherwise OK token-strings
    }

    @Test
    public void testInvalidSample() {
        assertThat(passed("[{[]}]"), is(false));
    }

    /**
     * A more complex version of the invalid sample, also taking the "list of OK tokens is OK" rule into consideration
     */
    @Test
    public void testComplexInvalidSample() {
        assertThat(passed("[[][{[]}]]"), is(false));
    }

    @Override
    protected Rule newRuleInstance() {
        return new BracketNestingDepthRule();
    }
}
