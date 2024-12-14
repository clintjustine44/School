public class StackArray {
    Object[] array;
    Object top;
    int count;

    public StackArray(int size) {
        array = new Object[size];
        count = 0;
        top = null;
    }

    // Default size
    public StackArray() {
        this(10);
    }

    private boolean isEmpty() {
        return count == 0;
    }

    private boolean isFull() {
        return count == array.length;
    }

    public boolean push(Object value) {
        if (!isFull()) {
            array[count++] = value;
            top = value;
            return true;
        } else {
            System.out.println("Stack is already full.");
            return false;
        }
    }

    public boolean pop() {
        if (!isEmpty()) {
            array[count - 1] = null;
            count--;
            top = isEmpty() ? null : array[count-1];
            return true;
        }
        System.out.println("Stack is empty.");  
        return false;
    }

    public Object peek() {
        return (!isEmpty()) ? top : null;
    }

    public int getCount(){
        return count;
    }

    public void display() {
        if (!isEmpty()) {
            for (int i = count - 1; i >= 0; i--) {
                System.out.println(array[i]);
            }System.out.println();
        } else
            System.out.println("Stack is empty.");
    }
}