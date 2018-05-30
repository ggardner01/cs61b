import java.util.HashMap;
import  java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols){
        Map<Character, Integer> m = new HashMap<>();
        for(int i = 0; i < inputSymbols.length; i += 1) {
            if(!m.containsKey(inputSymbols[i])){m.put(inputSymbols[i], 1);}
            else{m.put(inputSymbols[i], m.get(inputSymbols[i]) + 1);}
        }
        return m;
    }
    public static void main(String[] args){
        char[] c = FileUtils.readFile(args[0]);
        Map<Character, Integer> ftab = buildFrequencyTable(c);
        BinaryTrie bt = new BinaryTrie(ftab);
        Map<Character, BitSequence> km = bt.buildLookupTable();

        ArrayList<BitSequence> bitlist = new ArrayList<>();
        for(int i = 0; i < c.length; i += 1) {
            bitlist.add(km.get(c[i]));
        }
        BitSequence mybit = BitSequence.assemble(bitlist);

        ObjectWriter writ = new ObjectWriter(args[0] + ".huf");

        writ.writeObject(bt);
        writ.writeObject(c.length);
        writ.writeObject(mybit);

    }
}
