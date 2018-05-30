public class HuffmanDecoder {
    public static void main(String[] args){
        ObjectReader re = new ObjectReader(args[0]);
        BinaryTrie bt = (BinaryTrie) re.readObject();
        int len = (int) re.readObject();
        BitSequence mybit = (BitSequence) re.readObject();

        char [] c = new char[len];
        for(int i = 0; i < len; i += 1) {
            Match ma = bt.longestPrefixMatch(mybit);
            c[i] = ma.getSymbol();
            mybit = mybit.allButFirstNBits(ma.getSequence().length());
        }
        FileUtils.writeCharArray(args[1], c);
    }
}