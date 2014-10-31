package lt.wonderb0.manybrackets;

/**
 * This enum represents all tokens that can be found in a well-formed bracket string.
 */
public enum Token {
    PARANTHESES_OPEN('('),
    PARANTHESES_CLOSE(')'),
    CURLY_OPEN('{'),
    CURLY_CLOSE('}'),
    BRACKET_OPEN('['),
    BRACKET_CLOSE(']');

    private Character correspondingCharacter;

    Token(Character correspondingCharacter) {
        this.correspondingCharacter = correspondingCharacter;
    }

    public Character getCorrespondingCharacter() {
        return correspondingCharacter;
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
