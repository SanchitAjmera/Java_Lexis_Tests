
public interface BinaryTreeInterface<K extends Comparable<K>> extends Comparable<BinaryTreeInterface<K>> {

	public boolean isEmpty();
	// return true if BinaryTree is empty otherwise return false

	public K getRootData();
	// return item stored in root

	public BinaryTreeInterface<K> getLeftSubtree();
	// return left subtree

	public BinaryTreeInterface<K> getRightSubtree();
	// return right subtree

}
