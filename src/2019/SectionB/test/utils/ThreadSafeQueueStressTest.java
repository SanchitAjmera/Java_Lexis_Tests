package utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.Test;

public class ThreadSafeQueueStressTest {

  private static final int NUM_THREADS = 10;
  private static final int NUM_ELEMENTS_TO_ADD_AND_REMOVE = 1000_000;


  @Test
  public void minimalStressTest() {
    final Queue<Integer> safeQueueToTest = new SafeQueue<>();

    Thread threads[] = new Thread[NUM_THREADS];

    Arrays.setAll(threads, i -> new Thread(() -> {
      Random random = new Random(i);
      random.ints().limit(NUM_ELEMENTS_TO_ADD_AND_REMOVE).forEach(n -> {
        safeQueueToTest.push(n);
        safeQueueToTest.pop();
      });
    }));

    Arrays.stream(threads).forEach(t -> t.start());

    Arrays.stream(threads).forEach(t -> {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    assertEquals(0, safeQueueToTest.size());
  }

}
