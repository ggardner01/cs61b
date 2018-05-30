// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int mod(int x, int mo){
        return( x % mo + mo ) % mo;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        last = 0;
        first = 0;
        this.capacity = capacity;
        fillCount = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == capacity){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = mod(last + 1, capacity);
        fillCount = fillCount + 1;
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        T it =  peek();
        first = mod(first + 1,capacity);
        fillCount += -1;
        return it;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (fillCount == 0){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }

    @Override
    public Iterator<T> iterator(){
        return new myiter();
    }

    private class myiter<T> implements Iterator<T>{
        private int i;
        private int counter;
        public myiter(){
            i = first;
            counter = 0;
            }
            public boolean hasNext(){ ;
                return (counter < fillCount);
            }
            public T next(){
                 T retit = (T)rb[i];
                 counter += 1;
                 i = mod(i+1,rb.length);
                 return retit;
            }
    }
    // TODO: When you get to part 5, implement the needed code to support iteration.
}
