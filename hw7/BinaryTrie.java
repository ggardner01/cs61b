

import java.util.*;
import java.io.Serializable;



public class BinaryTrie implements Serializable {
    private Node tree;


    private static class Node implements Comparable<Node>, Serializable {
        private char ch;
        private int freq;
        private Node left, right;

        Node(char c, int f, Node l, Node r){
            this.ch = c;
            this.freq = f;
            this.left = l;
            this.right = r;

        }

        public boolean isleaf(){
            return left == null && right == null;
        }

        public int compareTo(Node N){
            return (this.freq - N.freq);
        }

    }


    public BinaryTrie(Map<Character, Integer> frequencyTable){
        PriorityQueue<Node> pr = new PriorityQueue<>();
        Iterator<Character> it = frequencyTable.keySet().iterator();
        while(it.hasNext()){
            Character c = it.next();
            if(frequencyTable.get(c) != 0) {
                pr.add(new Node(c, frequencyTable.get(c), null, null));
            }
        }
        while(pr.size() > 1){
            Node left = pr.poll();
            Node right = pr.poll();
            Node p = new Node('0',left.freq + right.freq, left, right);
            pr.add(p);
        }
        tree = pr.poll();
    }








    public Match longestPrefixMatch(BitSequence querySequence){
        Node curr = tree;
        BitSequence ret = new BitSequence();
        int i = 0;
        while(!curr.isleaf()) {
            int bi = querySequence.bitAt(i);
            ret = ret.appended(bi);
            if(bi == 0){curr = curr.left;}
            else if(bi == 1){curr = curr.right;}
            i += 1;
        }
        return new Match(ret, curr.ch);
    }


    public Map<Character, BitSequence> buildLookupTable(){
        Map<Character, BitSequence> re = new HashMap<>();
        createmap(re, tree, new BitSequence());
        return re;
    }

    private static void createmap(Map m, Node n, BitSequence bi){
        if(!n.isleaf()){
            createmap(m, n.left, bi.appended(0));
            createmap(m, n.right, bi.appended(1));
        }else {
            m.put(n.ch, bi);
        }

    }

}
