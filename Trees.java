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

		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("Val: ");
			sb.append(this.data);
			sb.append(" // Left: ");
			sb.append(this.left == null?
						"null":
						this.left.data);
			sb.append(" // Right: ");
			sb.append(this.right == null?
						"null":
						this.right.data);
			sb.append("\n");
			return sb.toString();
		}
	}

	/* static nodes for testing */
	static Node[] nodes = new Node[15];

	/* balance nodes array */
	private static void balance(){
		for (int i=nodes.length-1; i>=0; i--){
			nodes[i] = new Node(i);
			// indexes of children
			int l_child = 1 + i*2;
			int r_child = l_child + 1;
			if (l_child < nodes.length){
				nodes[i].left = nodes[l_child];
			} else {
				nodes[i].left = null;
			}
			if (r_child < nodes.length){
				nodes[i].right = nodes[r_child];
			} else {
				nodes[i].right = null;
			}
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
		System.out.println(title);
		System.out.println("\t Recursive: " + recursive);
		System.out.println("\t Iterative: " + iterative);
		System.out.println("\t Match: " + recursive.equals(iterative));
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
	public static boolean bfs(Node root, int goal){
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty()){
			Node current = q.removeFirst();
			if (current != null){
				if (current.data == goal){
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

	/* dfs */
	private static boolean dfs(Node root, int goal){
		return dfs(root, goal, false);
	}
	private static boolean dfs(Node root, int goal, boolean iterative){
		// check root node
		if (root == null){
			return false;
		}
		if (iterative){
			return dfs_iterative(root, goal);
		} else {
			return dfs_recursive(root, goal);
		}
	}
	private static boolean dfs_iterative(Node root, int goal){
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			root = stack.pop();
			if (root == null){
				continue;
			} else if (root.data == goal){
				return true;
			} else {
				stack.push(root.left);
				stack.push(root.right);
			}
		}
		return false;
	}
	private static boolean dfs_recursive(Node root, int goal){
		if (root == null) {
			return false;
		} else if (root.data == goal){
			return true;
		}else {
			return (dfs_recursive(root.left, goal) || dfs_recursive(root.right, goal));
		}
	}

	/* printing helper */
	public static void print(String str){
		System.out.println(str);
	}
	public static void print(Object obj){
		print("" + obj);
	}

	/* Main */
	public static void main(String[] args) {
		balance();
		// testPreorder(nodes[0]);
		print(dfs(nodes[0], 12, true));
		// testInorder(A);
		// testPostorder(A);
		// testBalanced(A);

		// System.out.println(balanced(A));

	}
}