package edu.iastate.cs228.hw5;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Camryn Williams
 * 
 *         Binary search tree implementation of the Set interface. The
 *         contains() and remove() methods of AbstractSet are overridden to
 *         search the tree without using the iterator. Instances of this class
 *         always maintain node counts; that is, the count() method of the
 *         BSTNode interface is implemented to be O(1). If constructed with the
 *         isSelfBalancing flag true, instances of this tree are self-balancing:
 *         whenever an add(), remove(), or Iterator.remove() operation causes
 *         any node to become unbalanced, a rebalance operation is automatically
 *         performed at the highest unbalanced node.
 */
public class BalancedBSTSet<E extends Comparable<? super E>> extends AbstractSet<E>
{
	/**
	 * Root of this tree.
	 */
	protected Node root;

	/**
	 * Number of elements in this tree.
	 */
	protected int size;

	protected boolean selfie;
	private Node highestUnbalancedNode = null;
	protected int top;
	protected int bottom;

	/**
	 * Node type for this implementation.
	 */
	protected class Node implements BSTNode<E>
	{
		public Node left;
		public Node right;
		public Node parent;
		public E data;
		public int count;

		public Node(E key, Node parent)
		{
			this.data = key;
			this.parent = parent;
			this.count = 1;
		}

		@Override
		public BSTNode<E> left()
		{
			return left;
		}

		@Override
		public BSTNode<E> right()
		{
			return right;
		}

		@Override
		public int count()
		{
			return count;
		}

		@Override
		public E data()
		{
			return data;
		}

		@Override
		public BSTNode<E> parent()
		{
			return parent;
		}

		@Override
		public String toString()
		{
			return data.toString();
		}
	}

	/**
	 * Constructs an empty binary search tree. This tree will maintain node
	 * counts but will not automatically perform rebalance operations.
	 */
	public BalancedBSTSet()
	{
		selfie = false;
		top = 2;
		bottom = 3;
	}

	/**
	 * Constructs an empty binary search tree. If the isSelfBalancing flag is
	 * true, the tree will be self-balancing: if so, whenever an add(),
	 * remove(), or Iterator.remove() operation causes any node to become
	 * unbalanced, a rebalance operation is automatically performed at the
	 * highest unbalanced node. The default value alpha = 2/3 is used for the
	 * balance condition. Maintains node counts whether or not isSelfBalancing
	 * is true.
	 * 
	 * @param isSelfBalancing
	 *            true if this binary search tree is to be self-balancing, false
	 *            otherwise
	 */
	public BalancedBSTSet(boolean isSelfBalancing)
	{
		selfie = isSelfBalancing;
		top = 2;
		bottom = 3;
	}

	/**
	 * Constructs an empty binary search tree. If the isSelfBalancing flag is
	 * true, the tree will be self-balancing: if so, whenever an add(),
	 * remove(), or Iterator.remove() operation causes any node to become
	 * unbalanced, a rebalance operation is automatically performed at the
	 * highest unbalanced node. The given alpha = top/bottom is used for the
	 * balance condition. Maintains node counts whether or not isSelfBalancing
	 * is true.
	 * 
	 * @param isSelfBalancing
	 *            true if this binary search tree is to be self-balancing, false
	 *            otherwise
	 * @param top
	 *            numerator of the fraction alpha
	 * @param bottom
	 *            denominator of the fraction alpha
	 * @throws IllegalArgumentException
	 *             if top / bottom is less than 1/2
	 */
	public BalancedBSTSet(boolean isSelfBalancing, int top, int bottom)
	{
		this.top = top;
		this.bottom = bottom;
		double a = top / (double) bottom;
		if ((a) < (.5) || top == 0 || bottom == 0 || (top > bottom))
			throw new IllegalArgumentException();
		
		selfie = isSelfBalancing;
	}

	/**
	 * Returns a read-only view of the root node of this tree.
	 * 
	 * @return root node of this tree
	 */
	public BSTNode<E> root()
	{
		return root;
	}

	/**
	 * Performs a rebalance operation on the given subtree. This operation does
	 * not create or destroy any nodes and does not change the size of this
	 * tree.
	 * 
	 * @param bstNode
	 *            root of the subtree to be rebalanced.
	 * @throws IllegalArgumentException
	 *             if the given node is not part of this tree.
	 * @throws ClassCastException
	 *             if the given node cannot be cast to the correct concrete node
	 *             type for this tree.
	 */
	public void rebalance(BSTNode<E> bstNode)
	{
		// TODO update count, catch exceptions

		if (!contains(bstNode.data()))
		{
			throw new IllegalArgumentException();
		}

		ArrayList<Node> arr = addInOrder((Node) bstNode); 
		int mid = (arr.size() - 1) / 2;

		if (((Node) bstNode).parent == null)
		{
			root = minimize(arr, 0, arr.size() - 1, null);
			root.parent = null;
			bstNode = root;
		}

		else
		{
			// find out the left and right sides
			if (((Node) bstNode).parent.right == bstNode)
			{
				((Node) bstNode).parent.right = minimize(arr, 0, arr.size() - 1,((Node) bstNode).parent);
			}

			else if (((Node) bstNode).parent.left == bstNode)
			{
				((Node) bstNode).parent.left = minimize(arr, 0, arr.size() - 1,((Node) bstNode).parent);
			}
		}

		counter(arr.get(mid));
	}

	/**
	 * Adds all the nodes in the BST into an array
	 * 
	 * @param node
	 * @return array with the BST nodes
	 */
	private ArrayList<Node> addInOrder(Node node)
	{
		ArrayList<Node> arr = new ArrayList<Node>();
		// start with left side
		if (node.left != null)
		{
			arr.addAll(addInOrder(node.left)); // addAll() acts as add() but
												// with collections
		}

		arr.add(node);
		if (node.right != null) // get to a leaf
		{
			arr.addAll(addInOrder(node.right));
		}

		return arr;
	}

	/**
	 * Recursive balance-- I called it minimize because it minimizes the
	 * unbalanced stuff
	 * 
	 * @param arr
	 *            Array of nodes in order
	 * @param first
	 *            first index
	 * @param last
	 *            last index
	 * @param guardian
	 *            parent of node
	 * @return root
	 */
	private Node minimize(ArrayList<Node> arr, int first, int last, Node guardian)
	{
		// TODO
		int mid = (first + last) / 2;

		if (mid == first)
		{
			arr.get(first).left = null;
			arr.get(first).right = arr.get(last);
			arr.get(last).left = null;
			arr.get(last).right = null;
			arr.get(last).parent = arr.get(first);
			return arr.get(first); // do nothing!
		}

		arr.get(mid).left = minimize(arr, first, mid - 1, arr.get(mid));
		arr.get(mid).left.parent = arr.get(mid);
		arr.get(mid).right = minimize(arr, mid + 1, last, arr.get(mid)); 
		arr.get(mid).right.parent = arr.get(mid);

		return arr.get(mid);
	}

	@Override
	public boolean contains(Object obj)
	{
		// This cast may cause comparator to throw ClassCastException at
		// runtime, which is the expected behavior
		E key = (E) obj;
		return findEntry(key) != null;
	}

	@Override
	public boolean add(E key)
	{
		// TODO parent
		boolean Cassidy = false;
		Node temp = root;

		if (root == null)
		{
			root = new Node(key, null);
			temp = root;
			++size;
			return true;
		}

		Node current = root;

		while (true)
		{
			int comp = current.data.compareTo(key);
			if (comp == 0)
			{
				// key is already in the tree
				Cassidy = false;
				break;
			}
			else if (comp > 0)
			{
				if (current.left != null)
				{
					current = current.left;
				}
				else
				{
					current.left = new Node(key, current);
					temp = current.left;
					++size;
					rex(temp);
					Cassidy = true;
					break;
				}
			}
			else
			{
				if (current.right != null)
				{
					current = current.right;
				}
				else
				{

					current.right = new Node(key, current);
					temp = current.right;
					++size;
					rex(temp);
					Cassidy = true;
					break;
				}
			}
		}

		if (selfie && highestUnbalancedNode != null)
		{
			rebalance(highestUnbalancedNode);
		}
		highestUnbalancedNode = null;

		return Cassidy;
	}

	/**
	 * This private helper method that adjusts the count of each of the parent
	 * nodes
	 * 
	 * @param nodie
	 */
	private void rex(Node nodie)
	{
		while (nodie.parent != null)
		{
			nodie.parent.count++;

			if (!balance(nodie.parent))
				highestUnbalancedNode = nodie.parent;

			nodie = nodie.parent;
		}
		
		if(nodie == root)
		{
			if (!balance(nodie))
				highestUnbalancedNode = nodie;
		}
	}

	@Override
	public boolean remove(Object obj)
	{
		// This cast may cause comparator to throw ClassCastException at
		// runtime, which is the expected behavior
		E key = (E) obj;
		Node n = findEntry(key);
		if (n == null)
		{
			return false;
		}

		unlinkNode(n);
		if (root != null)
		{
			counter2(root);

			if (selfie && highestUnbalancedNode != null)
			{
				rebalance(highestUnbalancedNode);
			}
			highestUnbalancedNode = null;
		}
		return true;
	}

	private void counter2(Node nodie)
	{
		int leftCount, rightCount;
		if (nodie.left == null)
		{
			leftCount = 0;
		}
		else
		{
			counter(nodie.left);
			leftCount = nodie.left.count;
		}
		if (nodie.right == null)
		{
			rightCount = 0;
		}
		else
		{
			counter(nodie.right);
			rightCount = nodie.right.count;
		}
		if (!balance(nodie) && highestUnbalancedNode == null)
			highestUnbalancedNode = nodie;

		nodie.count = 1 + leftCount + rightCount;
	}

	/**
	 * Sees if the node is balanced
	 * 
	 * @param node
	 * @return true if the node is balanced
	 */
	private boolean balance(Node node)
	{
		if (node.left != null)
		{
			if (((node.left.count) * bottom) > (size * top))
			{
				return false;
			}
		}

		if (node.right != null)
		{
			if (((node.right.count) * bottom) > (size * top))
			{
				return false;
			}
		}

		return true;
	}

	private void counter(Node nodie)
	{
		int leftCount, rightCount;
		if (nodie.left == null)
		{
			leftCount = 0;
		}
		else
		{
			counter(nodie.left);
			leftCount = nodie.left.count;
		}
		if (nodie.right == null)
		{
			rightCount = 0;
		}
		else
		{
			counter(nodie.right);
			rightCount = nodie.right.count;
		}

		nodie.count = 1 + leftCount + rightCount;
	}

	/**
	 * Returns the node containing key, or null if the key is not found in the
	 * tree.
	 * 
	 * @param key
	 * @return the node containing key, or null if not found
	 */
	protected Node findEntry(E key)
	{
		Node current = root;
		while (current != null)
		{
			int comp = current.data.compareTo(key);
			if (comp == 0)
			{
				return current;
			}
			else if (comp > 0)
			{
				current = current.left;
			}
			else
			{
				current = current.right;
			}
		}
		return null;
	}

	/**
	 * Returns the successor of the given node.
	 * 
	 * @param n
	 * @return the successor of the given node in this tree, or null if there is
	 *         no successor
	 */
	protected Node successor(Node n)
	{
		if (n == null)
		{
			return null;
		}
		else if (n.right != null)
		{
			// leftmost entry in right subtree
			Node current = n.right;
			while (current.left != null)
			{
				current = current.left;
			}
			return current;
		}
		else
		{
			// we need to go up the tree to the closest ancestor that is
			// a left child; its parent must be the successor
			Node current = n.parent;
			Node child = n;
			while (current != null && current.right == child)
			{
				child = current;
				current = current.parent;
			}
			// either current is null, or child is left child of current
			return current;
		}
	}

	/**
	 * Removes the given node, preserving the binary search tree property of the
	 * tree.
	 * 
	 * @param n
	 *            node to be removed
	 */
	protected void unlinkNode(Node n)
	{
		// first deal with the two-child case; copy
		// data from successor up to n, and then delete successor
		// node instead of given node n
		if (n.left != null && n.right != null)
		{
			Node s = successor(n);
			n.data = s.data;
			n = s; // causes s to be deleted in code below
		}

		// n has at most one child
		Node replacement = null;
		if (n.left != null)
		{
			replacement = n.left;
		}
		else if (n.right != null)
		{
			replacement = n.right;
		}

		// link replacement into tree in place of node n
		// (replacement may be null)
		if (n.parent == null)
		{
			root = replacement;
		}
		else
		{
			if (n == n.parent.left)
			{
				n.parent.left = replacement;
			}
			else
			{
				n.parent.right = replacement;
			}
		}

		if (replacement != null)
		{
			replacement.parent = n.parent;
		}

		--size;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new BSTIterator();
	}

	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns a representation of this tree as a multi-line string. The tree is
	 * drawn with the root at the left and children are shown top-to-bottom.
	 * Leaves are marked with a "-" and non-leaves are marked with a "+".
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		toStringRec(root, sb, 0);
		return sb.toString();
	}

	/**
	 * Preorder traversal of the tree that builds a string representation in the
	 * given StringBuilder.
	 * 
	 * @param n
	 *            root of subtree to be traversed
	 * @param sb
	 *            StringBuilder in which to create a string representation
	 * @param depth
	 *            depth of the given node in the tree
	 */
	private void toStringRec(Node n, StringBuilder sb, int depth)
	{
		for (int i = 0; i < depth; ++i)
		{
			sb.append("  ");
		}

		if (n == null)
		{
			sb.append("-\n");
			return;
		}

		if (n.left != null || n.right != null)
		{
			sb.append("+ ");
		}
		else
		{
			sb.append("- ");
		}
		sb.append(n.data.toString() + " (" + n.count() + ")");
		sb.append("\n");
		if (n.left != null || n.right != null)
		{
			toStringRec(n.left, sb, depth + 1);
			toStringRec(n.right, sb, depth + 1);
		}
	}

	/**
	 * Iterator implementation for this binary search tree. The elements are
	 * returned in ascending order according to their natural ordering.
	 */
	private class BSTIterator implements Iterator<E>
	{
		/**
		 * Node to be returned by next call to next().
		 */
		private Node current;

		/**
		 * Node returned by last call to next() and available for removal. This
		 * field is null when no node is available to be removed.
		 */
		private Node pending;

		/**
		 * Constructs an iterator starting at the smallest element in the tree.
		 */
		public BSTIterator()
		{
			// start out at smallest value
			current = root;
			if (current != null)
			{
				while (current.left != null)
				{
					current = current.left;
				}
			}
		}

		@Override
		public boolean hasNext()
		{
			return current != null;
		}

		@Override
		public E next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			pending = current;
			current = successor(current);
			return pending.data;
		}

		@Override
		public void remove()
		{
			if (pending == null)
				throw new IllegalStateException();

			// Remember, current points to the successor of
			// pending, but if pending has two children, then
			// unlinkNode(pending) will copy the successor's data
			// into pending and delete the successor node.
			// So in this case we want to end up with current
			// pointing to the pending node.
			if (pending.left != null && pending.right != null)
			{
				current = pending;
			}

			unlinkNode(pending);
			pending = null;

			counter2(root);

			if (selfie && highestUnbalancedNode != null)
			{
				rebalance(highestUnbalancedNode);
			}
			highestUnbalancedNode = null;
		}
	}
}
