package Canvas;

public class Queue<Item>
{
    private Node first = null;
    private Node last = null;
    private int N = 0;
    private class Node
    {
        private Item item;
        private Node next;
        Node() {}
        Node (Item item) {
            this.item = item;
        }
    }
    public boolean isEmpty()
    {
        if (N == 0)
            return true;
        else 
            return false;
    }
    public int size()
    {
        return N;
    }
    public Item dequeue()
    {
        Item temp =  first.item;
        first = first.next;
        N--;
        return temp;
    }
    public void enqueue(Item item)
    {
        if (N == 0)
        {
            first = new Node();
            first.item = item;
            last = first;
        }
        else
        {   
            last.next = new Node(item); 
            last = last.next;
        }
        
        N++;
    }
}
