package utils;

import java.util.ArrayDeque;
import java.util.Optional;

public class UnsafeQueue<E> implements Queue<E> {

  private final ArrayDeque<E> internalQueue = new ArrayDeque<>();

  @Override
  public void push(E element) {
    internalQueue.push(element);
  }

  @Override
  public Optional<E> pop() {
    return Optional.ofNullable(internalQueue.pollLast());
  }

  @Override
  public int size() {
    return internalQueue.size();
  }
}
