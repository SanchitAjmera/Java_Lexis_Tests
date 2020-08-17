package utils;

import java.util.Optional;

public interface Queue<E> {

  void push(E element);

  Optional<E> pop();

  int size();
}
