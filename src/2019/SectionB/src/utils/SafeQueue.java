package utils;

import java.util.Optional;

public class SafeQueue<E> implements Queue<E> {

  private final Queue<E> internalQueue = new UnsafeQueue<>();

  @Override
  public synchronized void push(E element) {
    internalQueue.push(element);
  }

  @Override
  public synchronized Optional<E> pop() {
    return internalQueue.pop();
  }

  @Override
  public synchronized int size() {
    return internalQueue.size();
  }
}
