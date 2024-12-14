import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testCase01() {
        char[] input = new char[] {'2','1','3','3',};
        var result = Main.findNextPalindrome(input);
        assertEquals("2222", result);
    }

    @Test
    void testCase02() {
        char[] input = new char[] {'1','9','9','8',};
        var result = Main.findNextPalindrome(input);
        assertEquals("2002", result);
    }

    @Test
    void testCase03() {
        char[] input = new char[] {'9','9','9','9',};
        var result = Main.findNextPalindrome(input);
        assertEquals("10001", result);
    }
}