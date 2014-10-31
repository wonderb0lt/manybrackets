package lt.wonderb0.manybrackets.parsing;

/**
 * This enum represents all tokens that can be found in a well-formed bracket string.
 * <p/>
 * These tokens always appear in pairs, one that opens a bracket (i.e. '[') and one that closes it (']'). Which one
 * of these forms a token is can be checked using {@link #isClosing()}. If it is a closing form, the corresponding
 * starting form for this token can be queried using {@link #getOpeningForm()}
 */
public enum Token {
    PARANTHESES_OPEN('(', false, null),
    PARANTHESES_CLOSE(')', true, PARANTHESES_OPEN),
    CURLY_OPEN('{', false, null),
    CURLY_CLOSE('}', true, CURLY_OPEN),
    BRACKET_OPEN('[', false, null),
    BRACKET_CLOSE(']', true, BRACKET_OPEN);

    private Character correspondingCharacter;
    private boolean closing;
    private Token openingForm;

    Token(Character correspondingCharacter, boolean closing, Token openingForm) {
        this.correspondingCharacter = correspondingCharacter;
        this.closing = closing;
        this.openingForm = openingForm;
    }

    public Character getCorrespondingCharacter() {
        return correspondingCharacter;
    }

    public boolean isClosing() {
        return closing;
    }

    public boolean isOpening() {
        // Method only for better readability.
        return !isClosing();
    }

    /**
     * @return The opening form for this token (i.e. BRACKET_OPEN for BRACKET_CLOSE), or null if this is not a closing token
     */
    public Token getOpeningForm() {
        return openingForm;
    }

    /**
     * @return The instance of this enum that matches the character, or null if there's no match.
     */
    public static Token match(char character) {
        for (Token token : Token.values()) {
            if (token.getCorrespondingCharacter().equals(character)) {
                return token;
            }
        }

        return null;
    }
}
