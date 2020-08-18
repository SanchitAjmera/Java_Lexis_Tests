package cells;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BackedUpMutableCell<T> extends MutableCell<T> implements BackedUpCell<T>{

    private boolean unbounded;
    private T value;
    private Deque<T> values;
    private int limit;

    public BackedUpMutableCell(){
        this.value = null;
        this.values = new ArrayDeque<>();
        this.unbounded = true;
        this.limit = 100000000;
    }

    public BackedUpMutableCell(int limit){
        this.limit = limit;
        this.value = null;
        this.values = new LinkedList<>();
        this.unbounded = false;
    }

    @Override
    public boolean hasBackup() {
        return !(this.values.isEmpty());
    }

    @Override
    public void revertToPrevious() {
        if (!this.hasBackup()){
            throw new UnsupportedOperationException("backed up of values is " +
                    "empty");

        }
        T previous = this.values.pollLast();
        this.value = previous;
    }

    @Override
    public void set(T value) {
        if (this.values.size() == this.limit && !this.unbounded && this.hasBackup()){
            this.values.remove();
        }
        if (this.value != null && this.values.size() < this.limit){
            this.values.add(this.value);
        }
        this.value = value;

    }

    @Override
    public boolean isSet() {
        return (this.get() != null);
    }

    @Override
    public T get() {
        return this.value;
    }
}
