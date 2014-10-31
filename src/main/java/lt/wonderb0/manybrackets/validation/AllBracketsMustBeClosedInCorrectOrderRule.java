package lt.wonderb0.manybrackets.validation;

import lt.wonderb0.manybrackets.parsing.Token;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class AllBracketsMustBeClosedInCorrectOrderRule implements Rule {
    /**
     * A stack of open tokens, represented by their opening form.
     */
    private Stack<Token> openTokens = new Stack<>();
    private RuleValidationResult result;

    @Override
    public RuleValidationResult validate(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.isOpening()) {
                handleOpening(token);
            } else {
                handleClosing(token);
            }

            // The handling methods set the result if they can already determine the result before all tokens are read
            if (result != null) {
                return result;
            }
        }

        if (openTokens.isEmpty()) {
            return new RuleValidationResult(true);
        } else {
            return new RuleValidationResult(false, "The following brackets remained unclosed: " + openTokens);
        }
    }

    private void handleOpening(Token token) {
        openTokens.push(token);
    }

    private void handleClosing(Token token) {
        try {
            Token lastOpened = openTokens.peek();

            if (token.getOpeningForm().equals(lastOpened)) {
                openTokens.pop();
            } else {
                result = new RuleValidationResult(false, "Encountered closing token " + token + " even though " + lastOpened + " needs to be closed first");
            }
        } catch (EmptyStackException e) {
            result = new RuleValidationResult(false, "Found closing bracket of type " + token + ", but this closes unopened bracket");
        }
    }
}
