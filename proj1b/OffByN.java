public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int val){
        N = val;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if((int)x + N == (int)y | (int)x - N == (int)y){
            return true;
        }
        else{return false;}
    }
}
