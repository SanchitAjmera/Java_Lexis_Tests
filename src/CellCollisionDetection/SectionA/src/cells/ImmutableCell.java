package cells;

public class ImmutableCell<T> implements Cell<T> {
    private final T value;

    public ImmutableCell(T value) {
        if (value == null){
            throw new IllegalArgumentException();
        }
        this.value = value;
    }


    @Override
    public void set(T value) {
        throw new UnsupportedOperationException("cannot set value of " +
                "immutable cell");
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public boolean isSet() {
        return (this.value != null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ImmutableCell){
            ImmutableCell cell = (ImmutableCell) obj;
            return cell.get().equals(this.get());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
