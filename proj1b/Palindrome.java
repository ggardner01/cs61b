public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> d = new LinkedListDeque();
        for(int i = 0; i < word.length();i += 1) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word){
        if(word.length() == 0 | word.length() == 1){
            return true;
        }
        if (word.charAt(0) == word.charAt(word.length()-1)){
            return isPalindrome(word.substring(1,word.length()-1));
        }
        else{
            return false;
        }
    }


    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word.length() == 0 | word.length() == 1){
            return true;
        }
        if (cc.equalChars(word.charAt(0), word.charAt(word.length()-1))){
            return isPalindrome(word.substring(1,word.length()-1), cc);
        }
        else{
            return false;
        }

    }

}
