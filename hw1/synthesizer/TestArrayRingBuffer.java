package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        int i = 0;
        ArrayRingBuffer arb = new ArrayRingBuffer(3);



        arb.enqueue(1);
        arb.enqueue(2);
        assertTrue((int)arb.dequeue() == 1);
        assertTrue((int)arb.dequeue() == 2);

        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertTrue(arb.isFull());
        try{
            arb.enqueue(3);
        }
        catch(RuntimeException e){
            i+=1;
        }

        assertTrue((int)arb.dequeue() == 1);
        assertTrue((int)arb.dequeue() == 2);
        assertTrue((int)arb.dequeue() == 3);
        assertTrue(arb.isEmpty());
        try{
            arb.dequeue();
        }
        catch(RuntimeException e){
            i+=1;
        }

        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);

        for (Object s : arb) {
            System.out.println(s);
        }

        assertTrue(i==2);
        System.out.print("");


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
