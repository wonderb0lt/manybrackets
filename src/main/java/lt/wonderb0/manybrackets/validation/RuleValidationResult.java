package lt.wonderb0.manybrackets.validation;

import com.google.common.base.MoreObjects;

import java.util.Optional;

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

    public static RuleValidationResult passed() {
        return new RuleValidationResult(true, null);
    }

    public static RuleValidationResult failed(String message) {
        return new RuleValidationResult(false, message);
    }

    public boolean passedRule() {
        return passesRule;
    }

    public String getMessage() {
        return message;
    }
}
