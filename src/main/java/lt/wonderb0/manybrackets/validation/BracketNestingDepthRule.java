package lt.wonderb0.manybrackets.validation;

import lt.wonderb0.manybrackets.parsing.Token;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class BracketNestingDepthRule implements Rule {
    private Stack<AtomicInteger> nestingDepths = new Stack<>();
    private boolean inBracket = false;

    @Override
    public RuleValidationResult validate(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.isOpening() && !nestingDepths.isEmpty() && nestingDepths.peek().get() + 1 >= 2) {
                return RuleValidationResult.failed(null);
            }

            if (token == Token.BRACKET_OPEN) {
                nestingDepths.push(new AtomicInteger());
            } else if (token == Token.BRACKET_CLOSE) {
                nestingDepths.pop();
            } else {
                if (!nestingDepths.isEmpty()) {
                    if (token.isOpening()) {
                        nestingDepths.peek().incrementAndGet();
                    } else {
                        nestingDepths.peek().decrementAndGet();
                    }
                }
            }
        }

        return RuleValidationResult.passed();
    }
}
