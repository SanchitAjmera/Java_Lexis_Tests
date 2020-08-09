import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Tests {

    private static int mismatches = 0;
    private static boolean exception = false;

    @Test
    public void testQuestion1() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0 ; i < 10; i ++){
            try {
                queue.add(i);
            } catch (PQException e) {
                e.printStackTrace();
            }
        }

        try {
            queue.add(-5);
        } catch (PQException e) {
            e.printStackTrace();
        }

        try {
            queue.add(15);
        } catch (PQException e) {
            e.printStackTrace();
        }


        queue.remove();



    }

}
