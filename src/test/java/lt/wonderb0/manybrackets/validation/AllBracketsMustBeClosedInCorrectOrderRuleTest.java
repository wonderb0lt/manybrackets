package lt.wonderb0.manybrackets.validation;

import lt.wonderb0.manybrackets.parsing.BracketParser;
import lt.wonderb0.manybrackets.parsing.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class AllBracketsMustBeClosedInCorrectOrderRuleTest {
    private Rule rule;

    @Before
    public void before() {
        rule = new AllBracketsMustBeClosedInCorrectOrderRule();
    }

    @Test
    public void testSimplePassingExample() {
        assertThat(rule.validate(parsed("()")).passedRule(), is(true));
        assertThat(rule.validate(parsed("[]")).passedRule(), is(true));
        assertThat(rule.validate(parsed("{}")).passedRule(), is(true));
    }

    @Test
    public void testComplexExample() {
        assertThat(rule.validate(parsed("[()()({})]")).passedRule(), is(true));
    }

    @Test
    public void testUnclosedBracketsDetected() {
        RuleValidationResult result = rule.validate(parsed("(()"));

        assertThat(result.passedRule(), is(false));
        assertThat(result.getMessage(), containsString("unclosed"));
    }

    @Test
    public void testClosingBracketWithoutOpeningDetected() {
        RuleValidationResult result = rule.validate(parsed("[]]"));

        assertThat(result.passedRule(), is(false));
        assertThat(result.getMessage(), containsString("unopened"));
    }

    @Test
    public void testIllegalOrderDetected() {
        RuleValidationResult result = rule.validate(parsed("{(})"));

        assertThat(result.passedRule(), is(false));
    }

    private List<Token> parsed(String input) {
        return new BracketParser().parse(input);
    }
}
