package lt.wonderb0.manybrackets.validation;

import lt.wonderb0.manybrackets.parsing.BracketParser;
import lt.wonderb0.manybrackets.parsing.Token;
import org.junit.Before;

import java.util.List;

/**
 * Created by wonderb0lt on 10/31/14.
 */
public abstract class RuleTest {
    protected Rule rule;

    protected abstract Rule newRuleInstance();

    protected List<Token> parsed(String input) {
        return new BracketParser().parse(input);
    }

    protected boolean passed(String input) {
        return rule.validate(parsed(input)).passedRule();
    }

    @Before
    public void before() {
        rule = newRuleInstance();
    }
}
