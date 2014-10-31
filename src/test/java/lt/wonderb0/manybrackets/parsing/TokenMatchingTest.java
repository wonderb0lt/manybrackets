package lt.wonderb0.manybrackets.parsing;

import lt.wonderb0.manybrackets.parsing.Token;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.*;

public class TokenMatchingTest {
    @Test
    public void testTokenMapping() {
        assertThat(Token.match('{'), is(Token.CURLY_OPEN));
        assertThat(Token.match('!'), is(nullValue()));
    }
}
