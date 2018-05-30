import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.


    // Your tests go here.
    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    static CharacterComparator offByOne = new OffByOne();
    static Palindrome palindrome = new Palindrome();

    @Test
    public void TestOffByOne() {
        assertTrue(palindrome.isPalindrome("fg", offByOne));
        assertTrue(palindrome.isPalindrome("fhamnbig", offByOne));
        assertFalse(palindrome.isPalindrome("catfishdogmonkey", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertFalse(palindrome.isPalindrome("racecar", offByOne));
    }
}
