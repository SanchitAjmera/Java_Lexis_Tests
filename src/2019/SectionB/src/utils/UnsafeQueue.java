package utils;

import java.util.ArrayDeque;
import java.util.Optional;

public class UnsafeQueue<E> implements Queue<E> {

    private final ArrayDeque<E> internalQueue = new ArrayDeque<E>();

    @Override
    public void push(E element) {
        internalQueue.push(element);
    }

    @Override
    public Optional<E> pop() {
        return Optional.ofNullable(internalQueue.poll());
    }

    @Override
    public int size() {
        return internalQueue.size();

    }
}
