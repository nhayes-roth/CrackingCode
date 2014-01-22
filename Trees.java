import java.util.Stack;

class Trees {

	/* Node class */
	static class Node {
		public int data;
		public Node left;
		public Node right;

		public Node (int i){
			this.data = i;
		}
	}

	/* Preorder */
	public static void testPreorder(Node root){
		String r = recursivePreorder(root);
		String i = iterativePreorder(root);
		compare("Preorder Traversal", r,i);
	}

	public static String recursivePreorder(Node root){
		if (root != null){
			String result 	= visit(root);
			String[] left_right = {recursivePreorder(root.left),
								   recursivePreorder(root.right)};
			for (String s : left_right){
				if (s != null){
					result += s;
				}
			}
			return result;
		} else {
			return null;
		}
	}

	public static String iterativePreorder(Node root){
		Stack<Node> stack = new Stack<Node>();
		String result = "";
		stack.push(root);
		while(!stack.isEmpty()){
			root = stack.pop();
			if (root != null){
				result += visit(root);
				stack.push(root.right);
				stack.push(root.left);
			}
		}
		return result;
	}

	/* Inorder */

	public static void recursiveInorder(){

	}

	/* Helper functions */
	public static String visit(Node n){
		return Integer.toString(n.data);
	}

	public static void compare(String title, String recursive, String iterative){
		System.out.println(title);
		System.out.println("Recursive: " + recursive);
		System.out.println("Iterative: " + iterative);
		System.out.println("Match: " + recursive.equals(iterative));
	}

	/* Main */
	public static void main(String[] args) {
		Node A = new Node(1);
		Node B = new Node(2);
		Node C = new Node(3);
		Node D = new Node(4);
		Node E = new Node(5);
		Node F = new Node(6);
		Node G = new Node(7);

		A.left = B;
		A.right = C;
		B.left = D;
		B.right = E;
		C.left = F;
		C.right = G;

		testPreorder(A);

	}
}