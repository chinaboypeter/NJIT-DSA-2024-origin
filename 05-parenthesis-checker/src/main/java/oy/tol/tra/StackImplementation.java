package oy.tol.tra;

public class StackImplementation<E> implements StackInterface<E> {

    private Object[] itemArray;
    private int capacity;
    private int currentIndex = -1;
    private static final int DEFAULT_STACK_SIZE = 10;
    private static final int MIN_CAPACITY = 2;

    public StackImplementation() throws StackAllocationException {
        this(DEFAULT_STACK_SIZE);
    }

    public StackImplementation(int capacity) throws StackAllocationException {
        if (capacity < MIN_CAPACITY) {
            throw new StackAllocationException("Capacity must be at least " + MIN_CAPACITY + ".");
        }

        try {
            itemArray = new Object[capacity];
            this.capacity = capacity;
        } catch (Exception e) {
            throw new StackAllocationException("Failed to allocate stack memory.");
        }
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {
        if (currentIndex + 1 >= capacity) {
            resizeStack();
        }

        if (element == null) {
            throw new NullPointerException("Cannot push null element onto the stack.");
        }

        currentIndex++;
        itemArray[currentIndex] = element;
    }

    private void resizeStack() throws StackAllocationException {
        int newCapacity = capacity * 2;
        try {
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(itemArray, 0, newArray, 0, capacity);
            itemArray = newArray;
            capacity = newCapacity;
        } catch (Exception e) {
            throw new StackAllocationException("Failed to reallocate stack memory.");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Cannot pop from an empty stack.");
        }

        E poppedElement = (E) itemArray[currentIndex];
        currentIndex--;

        return poppedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Cannot peek into an empty stack.");
        }

        return (E) itemArray[currentIndex];
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public void clear() {
        currentIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int index = 0; index <= currentIndex; index++) {
            builder.append(itemArray[index]);
            if (index < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}