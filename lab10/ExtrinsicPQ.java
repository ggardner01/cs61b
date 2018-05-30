/**
 * Priority queue where objects have no intrinsic priority. Instead,
 * priorities are supplied as an argument during insertion and can be
 * changed.
 */
public interface ExtrinsicPQ<T> {
    /* Inserts an item with the given priority value. This is also known as "enqueue", or "offer". */
    public void insert(T item, double priority);
    /* Returns the minimum item. Also known as "min". */
    public T peek();
    /* Removes and returns the minimum item. Also known as "dequeue". */
    public T removeMin();
    /* Changes the priority of the given item. The behavior if the item doesn't exist is undefined. */
    public void changePriority(T item, double priority);
    /* Returns the number of items in the PQ. */
    public int size();
<<<<<<< HEAD

=======
>>>>>>> 62033e77c794aa23f32ca034f1de68dbe507b4a7
}
