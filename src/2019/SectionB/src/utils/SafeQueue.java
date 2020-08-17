package utils;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;

public class SafeQueue<E> implements Queue<E> {

  private final ArrayDeque<E> internalQueue =
          new ArrayDeque<>();

  @Override
  public synchronized void push(E element) {
    internalQueue.add(element);
  }

  @Override
  public synchronized Optional<E> pop() {
    return Optional.ofNullable(internalQueue.poll());
  }

  @Override
  public synchronized int size() {
    return internalQueue.size();
  }
}
