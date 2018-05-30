import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static OffByN obn = new  OffByN(3);
    static Palindrome palindrome = new Palindrome();

    @Test
    public void TestOffByOne() {
        assertTrue(palindrome.isPalindrome("fcadfi", obn));
        assertFalse(palindrome.isPalindrome("fhamnbig", obn));
        assertFalse(palindrome.isPalindrome("catfishdogmonkey", obn));
        assertTrue(palindrome.isPalindrome("", obn));
        assertFalse(palindrome.isPalindrome("racecar", obn));
    }
}
