package lt.wonderb0.manybrackets.validation;

import com.google.common.collect.ImmutableList;
import lt.wonderb0.manybrackets.parsing.Token;

import java.util.List;

/**
 * A validator that executes all rules in this package.
 */
public class Validator {
    private final List<Rule> RULES = ImmutableList.of(
            new AllBracketsMustBeClosedInCorrectOrderRule(),
            new ContainmentRule(),
            new BracketNestingDepthRule()
    );

    public boolean validate(List<Token> tokens) {
        for (Rule rule : RULES) {
            RuleValidationResult result = rule.validate(tokens);

            if (!result.passedRule()) {
                return false;
            }
        }

        return true;
    }
}
