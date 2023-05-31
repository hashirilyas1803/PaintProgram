package Canvas;

import java.io.Serializable;

public class Stack<Item> implements Serializable
{
    private class Node implements Serializable
    {
        private Item item;
        private Node next;
    }
    private Node first = null;
    private int N = 0;
    public boolean isEmpty() 
    { return first == null; } 
    public void push(Item item) 
    { 
        Node second = first; 
        first = new Node(); 
        first.item = item;
        first.next = second; 
        N++; 
    } 
    public Item pop() 
    { 
        Item item = first.item; 
        first = first.next; 
        N--; 
        return item; 
    } 
    public int size() 
    { return N; }
}