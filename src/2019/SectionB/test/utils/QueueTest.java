package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {

  Queue<Integer> queue;


  @Before
  public void setupConcreteImplementation() {
    queue = new SafeQueue<>();
  }


  @Test
  public void theQueueIsInitiallyEmpty() {
    assertEquals(0, queue.size());
    assertTrue(queue.pop().isEmpty());
  }

  @Test
  public void addingElementsIncreasesTheSize() {
    assertEquals(0, queue.size());
    queue.push(7);
    assertEquals(1, queue.size());
    queue.push(11);
    assertEquals(2, queue.size());
    queue.push(13);
    assertEquals(3, queue.size());
  }

  @Test
  public void removingElementsDecreasesTheSize() {
    assertEquals(0, queue.size());
    queue.push(7);
    queue.push(11);
    queue.push(13);
    assertEquals(3, queue.size());
    queue.pop();
    assertEquals(2, queue.size());
    queue.pop();
    assertEquals(1, queue.size());
    queue.pop();
    assertEquals(0, queue.size());
  }

  @Test
  public void theQueueDoesNotLoseElementsAndImplementsAFIFOPolicy() {
    assertEquals(0, queue.size());
    assertTrue(queue.pop().isEmpty());
    queue.push(1);
    assertEquals(Integer.valueOf(1), queue.pop().get());
    assertTrue(queue.pop().isEmpty());

    queue.push(7);
    queue.push(11);
    queue.push(13);

    assertEquals(Integer.valueOf(7), queue.pop().get());
    assertEquals(Integer.valueOf(11), queue.pop().get());
    assertEquals(Integer.valueOf(13), queue.pop().get());
    assertTrue(queue.pop().isEmpty());
  }

}