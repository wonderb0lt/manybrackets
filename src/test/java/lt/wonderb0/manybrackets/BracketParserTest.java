package lt.wonderb0.manybrackets;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

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
}
