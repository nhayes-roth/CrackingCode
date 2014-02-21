import java.util.*;

class LinkedList {

	// Node class
	class Node {
		int data;
		Node next;

		public Node(){
			data = 0;
			next = null;
		}
		public Node(int val){
			data = val;
			next = null;
		}
		public Node(int val, Node n){
			data = val;
			next = n;
		}
		public String toString(){
			return this.data + " ";
		}
	}

	Node head;
	public LinkedList(Node n){
		this.head = n;
	}

	// 2.1
	// write code to remove duplicates from an unsorted linked list
	// followup: what if no buffer is allowed?
	private void removeDuplicates(boolean buffer){
		Node root = this.head;
		if (buffer){
			HashSet<Integer> table = new HashSet<Integer>();
			table.add(root.data);
			Node next = root.next;
			while (next != null){
				if (table.contains(next.data)){
					// remove it
					root.next = next.next;
				} else {
					// add data to the table
					root = root.next;
					next = next.next;
				}
			}
		} else {
			// TODO
		}
	}
	// 2.2
	// find the nth to last element of a singly linked list

	// 2.3
	// implement an algorithm to delete a node in the middle of a
	// singly linked list, given only access to that node

	// 2.4
	// you are given 2 numbers as 2 linked lists, where each node
	// contains a single digit (least to most significant)
	// 	write a function that adds the numbers and 
		// returns the sum as a linked list

	// 2.5
	// given a circular linked list, implement an algorithm 
	// that returns a node at the beginning of the loop

	private void appendToTail(Node tail){
		Node n = this.head;
		if (n == null){
			this.head = tail;
			return;
		} else {
			while(n.next != null){
				n = n.next;
			}
			n.next = tail;
		}
	}

	@Override
	public String toString(){
		Node root = this.head;
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while(root != null && 100 > count++){
			sb.append(root.data);
			sb.append(" ");
		}
		return sb.toString();
	}

	private void print(String str){
		System.out.println(str);
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList(new Node(0));
		ll.appendToTail(new Node(1));
		ll.appendToTail(new Node(2));
		ll.appendToTail(new Node(4));
		ll.appendToTail(new Node(2));
		ll.appendToTail(new Node(1));
		ll.appendToTail(new Node(3));
		ll.appendToTail(new Node(0));
		ll.appendToTail(new Node(5));
		ll.removeDuplicates();
		print(ll.toString());
	}
}