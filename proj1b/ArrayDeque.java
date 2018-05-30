public class ArrayDeque<T> implements Deque<T>{
    private T[] arr;
    private int size;
    private int first;

    /** modular arithmatic helper. useful for "looping index back around", though this can be slightly inefficient. */
    private int mod(int x, int mo){
        return( x % mo + mo ) % mo;
    }

    /** helper to return the "true" index of the last value in the list */
    private int last(){
        return mod(first+size - 1, arr.length);
    }

    /** constructor, has a pointer index "first" which refers to an index in arr. to keep track of where the list begins. */

    public ArrayDeque(){
        arr = (T[]) new Object[8];
        size = 0;
        first = 0;

    }


    /**helper that doubles/ halves the size under    similar conditions to those asked for in the description
     * copies all of our items (items between first and first + size -1), to the new array.
     */
    private void resize(){
        if(size > arr.length - 2) {
            T[] a = (T[]) new Object[arr.length * 2];
            for (int i = first; i < size + first; i += 1) {
                a[i] = arr[mod(i, arr.length)];
            }
            arr = a;
        }
        if((size*4 + 1 < arr.length) && (arr.length > 9)){
            T[] a = (T[]) new Object[arr.length / 2];
            for (int i = 0; i < size; i += 1) {
                a[i] = arr[ mod(i+first, arr.length)];
            }
            first = 0;
            arr = a;
        }
    }

    /** addFirst resizes if necesssary, then
     * finds the new "first" index using modular arithmatic then reassignes that index in our array arr to the
     * value x, then increases teh size by +1. addLast does the same except it doesn't remove first and instead reassignes
     * the index corresping to the value after last to x.
     * @param x
     */
    @Override
    public void addFirst(T x){
        this.resize();
        first = mod(first-1,arr.length);
        arr[first] = x;
        size += 1;
    }

    @Override
    public void addLast(T x){
        this.resize();
        arr[mod(first + size, arr.length)] = x;
        size += 1;
    }

    /** check whether a list is empty by referencing size. This is the only way to find if a list is empty given
     * the way i have structured the variables in my list.
     * @return
     */
    @Override
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /** returns the size instance variable */
    @Override
    public int size(){
        return size;
    }

    /** indexes over the values in the list and prints them one by one. */
    @Override
    public void printDeque(){
        for(int i = first; i < size + first; i += 1){
            System.out.print(arr[mod(i,arr.length)]);
            System.out.print(" ");
        }
    }

    @Override
    public T removeFirst(){
        if(this.isEmpty()){
            return null;
        }
        this.resize();
        if(this.isEmpty()){
            return null;
        }
        else {
            T h = arr[first];
            first = mod(first + 1, arr.length);
            size -= 1;
            return h;
        }
    }

    @Override
    public T removeLast(){
        if(this.isEmpty()){
            return null;
        }
        else {
            this.resize();
            T h = arr[this.last()];
            size -= 1;
            return h;
        }
    }

    @Override
    public T get(int x){
        return arr[mod(first + x , arr.length)];
    }


}