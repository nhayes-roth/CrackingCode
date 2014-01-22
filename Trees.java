import java.util.*;

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

	/* Preorder (root, left, right) */
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
		Node last_visited = null;
		Stack<Node> stack = new Stack<Node>();
		while (!stack.isEmpty() || root != null){
			// haven't reached a leaf or left-bound
			if (root != null){
				// push node and move left
				stack.push(root);
				root = root.left;
			} else {
				// if a right branch exists and we're coming from the left
				if (stack.peek().right != null && last_visited != stack.peek().right){
					// move right
					root = stack.peek().right;
				} else{
					// move up
					root = stack.pop();
					result += visit(root);
					last_visited = root;
				}
			}
		}
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

	/*
	 * Determines whether a tree is roughly balanced.
	 * - no two leaf nodes differ in distance from the root by more than one
	 * that is: return (maxDepth(A) - minDepth(A) <= 1)
	 */
	public static boolean balanced(Node root){
		int max = maxDepth(root);
		int min = minDepth(root);
		System.out.println("max: " + max);
		System.out.println("min: " + min);
		return (maxDepth(root) - minDepth(root) <= 1);
	}
	public static int maxDepth(Node root){
		return (root == null)?
			0:
			1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}
	public static int minDepth(Node root){
		return (root == null)?
			0:
			1 + Math.min(minDepth(root.left), minDepth(root.right));
	}

	/* bfs */
	public static boolean bfs(Node root, Node goal){
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty()){
			Node current = q.removeFirst();
			if (current != null){
				if (current == goal){
					return true;
				}
				else {
					q.add(current.left);
					q.add(current.right);
				}
			}
		}
		return false;
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

		// testPreorder(A);
		// testInorder(A);
		// testPostorder(A);

		// System.out.println(balanced(A));

		System.out.println(bfs(A, G));
	}
}