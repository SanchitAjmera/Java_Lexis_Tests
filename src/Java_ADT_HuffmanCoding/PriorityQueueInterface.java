public interface PriorityQueueInterface<E extends Comparable<E>> {

	public void add(E newEntry) throws PriorityQueueException;
	//  post: Adds a new entry to the priority queue according to 
        // the priority value (i.e.its frequency value).

	public E getMin();
	// post: Returns the object E with the highest priority (i.e. lowest
	// frequency value) or returns null if the priority queue is empty

	public E removeMin();
	// post: Removes and returns the object E with the highest 
        // priority (i.e. lowest frequency value) or returns null 
        // if the priority queue is empty

	public boolean isEmpty();
	// post: Returns true if the queue is empty. False otherwise.

	public int getSize();
	// post: returns the size of the priority queue.

}
