package oy.tol.tra;

/**
 * This class instantiates a queue implementing the {@code QueueInterface}.
 *
 * @author Antti Juustila
 */
public class QueueFactory {

    private QueueFactory() {
    }

    /**
     * Creates an instance of QueueInterface for Integer type with the given capacity.
     *
     * @param capacity Number of elements the queue can hold.
     * @return The queue object.
     */
    public static QueueInterface<Integer> createIntegerQueue(int capacity) {
        // Instantiates a queue implementation using Integer as the template parameter
        // with the given capacity
        return new QueueImplementation<>(capacity);
    }

    /**
     * Creates an instance of QueueInterface for Integer type using the default capacity.
     *
     * @return The queue object.
     */
    public static QueueInterface<Integer> createIntegerQueue() {
        // Instantiates a queue implementation using Integer as the template parameter
        // with default capacity
        return new QueueImplementation<>();
    }

}