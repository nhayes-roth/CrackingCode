class LinkedList {
	class Node {
		int data;
		Node next;

		public Node(){
			data = 0;
			next = NULL;
		}
		public Node(int val){
			data = val;
			next = NULL;
		}
		public Node(int val, Node n){
			data = val;
			next = n;
		}
	}
}