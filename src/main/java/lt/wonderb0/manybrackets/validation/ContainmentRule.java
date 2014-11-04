package lt.wonderb0.manybrackets.validation;

import com.google.common.collect.Sets;
import lt.wonderb0.manybrackets.parsing.Token;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Implementation for rules 3, 4 and (implicitly) rule 6.
 * <p/>
 * These rules state various rules which kinds of tokens are allowed enclosed by other tokens.
 */
public class ContainmentRule implements Rule {
    private Stack<Token> openTokens = new Stack<>();

    @Override
    public RuleValidationResult validate(List<Token> tokens) {
        for (Token token : tokens) {
            if (!openTokens.isEmpty()) {
                boolean ruleViolated = false;

                switch (openTokens.peek()) {
                    case PARANTHESES_OPEN:
                        // Allowed are only curlies and the closing form of the currently open token
                        ruleViolated = (token != Token.PARANTHESES_CLOSE
                                && token != Token.CURLY_OPEN && token != Token.CURLY_CLOSE);
                        break;
                    case CURLY_OPEN:
                        ruleViolated = (token != Token.CURLY_CLOSE &&
                                token != Token.BRACKET_OPEN && token != Token.BRACKET_CLOSE);
                        break;
                }

                if (ruleViolated) {
                    return RuleValidationResult.failed("Only Brackets in Parantheses / Parantheses in curly braces");
                }
            }

            // Another rule checks closing order, we just assume correct order right here.
            if (token.isOpening()) {
                openTokens.push(token);
            } else {
                openTokens.pop();
            }
        }
        return RuleValidationResult.passed();
    }
}
