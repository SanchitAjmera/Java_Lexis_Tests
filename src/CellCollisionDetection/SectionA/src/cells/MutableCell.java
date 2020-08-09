package cells;

public class MutableCell<T> implements Cell<T> {

    private T value;

    public MutableCell() {
        this.value = null;
    }

    public MutableCell(T value) {
        if (value == null){
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    @java.lang.Override
    public void set(T value) {
        if (value == null){
            throw new IllegalArgumentException("argument is null");
        }
        this.value = value;
    }

    @java.lang.Override
    public T get() {
        return this.value;
    }

    @java.lang.Override
    public boolean isSet() {
        return (value != null);
    }
}
