/**
 * Represents a node in a linked list. Each node points to a MemoryBlock object. 
 */
public class Node {

	MemoryBlock block;
	Node next = null;

	public Node(MemoryBlock block) {
		this.block = block;
	}

	public String toString() {
		return "{" + block + "}";
	}
}