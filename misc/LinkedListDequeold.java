/** @source The code below is heavily inspired by the 61b textbook for spring 2018 (http://sp18.datastructur.es/)
 * some lines taken verbatim such as the Node subclass which is almost identical the course's "IntNode" class
 * @param <T>
 */




public class LinkedListDeque<Tc> {
    private IntNode sentinel;
    private IntNode first;
    private IntNode last;
    private int size;

    /**
     * constructor, takes in generic "Tc" class. creates an instance of the linked list deque one element
     * long. Includes a generic null sentinel, done in a a "looping" fashion as reccommended
     * in the text book/ lecture
     */
    public LinkedListDeque(Tc x) {
        size = 1;
        first = new Node(x, sentinel, sentinel);
        last = first;
        sentinel = new Node(null, last, first);

    }

    /**
     * improved linked list similar to the one from our textbook
     * takes in both a prev and next arguments which are referneces to their sequential
     * int nodes
     */
    public static class Node {
        public Tc item;
        public IntNode prev;
        public IntNode next;

        public Node(Tc x, IntNode p, IntNode n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    public void addLast(Tc x) {
        last.next = new Node(x, last, sentinel);
        last = last.next;
        sentinel.prev = last;
        size += 1;
    }



    public void addFirst(Tc x) {
        first.prev = new Node(X, sentinel, first);
        first = first.prev;
        sentinel.next = first;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel == first) {
            return true;
        }
        else {return false;}
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node p = first;
        while(p != sentinel){
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
    }

    public Tc removeFirst(){
        Tc h = first.item;
        first = first.next
        sentinel.next = first;
        first.prev = sentinel;
        size -= 1;
        return h;
    }

    public Tc removeLast(){
        Tc h = last.item;
        last = last.prev;
        sentinel.prev = last;
        last.next = sentinel;
        size -= 1;
        return h;
    }


    public static void main(String[] args) {

    }

}






