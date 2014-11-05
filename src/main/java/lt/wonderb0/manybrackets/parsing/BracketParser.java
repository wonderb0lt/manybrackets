package lt.wonderb0.manybrackets.parsing;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

public class BracketParser {
    /**
     * @return The tokens in order of appearance in the string
     * @throws java.lang.IllegalArgumentException if the input string is syntactically malformed
     */
    public List<Token> parse(String input) {
        Preconditions.checkNotNull(input, "Passed input string for BracketParser must not be null");
        Preconditions.checkArgument(!input.isEmpty(), "Input cannot be an empty string");

        List<Token> tokens = Lists.newArrayListWithExpectedSize(input.length());

        for (String possibleToken : input.split("")) {
            // We can safely use charAt(0) here since we're safeguarding against dubious input before this line
            Token token = Token.match(possibleToken.charAt(0));

            if (token != null) {
                tokens.add(token);
            } else {
                throw new IllegalArgumentException(String.format("Unparsable input string: Unmatched character '%s'", possibleToken));
            }
        }

        return tokens;
    }
}
