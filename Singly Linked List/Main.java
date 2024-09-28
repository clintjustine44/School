public class Main{
    public static void main(String[] args){
        StackList s1 = new StackList();

        s1.push("1");
        s1.push("2");
        s1.push("3");
        
        s1.display();

        s1.pop(); // Pops out 3
        System.out.println("Top: " + s1.peek()); // Top = 2
        s1.display();

        System.out.println("Head: " + s1.getHead()); // Head is 1
        s1.insertFirst("frst"); // 1 is replaced by frst
        System.out.println("Head: " + s1.getHead()); // Head is frst.
        s1.display();
    }
}
