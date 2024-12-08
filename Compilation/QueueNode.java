// enqueue, dequeue, peek, display

public class QueueNode {
    Node first, last;
    int count;

    public QueueNode(){
        first = null;
        count = 0;
    }

    private boolean isEmpty(){
        return count == 0;
    }

    public boolean enqueue(Object value){
        Node temp = new Node(value);
        if(isEmpty()){
            first = temp;
            last = temp;
        }else{
            last.next = temp;
            last = temp;
        }
        count++;
        return true;
    }

    public boolean dequeue(){
        if(!isEmpty()){
            if(first == last){
                first = null;
                last = null;
            }else{
                first = first.next;
            }
            count--;
            return true;
        }
        System.out.println("Queue is empty.");
        return false;
    }

    public Object peek(){
        return (!isEmpty()) ? first.value : null;
    }

    public void display(){
        Node tempNode = first;
        if(!isEmpty()){
            while(tempNode != null){
                if(tempNode.next != null)
                    System.out.print(tempNode.value + " -> ");
                else
                    System.out.print(tempNode.value);
                tempNode = tempNode.next;
            }System.out.println();
        }else
            System.out.println("Queue is empty.");
    }
}
