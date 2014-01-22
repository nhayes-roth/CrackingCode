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

	/* Preorder (visit the root, left branch, right branch, repeat) */
	public static void testPreorder(Node root){
		String r = recursivePreorder(root);
		String i = iterativePreorder(root);
		compare("Preorder Traversal", r,i);
	}

	public static String recursivePreorder(Node root){
		String result = "";
		if (root != null){
			result += visit(root);
			result += recursivePreorder(root.left);
			result += recursivePreorder(root.right);
		}
		return result;
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

	/* Inorder (left, root, right) */
	public static void testInorder(Node root){
		String r = recursiveInorder(root);
		String i = iterativeInorder(root);
		compare("Inorder Traversal", r,i);
	}

	public static String recursiveInorder(Node root){
		String result = "";
		if (root != null){
			result += recursiveInorder(root.left);
			result += visit(root);
			result += recursiveInorder(root.right);
		}
		return result;
	}

	public static String iterativeInorder(Node root){
		String result = "";
		Stack<Node> stack = new Stack<Node>();
		while(!stack.isEmpty() || root != null){
			if (root != null){
				stack.push(root);
				root = root.left;
			} else {
				root = stack.pop();
				result += visit(root);
				root = root.right;
			}
		}
		return result;
	}

	/* Postorder (left, right, root) */
	public static void testPostorder(Node root){
		String r = recursivePostorder(root);
		String i = iterativePostorder(root);
		compare("Postorder Traversal", r,i);
	}

	public static String recursivePostorder(Node root){
		String result = "";
		if (root != null){
			result += recursivePostorder(root.left);
			result += recursivePostorder(root.right);
			result += visit(root);
		}
		return result;
	}

	public static String iterativePostorder(Node root){
		String result = "";
		return result;
	}

	/* Helper functions */
	public static String visit(Node n){
		return Integer.toString(n.data);
	}

	public static void compare(String title, String recursive, String iterative){
		System.out.println("\t" + title);
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
		testInorder(A);
		testPostorder(A);
	}
}