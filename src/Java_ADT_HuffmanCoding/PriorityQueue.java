import java.util.Iterator;

public class PriorityQueue<E extends Comparable<E>> implements PriorityQueueInterface<E>{

	private E[] items;    //a heap of HuffmanTrees
	private final static int max_size = 256;
	private int size;    //number of HuffManTrees in the heap.
	
	
	public PriorityQueue( ) {
        // constructor which creates an empty heap
		items = (E[]) new Comparable[max_size];
		size = 0;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int getSize(){
		return size;
	}

	@Override
	public PriorityQueue<E> clone() {
		return null;
	}

	public E getMin(){
		E root = null;
		if (!isEmpty()) root = items[0];
		return root;
	}
	
	public void add(E newEntry) throws PriorityQueueException{
		items[size] = newEntry;
		size++;
		heapRebuildUp(size - 1);
	}

	private void heapRebuildUp(int leaf) {
		// Rebuild heap to keep it ordered
		if (items[leaf].compareTo(items[(leaf - 1) / 2]) < 0) {
			swap(leaf, (leaf - 1) / 2);
			heapRebuildUp((leaf - 1) / 2);
		}
	}

	private void swap (int i, int j) {
		E element = items[i];
		items[i] = items[j];
		items[j] = element;
	}


	public E removeMin(){
	// post: Removes the minimum valued item from the PriorityQueue
		E root = null;
		if (!isEmpty()){
			root = items[0];
			items[0] = items[size-1];
			size--;
			heapRebuild(0);
		}
		return root;
	}

	private void heapRebuild(int root) {
		// Rebuild heap to keep it ordered
		if (2 * root + 1 < size) {
			int index = 2 * root + 1;
			if (2*root + 2 < size && items[2 * root + 2].compareTo(items[2 * root + 1]) < 0) {
				index = 2 * root + 2;
			}
			if (items[root].compareTo(items[index]) > 0) {
				swap(root, index);
				heapRebuild(index);
			}
		}
	}
}
