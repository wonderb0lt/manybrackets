package lt.wonderb0.manybrackets.validation;

import lt.wonderb0.manybrackets.parsing.Token;

import java.util.List;

/**
 * A rule represents one particular rule that a list of tokens must conform to.
 */
public interface Rule {
    RuleValidationResult validate(List<Token> tokens);
}
