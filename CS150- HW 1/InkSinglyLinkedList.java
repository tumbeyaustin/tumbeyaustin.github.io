
	import java.util.NoSuchElementException;
	
	public class IntSinglyLinkedList { 
	    // Inner Node class
	    private static class Node {
	        int data;
	        Node next;
	       
	        Node(int data) {
	            this.data = data;
	            this.next = null;
	        } 
	    }
	    
	    // Instance variables
	    private Node head;
	    private int count;
	    
	    // Constructor
	    public IntSinglyLinkedList() {
	        this.head = null;
	        this.count = 0;
	    }
	    
	    // Add element at the beginning
	    public void addFirst(int data) {
	        Node newNode = new Node(data);
	        newNode.next = head;
	        head = newNode;
	        count++;
	    }
	    
	    // Add element at the end
	    public void addLast(int data) {
	        Node newNode = new Node(data);
	        
	        if (isEmpty()) {
	            head = newNode;
	        } else {
	            Node current = head;
	            while (current.next != null) {
	                current = current.next;
	            }
	            current.next = newNode;
	        }
	        count++;
	    }
	    
	    // Retrieve element at specified index
	    public int get(int index) {
	        validateIndex(index);
	        
	        Node current = head;
	        for (int i = 0; i < index; i++) {
	            current = current.next;
	        }
	        return current.data;
	    }
	    
	    // Remove and return first element
	    public int removeFirst() {
	        if (isEmpty()) {
	            throw new NoSuchElementException("Cannot remove from empty list");
	        }
	        
	        int removedData = head.data;
	        head = head.next;
	        count--;
	        return removedData;
	    }
	    
	    // Remove first occurrence of specified value
	    public boolean removeValue(int data) {
	        if (isEmpty()) {
	            return false;
	        }
	        
	        // Check if head contains the value
	        if (head.data == data) {
	            head = head.next;
	            count--;
	            return true;
	        }
	        
	        // Search through rest of list
	        Node current = head;
	        while (current.next != null) {
	            if (current.next.data == data) {
	                current.next = current.next.next;
	                count--;
	                return true;
	            }
	            current = current.next;
	        }
	        return false;
	    }
	    
	    // Return number of elements
	    public int size() {
	        return count;
	    }
	    
	    // Check if list is empty
	    public boolean isEmpty() {
	        return count == 0;
	    }
	    
	    // Validate index bounds
	    private void validateIndex(int index) {
	        if (index < 0 || index >= count) {
	            throw new IndexOutOfBoundsException("Invalid index: " + index + ", size: " + count);
	        }
	    }
	    
	    @Override
	    public String toString() {
	        if (isEmpty()) {
	            return "[]";
	        }
	        
	        StringBuilder result = new StringBuilder("[");
	        Node current = head;
	        
	        while (current != null) {
	            result.append(current.data);
	            if (current.next != null) {
	                result.append(", ");
	            }
	            current = current.next;
	        }
	        result.append("]");
	        return result.toString();
	    }
	}