public class BinaryTree<K extends Comparable<K>> implements BinaryTreeInterface<K> {

	private class TreeNode {
        // Inner class TreeNode

		private K data;
		private TreeNode left;
		private TreeNode right;

		private TreeNode(K newData) {
			data = newData;
			left = right = null;
		}

		private TreeNode(K newData, TreeNode leftNode, TreeNode rightNode) {
			data = newData;
			left = leftNode;
			right = rightNode;
		}
	}

	private TreeNode root;

	public BinaryTree() {
        // Default constructor
		root = null;
	}

	public BinaryTree(K rootData) {
	// Constructor
		root = new TreeNode(rootData);
	}

	public BinaryTree(K rootData, BinaryTree<K> leftTree, BinaryTree<K> rightTree) {
	// Constructor
		root = new TreeNode(rootData, leftTree.root, rightTree.root);
	}

	protected BinaryTree(TreeNode newRoot) {
	// Constructor
		root = newRoot;
	}
	
	public boolean isEmpty() {
		return root == null;
	}

	public K getRootData() {
		if (!isEmpty())
			return root.data;
		return null;
	}

	public BinaryTree<K> getLeftSubtree() {
		if (root != null && root.left != null) {
			BinaryTree<K> temp = new BinaryTree<K>(root.left);
			return temp;
		} else
			return null;
	}

	public BinaryTree<K> getRightSubtree() {
		if (root != null && root.right != null) {
			BinaryTree<K> temp = new BinaryTree<K>(root.right);
			return temp;
		} else
			return null;
	}

	public int compareTo(BinaryTreeInterface<K> tree) {
		return (root.data).compareTo(tree.getRootData());
	}


}
