public class StackList{
    private StackNode head;
    private StackNode top;
    private int count = 0;

    public boolean push(String data){
        if(!isEmpty()){
            StackNode newNode = new StackNode(data);
            newNode.tail = top;
            top = newNode;
        }else{
            head = new StackNode(data);
            head.tail = top;
            top = head;
        }

        count++;
        return true;
    }

    private boolean isEmpty(){
        return count == 0;
    }

    public boolean pop(){
        if(!isEmpty()){
            top = top.tail;
            count--;
            return true;
        }
        
        return false;
    }

    public String peek(){
        if(!isEmpty())
            return top.data;
        return null;
    }

    public int getCount(){
        return count;
    }

    public String getHead(){
        return head.data;
    }

    public boolean insertFirst(String data){
        StackNode newNode = new StackNode(data);
        newNode.tail = head;
        head = newNode;
        count++;
        return true;
    }

    public void display(){
        if(!isEmpty()){
            StackNode temp = top;
            while(temp != null && temp != head){
                System.out.println(temp.data);
                temp = temp.tail;
            }
            System.out.println(head.data);
            System.out.println();
        }else
            System.out.println("Stack is empty...");
    }
    
    private class StackNode{
        public String data;
        public StackNode tail;
    
        public StackNode(String data){
            this.data = data;
            tail = null;
        }
    }
}