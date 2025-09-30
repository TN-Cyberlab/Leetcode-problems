import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic circular queue (ring buffer) backed by an array.
 * Can be constructed as fixed-capacity or auto-resizing.
 *
 * @param <T> element type
 */
public class CircularQueue<T> implements Iterable<T> {
    private T[] data;
    private int head = 0;   // index of next element to dequeue / peek
    private int tail = 0;   // index to write next element (enqueue)
    private int size = 0;
    private final boolean autoResize;

    /**
     * Create a fixed-capacity circular queue.
     *
     * @param capacity positive capacity
     */
    public CircularQueue(int capacity) {
        this(capacity, false);
    }

    /**
     * Create a circular queue with optional auto-resizing.
     *
     * @param capacity   initial capacity (must be >= 1)
     * @param autoResize if true, queue will grow when full
     */
    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity, boolean autoResize) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be >= 1");
        this.data = (T[]) new Object[capacity];
        this.autoResize = autoResize;
    }

    public int capacity() {
        return data.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    /**
     * Enqueue an element.
     * If queue is full and autoResize == false, IllegalStateException is thrown.
     * If autoResize == true, internal array is grown (doubled).
     *
     * @param item element to add (may be null)
     */
    public void enqueue(T item) {
        if (isFull()) {
            if (autoResize) grow();
            else throw new IllegalStateException("Queue is full");
        }
        data[tail] = item;
        tail = (tail + 1) % data.length;
        size++;
    }

    /**
     * Dequeue and return the front element.
     *
     * @return removed front element
     * @throws NoSuchElementException if empty
     */
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        T item = data[head];
        data[head] = null; // help GC
        head = (head + 1) % data.length;
        size--;
        return item;
    }

    /**
     * Peek at the front element without removing.
     *
     * @return front element
     * @throws NoSuchElementException if empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return data[head];
    }

    /**
     * Clear the queue.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        // Null out references for GC
        for (int i = 0; i < size; i++) {
            data[(head + i) % data.length] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int newCap = Math.max(1, data.length * 2);
        T[] newData = (T[]) new Object[newCap];
        // copy from head.. wrap-around
        for (int i = 0; i < size; i++) {
            newData[i] = data[(head + i) % data.length];
        }
        data = newData;
        head = 0;
        tail = size;
    }

    /**
     * Iterator from front (head) to back (tail-1).
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int idx = 0;
            @Override
            public boolean hasNext() {
                return idx < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T val = data[(head + idx) % data.length];
                idx++;
                return val;
            }
        };
    }

    // Simple demo / smoke test
    public static void main(String[] args) {
        CircularQueue<Integer> q = new CircularQueue<>(4, true); // start cap 4, auto-resize
        System.out.println("enqueue 1..6");
        for (int i = 1; i <= 6; i++) q.enqueue(i);
        System.out.println("size: " + q.size() + ", capacity: " + q.capacity()); // size 6, capacity >= 6
        System.out.println("dequeue 2 elements:");
        System.out.println(q.dequeue()); // 1
        System.out.println(q.dequeue()); // 2
        System.out.println("enqueue 7,8");
        q.enqueue(7);
        q.enqueue(8);
        System.out.println("contents (front->back):");
        for (int v : q) System.out.print(v + " ");
        System.out.println("\npeek: " + q.peek()); // front element
    }
}
