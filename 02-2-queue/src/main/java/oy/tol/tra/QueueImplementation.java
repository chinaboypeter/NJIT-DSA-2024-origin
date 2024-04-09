package oy.tol.tra;

import java.util.Arrays;

@SuppressWarnings("unused")
public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] array;
    private int size = 0;
    private int capacity;
    private int head = 0;
    private int tail = -1;

    public QueueImplementation(int capacity) throws QueueAllocationException{
        if(capacity < 1){
            throw new QueueAllocationException("can not be null");
        }
        try{
            array = new Object[capacity];
        }catch(OutOfMemoryError e){
            throw new QueueAllocationException("Failed room");
        }
        this.capacity = capacity;
    }
    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for(int i = 0; i <size; i++){
            array[i] = null;
        }
        size = 0;
        head = 0;
        tail = -1;
    }


    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException{
        if(element == null){
            throw new NullPointerException("can not be null");
        }
        if (size >= capacity) {
            int newCapacity = capacity * 2;
            Object[] newArray;
            try{
                newArray = new Object[newCapacity];
                for(int i = 0; i < size; i++){
                    newArray[i] = array[(head + 1) % capacity];
                }
                array = newArray;
                capacity = newCapacity;
                head = 0;
                tail = size - 1;
            }catch(Exception e){
                throw new QueueAllocationException("Cannot allocate room");
            }
        }
        array[size++] = element;

    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E element = (E) array[head];
        array[head] = null;
        head = (head + 1) % capacity;
        size--;
        return element;

    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (isEmpty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return (E) array[0];
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
