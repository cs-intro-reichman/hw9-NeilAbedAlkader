/**
 * Represents a list of Nodes. 
 */
public class LinkedList {

	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list

	/**
	 * Constructs a new list.
	 */
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}

	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */
	public Node getLast() {
		return this.last;
	}

	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Gets the node located at the given index in this list.
	 *
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */
	public Node getNode(int index) {
		indexValidityCheck(index);
		ListIterator iterator = iterator();
		for (int i = 0; i < index; i++) {
			iterator.next();
		}
		return iterator.current;
	}

	/**
	 * Creates a new Node object that points to the given memory block,
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last
	 * node in this list.
	 * <p>
	 * The method implementation is optimized, as follows: if the given
	 * index is either 0 or the list's size, the addition time is O(1).
	 *
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		indexValidityCheck(index);
		
		if (index== 0) 
			addFirst(block);
		
		else {
			if (index ==size) {
				addLast(block);
			} else {
				Node current = new Node(block);
				Node previous = getNode(index-1);
				Node next = previous.next;
				previous.next = current;
				current.next = next;
				size++;
			}
		}
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 *
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node current = new Node(block);
		if (size == 0) {
			last = current;
			first = current;
		}
		else {
			last.next = current;
			last = current;
		}
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the beginning of this list (the node will become the list's first element).
	 *
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node current = new Node(block);
		if (size == 0) {
			first = current;
			last = current;
		}
		else {
			current.next = first;
			first = current;
		}
		size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 *
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		indexValidityCheck(index, true);
		return getNode(index).block;
	}

	/**
	 * Gets the index of the node pointing to the given memory block.
	 *
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		ListIterator iterator = iterator();
		for (int i = 0; i < size; i++) {
			if (iterator.current.block.equals(block)) {
				return i;
			}
			iterator.next();
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.
	 *
	 * @param nd
	 *        the node that will be removed from this list
	 */
	public void remove(Node nd) {
		int index = indexOf(nd.block);
		boolean isFirst = index == 0;
		boolean isLast = index == size - 1;
		Node previous = null;
		Node next = null;
		if (isFirst) {
			if (size == 1) {
				first = null;
				last = first;
			}
			else
				first = getNode(1);

		} else if (isLast) {
			previous = getNode(index -1);
			previous.next = null;
			last = previous;
		}
		else {
			previous = getNode(index - 1);
			next = getNode(index+ 1);
			previous.next = next;
		}
		size--;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 *
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		indexValidityCheck(index, true);
		Node nd = getNode(index);
		remove(nd);
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 *
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int index = indexOf(block);
		indexValidityCheck(index);
		Node nd = getNode(index);
		remove(nd);
	}

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}

	/**
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void indexValidityCheck(int index){
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
	}

	/**
	 *Overloads indexValidityCheck for cases where index cannot be equal to size
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void indexValidityCheck(int index, boolean foo){
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
	}

	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String resString = "";
		ListIterator it = iterator();
		while (it.hasNext()) {
			resString += it.current.block + " ";
			it.next();
		}
		return resString;
	}
	public void main() {
		LinkedList tester = new LinkedList();
		MemoryBlock block = new MemoryBlock(10, 1000);
		tester.add(0, block);
		MemoryBlock block2 = new MemoryBlock(20, 2000);
		tester.add(0, block2);
		MemoryBlock block3 = new MemoryBlock(30, 3000);
		tester.add(1, block3);
		MemoryBlock block4 = new MemoryBlock(40, 4000);
		tester.add(3, block4);
		System.out.println(tester);
	}
}