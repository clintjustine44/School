public class NodeList{
    private int count;
    private Node last;
    private Node head;
    
    public NodeList(){
        count = 0;
        last = null;
        head = null;
    }
    
    private boolean isEmpty(){
        return count == 0;
    }
    
    public boolean add(Node item){
        if(head == null){
            head = item;
            last = item;
        }else{
            last.next = item;
            last = item;
        }
        count++;
        return true;
    }

    public boolean delete(int location) {
        Node current = head;
        Node previous = null;
        int currentIndex = 1;

        // Ensure the location is valid
        if (location <= 0 || location > count) {
            System.out.println("Location is out of bounds.");
            return false;
        }
    
        // Case for deletion of head
        if (location == 1) {
            head = head.next;
            if (head == null) {
                last = null;
            }
            count--;
            return true;
        }
    
        while (current != null && currentIndex < location) {
            previous = current;
            current = current.next;
            currentIndex++;
        }
    
        if (current == null) {
            return false;
        }
    
        // Adjust pointers to remove the node
        previous.next = current.next;
    
        // Case for deletion of last.
        if (current == last) {
            last = previous;
        }
    
        count--;
        return true;
    }

    public String peek()
   {
      if(!isEmpty())
      {
         return head.value.toString();
      }
      return null;
   }
   
   public int getCount(){
      return count;
   }
    

    public void display() {
        if(!isEmpty()){
            Node temp = head;
            while(temp != null){
                if(temp.next != null)
                    System.out.print(temp.value + " -> ");
                else
                    System.out.print(temp.value);
                temp = temp.next;
            }System.out.println();
        }else
            System.out.println("List is currently empty.");
    }
}