public class QueueArray {
    Object[] array;
    Object first, last;
    int count;

    public QueueArray(int size) {
        if(size <= 0)
            size = 5;
        array = new Object[size];
        count = 0;
        first = null;
        last = null;
    }

    // Default size
    public QueueArray() {
        this(5);
    }

    private boolean isEmpty() {
        return count == 0;
    }

    private boolean isFull() {
        return count == array.length;
    }

    public boolean enqueue(Object value) {
        if(isEmpty()){
            first = value;
            last = value;
            array[count++] = value;
            return true;
        }
        if (!isFull()) {
            array[count++] = value;
            last = value;
            return true;
        } else {
            System.out.println("Queue is already full.");
            return false;
        }
    }

    public boolean dequeue() {
        if (!isEmpty()) {
            for (int i = 0; i <= count; i++) {
                array[i] = array[i + 1];
            }
            array[count - 1] = null;
            count--;
            first = isEmpty() ? null : array[0];
            last = isEmpty() ? null : array[count-1];
            return true;
        }
        System.out.println("Queue is empty.");
        return false;
    }

    public Object peek() {
        return (!isEmpty()) ? first : null;
    }

    public int getCount(){
        return count;
    }

    public void display() {
        if (!isEmpty()) {
            for (int i = 0; i < count; i++) {
                if(i + 1 != count)
                    System.out.print(array[i] + " -> ");
                else
                    System.out.print(array[i]);
            }System.out.println();
        } else
            System.out.println("Queue is empty.");
    }
}