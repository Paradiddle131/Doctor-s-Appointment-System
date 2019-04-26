
package appointment;

public class LinkedList <AnyType extends Patient>
        
{  private static class Node<AnyType>
    {
        public AnyType data;
        public Node<AnyType> next;
        public Node(AnyType d, Node<AnyType> n)
        {
            data = d;
            next = n;
        }
    }
private int listsize = 0;
private Node<AnyType> head;
private Node<AnyType> tail;

    public LinkedList() 
    {
        makeEmpty();
    }
	
    public void makeEmpty()
    {
        head = new Node<>(null,null);
        tail = new Node<>(null,null);
        
        head.next = tail;
        listsize = 0;
    }
    
    public AnyType findID(int id){
        if (isEmpty()) 
            return null;
        else
        {
            Node<AnyType> current;
            current = head.next;
            while (current != tail) {
                if (current.data.getID() == id) {
                    return current.data;
                    
                }current = current.next;
            }
        }return null;
    }

     public boolean insertAtFront(AnyType x)
    {
        //if the ID already exists, do not add it again and return false
        if(findID(x.getID()) != null)
            return false;
        else
        {
            //creates a new node to add to the list
            Node<AnyType> newNode = new Node<>(x, null);
            //links the previous first item to the new node
            newNode.next = head.next;
            //links head to the new node
            head.next = newNode;
            listsize++;
            return true;
        }
    }
    
   
    
    //deletes object corresponding to ID
    public AnyType delete(int ID)
    {
        //checks to see if it is empty
         if(isEmpty())
            return null;
         else
         {
            //creates a new AnyType for the deleted data and a node to traverse the list
            AnyType deleted;
            Node<AnyType> current;
            current = head;
            while(current != tail)
            {
                //checks to see if the next node matches the ID
                if(current.next.data.getID() == ID)
                {
                    //stores the next node's data and links the current to 2 ahead
                    deleted = current.next.data;
                    current.next = current.next.next;
                    listsize--;
                    return deleted;
                }
                current = current.next;
            }
         }
         return null;
    }
    
    //prints all records in the list
    public void printAllRecords()
    {
        //start at head
        Node<AnyType> current;
        current = head.next;
        while(current != tail)
        {
            current.data.printID();
            System.out.println(); //newline for cleaner formatting
            current = current.next;
        }
        
    }
    
    
    
    public boolean isEmpty()
    {
        return listsize == 0;
    }

}
