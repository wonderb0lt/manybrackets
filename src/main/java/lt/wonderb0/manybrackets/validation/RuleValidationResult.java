package lt.wonderb0.manybrackets.validation;

import com.google.common.base.MoreObjects;

/**
 * Simple container for the result of single {@link lt.wonderb0.manybrackets.validation.Rule}s
 */
public class RuleValidationResult {
    /**
     * Whether or not an input has passed the rule
     */
    private boolean passesRule;
    /**
     * If an input did not pass a rule, additional information can be found in this field
     */
    private String message;

    RuleValidationResult(boolean passesRule, String message) {
        this.passesRule = passesRule;
        this.message = MoreObjects.firstNonNull(message, "");
    }

    public RuleValidationResult(boolean passesRule) {
        this(passesRule, null);
    }

    public boolean passedRule() {
        return passesRule;
    }

    public String getMessage() {
        return message;
    }
}
