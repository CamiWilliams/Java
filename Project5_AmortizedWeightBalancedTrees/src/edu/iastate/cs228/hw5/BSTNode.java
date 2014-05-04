package edu.iastate.cs228.hw5;

/**
 * @author Camryn Williams
 *
 * Read-only view of a binary search tree node.
 */
public interface BSTNode<E>
{
	/**
	 * Returns the left child.
	 * 
	 * @return the left child
	 */
	BSTNode<E> left();

	/**
	 * Returns the right child.
	 * 
	 * @return the right child
	 */
	BSTNode<E> right();

	/**
	 * Returns the parent node.
	 * 
	 * @return the parent node
	 */
	BSTNode<E> parent();

	/**
	 * Returns the size of the subtree rooted at this node.
	 * 
	 * @return number of elements in this node's subtree
	 */
	int count();

	/**
	 * Returns the element stored in this node.
	 * 
	 * @return the data in this node
	 */
	E data();
}
