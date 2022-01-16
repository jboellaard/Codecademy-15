package Domain.Tools.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import Domain.Tools.UrlTools;

public class UrlToolsTest {

    @Test
    public void testValidUrlRequiresNoHttpOrHttpsEnsuresFalse() {
        // arrange
        String url = "www.a.b";

        // act
        boolean result = UrlTools.formatUrl(url);

        // assert
        assertEquals(false, result);
    }

    @Test
    public void testValidUrlRequiresOnlyOneDotEnsuresFalse() {
        // arrange
        String url = "https://www.b";

        // act
        boolean result = UrlTools.formatUrl(url);

        // assert
        assertEquals(false, result);
    }

    @Test
    public void testValidUrlRequiresLengthAfterSlashBiggerThanOneEnsuresFalse() {
        // arrange
        String url = "https://.a.b";

        // act
        boolean result = UrlTools.formatUrl(url);

        // assert
        assertEquals(false, result);
    }

    @Test
    public void testValidUrlRequiresLengthBetweenDotsBiggerThanOneEnsuresFalse() {
        // arrange
        String url = "https://www..b";

        // act
        boolean result = UrlTools.formatUrl(url);

        // assert
        assertEquals(false, result);
    }

    @Test
    public void testValidUrlRequiresLengthAfterLastDotBiggerThanOneEnsuresFalse() {
        // arrange
        String url = "https://www.a.";

        // act
        boolean result = UrlTools.formatUrl(url);

        // assert
        assertEquals(false, result);
    }

    @Test
    public void testValidUrlRequiresHttpOrHttpsEnsuresTrue() {
        // arrange
        String url = "http://www.url.com";

        // act
        boolean result = UrlTools.formatUrl(url);

        // assert
        assertEquals(true, result);
    }
}
