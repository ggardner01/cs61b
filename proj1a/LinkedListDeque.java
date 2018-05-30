/** @source The code below is heavily inspired by the 61b textbook for spring 2018 (http://sp18.datastructur.es/)
 * some lines taken verbatim such as the Node subclass which is almost identical the course's "IntNode" class
 * @param
 */

public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    /**
     * @LinkedListDeque
     * constructor, takes in generic "Tc" class. creates an instance of the linked list deque one element
     * long. Includes a generic null sentinel, done in a a "looping" fashion as reccommended
     * in the text book/ lecture
     */

    public LinkedListDeque(){
        size = 0;
        sentinel = new Node(null,sentinel,sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /**
     * @Node
     * improved linked list similar to the one from our textbook
     * takes in both a prev and next arguments which are referneces to their sequential
     * int nodes
     */
    private class Node{
        public T item;
        public Node prev;
        public Node next;

        public Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }
    }

    /**
     * @addFirst
     *  Creates a node with x as its item. It points to sentinel as its previous node and the sentinels.next node as
     *  its next. sentinel.next (the "old" first item) then has its previous reassigned to the new int note. The
     *  sentinel also has its next reassigned to the new node. this adds a new int node with x to the list at the front
     *  (also adds to size counter)
     *  @addLast
     *  Functions the same as addFirst but in reverse
     * @param x
     */
    public void addLast(T x) {
        Node p = new Node(x,sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }


    public void addFirst(T x) {
        Node p = new Node(x,sentinel,sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }

    /** if the sentinel's next points to itself, this means the sentinel is "unlinked" and thus the list is empty.
     * This function returns a boolean which is true if and only if the deque list is empty
     * @return
     */
    public boolean isEmpty() {
        if (sentinel == sentinel.next) {
            return true;
        }
        else {
            return false;
        }
    }

    /** returns size by reading it from the instance variable */
    public int size(){
        return size;
    }

    /** printers object by object by using the pointer p. the item from the int node is printed, not the intnode object
     * itself.
     */
    public void printDeque(){
        Node p = sentinel.next;
        while(p != sentinel){
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
    }

    /** @removeFirst removes the first item in the deque list. first selects the node two in front the sentinel (sentinel.next.next)
     and reassignes its prev to the sentinel, then changes the sentinel's next reference to the same node that is two ahead
     this removes all references to the prior first node in the chain of link nodes linked to sentinel, thus (hopefully)
     allowing java to remove the unused node from memorey
     @addLast does the same as addFirst but in reverse
     **/

    public T removeFirst(){
        if(this.isEmpty()){
            return null;
        }
        else {
            T h = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return h;
        }
    }

    public T removeLast(){
        if(this.isEmpty()){
            return null;
        }
        else {
            T h = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return h;
        }
    }

    /** @get Gets item at corresponding to index "i" from the linked list. Raises an error if the index is out of range.
     * it then checks if the index is closer to the front or back of the list (to optimize runtime) then loops forward index times
     * or backward size - index times. It then returns the item corresponding to that node. uses iterative pointer to "scroll"
     * through d-list
     * @param index
     *
     */
    public T get(int index){
        if(index >= size){
            throw new java.lang.Error("ERROR: index out of range");
        }
        if(index < size/2) {
            Node p = sentinel.next;
            for (int i = 0; index > i; i += 1) {
                p = p.next;
            }
            return p.item;
        }
        else{
            Node p = sentinel.prev;
            int ind = (size-1) - index;
            for (int i = 0; i < ind; i +=1){
                p = p.prev;
            }
            return p.item;
        }
    }

    /** gets item through helper function. rev decides whether to index forward or backwards and is chosen based on which is faster
     * similar to the other get. the helper simpley loops to next until a recursive index/ negative counter reaches 0.
     * @param index
     * @return
     */

    public T getRecursive(int index){
        if(index >= size){
            throw new java.lang.Error("ERROR: index out of range");
        }
        Node k = sentinel;
        if (size/2 > index) {
            return helper(k, index, false);
        }
        else{
            return helper(k, size - 1 - index, true);
        }
    }

     private T helper(Node p, int i, boolean rev){
        if (i >= 0) {
            if (rev == false) {
                return helper(p.next, i - 1, rev);
            } 
            else {
                return helper(p.prev, i - 1, rev);
            }
        }
        else{
            return p.item;
        }
    }

}






