public class StackList 
{
   private Node top;
   private int count;
   
   public StackList()
   {
      top = null;
      count = 0;
   }
   
   private boolean isEmpty()
   {
      //return top == null;
      return count == 0;
   }
   
   public boolean push(Object value)
   {
      Node node = new Node(value);
      node.next = top;
      top = node;
      count++;
      return true;
   }
   
   public boolean pop()
   {
      if(!isEmpty())
      {
         top = top.next;
         count--;
         return true;
      }
      return false;
   }
   
   public String peek()
   {
      if(!isEmpty())
      {
         return top.value.toString();
      }
      return null;
   }
   
   public int getCount()
   {
      return count;
   }
   
   public void display()
   {
      if(!isEmpty())
      {
         Node temporary = top;
         while(temporary != null)
         {
            System.out.println(temporary.value);
            temporary = temporary.next;
         }
         System.out.println();
      }
      else 
      {
         System.out.println("Stack is empty.");
      }
   }
}