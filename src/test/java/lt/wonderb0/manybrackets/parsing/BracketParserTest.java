package lt.wonderb0.manybrackets.parsing;

import lt.wonderb0.manybrackets.parsing.BracketParser;
import lt.wonderb0.manybrackets.parsing.Token;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;

public class BracketParserTest {
    @Test(expected = NullPointerException.class)
    public void testNullInput() {
        new BracketParser().parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInput() {
        new BracketParser().parse("");
    }

    @Test
    public void testSimpleCorrectInput() {
        List<Token> tokens = new BracketParser().parse("(())");

        assertThat(tokens, hasSize(4));
        assertThat(tokens, contains(
                        Token.PARANTHESES_OPEN,
                        Token.PARANTHESES_OPEN,
                        Token.PARANTHESES_CLOSE,
                        Token.PARANTHESES_CLOSE)
        );
    }

    @Test
    public void testSimpleIncorrectInput() {
        try {
            new BracketParser().parse("((a))");
            fail("Invalid chracter 'a' should have caused an exception");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), containsString("Unmatched character 'a'"));
        }
    }
}
