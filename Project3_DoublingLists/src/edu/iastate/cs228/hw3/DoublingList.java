package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked list that stores data in Nodes with varying size arrays as
 * the backing store.
 * 
 * Important Note: Your index-based methods remove(int pos), add(int pos, E
 * item) and listIterator(int pos) must not traverse every element in order to
 * find the node and offset for a given index pos (see spec for more details)
 * 
 * @param <E>
 *            the type of elements in this list
 * 
 * @author Camryn Williams
 */
public class DoublingList<E> extends AbstractSequentialList<E>
{
	// This code is long, rough, and probably not the best you will see. I am so
	// sorry that you have to look at this.
	// Have a nice day
	// And please be generous
	// Thanks
	/**
	 * Node to keep track of the head (beginning of the list)
	 */
	public Node head;
	/**
	 * Node to keep track of the tail (end of the list)
	 */
	public Node tail;

	private int size;

	private int nodeCount;

	public DoublingList<E> dlist;

	public ListIterator<E> iterlist;

	public Iterator<E> iter;

	public DoublingList()
	{
		size = 0;
		nodeCount = 0;
		head = new Node(tail, null, null);
		tail = new Node(null, head, null);
	}

	/**
	 * Removes the element with the given logical position, following the rules
	 * for removing an element. 1. Find the node r and the offset j for element
	 * with logical index i. 2. Save the contents of entry j of the array at r
	 * in a variable x and set the entry to null. 3. If the total number of
	 * elements in L drops to zero, replace L with an empty list and return x 4.
	 * Shift all the elements in r with offset greater than j down by one. Make
	 * sure that the resulting empty slot in the array is set to null. See
	 * Figure 9. 5. If L.size() <= 2^(k-2) - 1, replace L by another doubling
	 * list L' such that - L' has k-1 nodes (numbered 0 through k-2), - L'
	 * contains the same elements as L, in the same order, and - all elements of
	 * L' are stored in nodes 0 through k-3. Thus, the last node is completely
	 * empty 6. Return x.
	 */
	@Override
	public E remove(int pos)
	{
		// TODO test
		if (pos > size || pos < 0)
		{
			throw new IndexOutOfBoundsException();
		}

		int[] arr = this.findLI();
		int index = pos;

		NodeInfo info = find(index);

		Node n = info.node;
		E[] dat = info.node.data;
		E temp = dat[info.offset];

		dat[info.offset] = null;
		n.count--;

		int i = info.offset;

		if (i < dat.length - 1)
		{
			while (i < dat.length - 1)
			{
				dat[i] = dat[i + 1];
				dat[i + 1] = null;
				i++;
			}
		}
		size--;
		int k = nodeCount;

		if (size <= (int) Math.pow(2, k - 2) - 1)
		{
			DoublingList<E> dbl = compress();

			Node tn = head.next; // this n
			Node dn = dbl.head.next; // dbl n

			while (tn.next != null && dn.next != null)
			{
				tn.data = dn.data;
				tn = tn.next;
				dn = dn.next;
			}
			// TODO
			tail.prev = tail.prev.prev;

		}
		return temp;
	}

	/**
	 * Adds the given item to have the given logical position. Adds a new Node
	 * if necessary. Follows the rules stated by leftward and rightward shift.
	 * 
	 * Leftward shift at i. Let t be the rightmost predecessor of r that has
	 * empty slots (we assume that such a node exists). Then, we shift by one
	 * position to the left all elements with logical index less than or equal
	 * to i stored in the nodes between the successor of t and r inclusive. See
	 * Figure 5.
	 * 
	 * Rightward shift at i. Let t be the leftmost successor of r that has empty
	 * slots (we assume that such a node exists). Then, we shift by one position
	 * to the right every element with logical
	 */
	@Override
	public void add(int pos, E item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		if (pos > size || pos < 0)
		{
			throw new IndexOutOfBoundsException();
		}

		if (pos == (int) Math.pow(2, nodeCount) - 2)
		{
			add(item);
		}

		if (getBlankSpacesLocations().size() == 0) // no blanks, need to add new
													// node
		{
			E[] array = (E[]) new Object[tail.prev.data.length * 2];
			Node n = new Node(array);
			link(tail.prev, n);
			Node nb4 = n.prev;
			nodeCount++;

			n.data[0] = nb4.data[nb4.data.length - 1];
			nb4.data[nb4.data.length - 1] = null;

			int off = nb4.data.length - 1;
			int i = 1;

			while (off + (int) Math.pow(2, nodeCount - i) > pos)
			{
				if (off > 0)
				{
					nb4.data[off] = nb4.data[off - 1];
					nb4.data[off - 1] = null;
					off--;
				}

				else
				{
					nb4.data[0] = nb4.prev.data[nb4.prev.data.length - 1];
					nb4.prev.data[nb4.prev.data.length - 1] = null;
					nb4 = nb4.prev;
					i++;
					off = nb4.data.length - 1;
				}
			}

			nb4.data[pos] = item;
		}

		else
		{
			Node n = head.next;
			int temp = pos;
			int nodeNumN = 0;

			// find what node position is in
			while (pos - n.data.length > 0)
			{
				int i = n.data.length;
				pos -= i;
				nodeNumN++;
				n = n.next;
			}

			int offset = pos;

			int closest = findClosestBlank(temp);

			// find node of closest
			Node n2 = head.next;
			int blank = closest;
			int nodeNumN2 = 0;
			while (closest - n2.data.length > 0)
			{
				int i = n2.data.length;
				closest -= i;
				nodeNumN2++;
				n2 = n2.next;
			}

			int offblank = closest;
			int i = nodeNumN2;

			shift(n2, temp, blank, offblank);

			n.data[offset] = item;
		}

		size++;
	}

	private void shift(Node n2, int temp, int blank, int offblank)
	{
		if (temp - blank > 0) // shift left
		{
			while (blank < temp)
			{
				if (offblank < n2.data.length - 1)
				{
					n2.data[offblank] = n2.data[offblank + 1];
					n2.data[offblank + 1] = null;
					offblank++;
					blank++;
				}

				else
				{
					n2.data[offblank] = n2.next.data[0];
					n2.next.data[0] = null;
					n2 = n2.next;
					offblank = 0;
					blank++;
				}
			}
		}

		else
		// shift right
		{
			while (blank > temp)
			{
				if (offblank > 0)
				{
					n2.data[offblank] = n2.data[offblank - 1];
					n2.data[offblank - 1] = null;
					offblank--;
					blank--;
				}

				else
				{
					n2.data[0] = n2.prev.data[n2.prev.data.length - 1];
					n2.prev.data[n2.prev.data.length - 1] = null;
					n2 = n2.prev;
					offblank = n2.data.length - 1;
					blank--;
				}
			}
		}

	}

	/**
	 * Adds the given item to the end of the list. Creates a new Node if
	 * Necessary. Throws a NullPointerException if the item is null. Return true
	 * if the add was successful, false otherwise.
	 */
	@Override
	public boolean add(E item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		}

		else if (size == 0)
		{
			E[] array = (E[]) new Object[1];
			Node n = new Node(tail, head, array);
			n.data[0] = item;
			tail.prev = n;
			head.next = n;
			tail.prev.count++;
			nodeCount++;
		}

		else if (tail.prev.count == tail.prev.data.length)
		{
			if (getBlankSpacesLocations().size() == 0)
			{
				E[] array = (E[]) new Object[tail.prev.data.length * 2];
				Node n = new Node(array);
				link(tail.prev, n);
				n.data[0] = item;
				tail.prev.count++;
				nodeCount++;
			}

			else
			{
				int temp = (int) Math.pow(2, nodeCount) - 2;
				int closest = findClosestBlank(temp);

				// find node of closest
				Node n2 = head.next;
				int blank = closest;
				int nodeNumN2 = 0;
				while (closest > 0)
				{
					int i = tail.prev.data.length;
					closest -= i;
					nodeNumN2++;
					n2 = n2.next;
				}

				int offblank = closest;
				int i = nodeNumN2;

				shift(n2, temp, blank, offblank);

				tail.prev.data[tail.prev.data.length - 1] = item;
			}
		}

		else
		{
			tail.prev.addThing(item);
		}

		size++;
		return true;
	}

	/**
	 * Returns an int array of the list's data
	 * 
	 * @return li
	 */
	private int[] findLI()
	{
		int[] li = new int[(int) Math.pow(2, nodeCount) - 1];
		Node n = head.next;
		int li_num = 0;
		int j = 0;

		while (n.next != null)
		{
			int i = 0;

			while (i < n.data.length)
			{
				if (n.data[i] != null)
				{
					li[j] = li_num;
					j++;
					li_num++;
				}

				else
				{
					li[j] = -1;
					j++;
				}
				i++;
			}

			n = n.next;
		}
		return li;
	}

	private DoublingList<E> compress()
	{
		DoublingList<E> dbl = new DoublingList<E>();
		int k = nodeCount;

		int i = 0;
		Node n = dbl.head.next;
		Node current = null;
		Node previous = null;
		int fill = 1;

		while (i < k - 1)
		{
			if (dbl.nodeCount == 0)
			{
				previous = tail;
				E[] array = (E[]) new Object[fill];
				n = new Node(array);
				current = n;
				i++;
				dbl.tail.prev = n;
				dbl.head.next = n;
				dbl.nodeCount++;
			}

			else
			{
				previous = current;
				fill = fill * 2;
				E[] array = (E[]) new Object[fill];
				n = new Node(array);
				current = n;
				current.prev = previous;
				current.next = previous.next;
				tail.prev = current;
				previous.next = current;
				i++;
			}
		}

		int[] numbersToAdd = this.findLI();

		int j = 0;

		while (j < numbersToAdd.length)
		{
			if (numbersToAdd[j] != -1)
			{
				NodeInfo info = this.find(numbersToAdd[j]);
				dbl.add(info.node.data[info.offset]);
			}
			j++;
		}

		return dbl;
	}

	/**
	 * Inserts newNode into the list after current without updating size.
	 * Precondition: current != null, newNode != null
	 */
	private void link(Node current, Node newNode)
	{
		newNode.prev = current;
		newNode.next = current.next;
		current.next.prev = newNode;
		current.next = newNode;
	}

	/**
	 * Removes current from the list without updating size.
	 */
	private void unlink(Node current)
	{
		current.prev.next = current.next;
		current.next.prev = current.prev;
	}

	/**
	 * Returns a ListIterator for this DoublingList at the given position (I.E.
	 * a call to next should return the element with the logical index equal to
	 * the index given)
	 */
	@Override
	public ListIterator<E> listIterator(int index)
	{
		iterlist = new DoublingListIterator(index);
		return iterlist;
	}

	/**
	 * Returns a ListIterator for this DoublingList starting from the beginning
	 */
	@Override
	public ListIterator<E> listIterator()
	{
		iterlist = new DoublingListIterator();
		return iterlist;
	}

	/**
	 * Returns an Iterator for this DoublingList starting from the beginning
	 */
	@Override
	public Iterator<E> iterator()
	{
		iter = new DoublingIterator();
		return iter;
	}

	/**
	 * Returns the size of the list. It is acceptable to create an instance
	 * variable and update it during add / remove so you can just return that
	 * variable here.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * A method to set the size for this DoublingList. In real life this method
	 * would not exist, but in order to test your code we need it.
	 */
	public void setSize(int size)
	{
		this.size = size;
	}

	/**
	 * 
	 * ListIterator class. Please reference the ListIterator API to see how
	 * methods handle errors (no next element, null arguments, etc.)
	 * 
	 * Note that is inner class is public. Normally this isn't done because then
	 * outside users can mess with the internal structure of the list, however
	 * we need this to test your code.
	 * 
	 * API: http://docs.oracle.com/javase/6/docs/api/java/util/ListIterator.html
	 */
	private class DoublingListIterator implements ListIterator<E>
	{
		/**
		 * Index of iterator
		 */
		public int nodeIndex;

		/**
		 * Node at index.
		 */
		public Node cursor;

		private int position;

		private E element;

		/**
		 * These ints tell you where the cursor came from after calling next()
		 * or previous(). The final ints are the values for direction.
		 */
		public int direction;
		private static final int BEHIND = -1;
		private static final int AHEAD = 1;
		private static final int NONE = 0;

		private DoublingListIterator()
		{
			nodeIndex = 0;
			position = 0;
			direction = NONE;
			cursor = head.next;
		}

		private DoublingListIterator(int s)
		{
			if (s == 0)
			{
				nodeIndex = 0;
				position = 0;
				direction = NONE;
				cursor = head.next;
			}

			else
			{
				position = s;
				direction = NONE;
				NodeInfo info = find(s);
				cursor = info.node;
				nodeIndex = s;
			}

			if (nodeIndex > size + 1 || nodeIndex < 0)
			{
				throw new IndexOutOfBoundsException();
			}
		}

		/**
		 * Adds the given element to the DoublingList following the rules of
		 * add(). DO NOT call the add method you wrote for DoublingList above!
		 * 
		 */
		@Override
		public void add(E arg0)
		{
			if (arg0 == null)
			{
				throw new NullPointerException();
			}

			NodeInfo info = find(nodeIndex);
			Node node = info.node;

			if (node.data[info.offset] == null)
			{
				node.data[info.offset] = arg0;
			}

			DoublingList.this.add(nodeIndex, arg0);

			nodeIndex++;
			size++;
			direction = NONE;
		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the forward direction. (In other words, returns true if
		 * next would return an element rather than throwing an exception.)
		 */
		@Override
		public boolean hasNext()
		{
			try
			{
				Node n = head.next;
				int c = 0;
				while (n.next != null)
				{
					c += n.count;
					n = n.next;
				}
				return nodeIndex < c;
			}
			catch (NoSuchElementException e)
			{
				return false;
			}
		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the reverse direction. (In other words, returns true if
		 * previous would return an element rather than throwing an exception.)
		 */
		@Override
		public boolean hasPrevious()
		{
			try
			{
				return nodeIndex >= 0;
			}
			catch (NoSuchElementException e)
			{
				return false;
			}
		}

		/**
		 * Returns the next element in the list. This method may be called
		 * repeatedly to iterate through the list, or intermixed with calls to
		 * previous to go back and forth. (Note that alternating calls to next
		 * and previous will return the same element repeatedly.)
		 */
		@Override
		public E next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}

			if (cursor == head)
			{
				cursor = head.next;
				element = cursor.data[nodeIndex];
			}

			NodeInfo info = find(nodeIndex);
			// If the nodeIndex is bigger than the cursor's, go to next node
			if (info.offset >= cursor.data.length - 1)
			{
				cursor = cursor.next;
				element = cursor.data[info.offset];
			}
			else
			{
				element = cursor.data[info.offset]; // go to next index
			}

			nodeIndex++;
			position++;
			direction = BEHIND;
			return element;
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to next. (Returns list size if the list iterator is
		 * at the end of the list.)
		 */
		@Override
		public int nextIndex()
		{
			return nodeIndex;
		}

		/**
		 * Returns the previous element in the list. This method may be called
		 * repeatedly to iterate through the list backwards, or intermixed with
		 * calls to next to go back and forth. (Note that alternating calls to
		 * next and previous will return the same element repeatedly.)
		 */
		@Override
		public E previous()
		{
			if (!hasPrevious())
			{
				throw new NoSuchElementException();
			}

			if (cursor == tail)
			{
				cursor = tail.prev;
				element = cursor.data[nodeIndex];
			}

			NodeInfo info = find(nodeIndex);

			// If the nodeIndex is less than the cursor's, go to next node
			if (info.offset < cursor.data.length)
			{
				element = cursor.data[info.offset];
			}

			else
			{
				cursor = cursor.prev;
				nodeIndex = cursor.data.length - 1;
				element = cursor.data[nodeIndex];
			}

			nodeIndex--;
			position--;
			direction = AHEAD;
			return element;
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to previous. (Returns -1 if the list iterator is at
		 * the beginning of the list.)
		 */
		@Override
		public int previousIndex()
		{
			return nodeIndex;
		}

		/**
		 * Removes from the list the last element that was returned by next or
		 * previous (optional operation). This call can only be made once per
		 * call to next or previous. It can be made only if ListIterator.add has
		 * not been called after the last call to next or previous. DO NOT call
		 * the remove method you wrote for DoublingList above!
		 */
		@Override
		public void remove()
		{
			if (direction == NONE)
			{
				throw new IllegalStateException();
			}

			else
			{
				NodeInfo info;
				if (direction == BEHIND)
				{
					info = find(position);
					nodeIndex--;
				}

				else
				{
					info = find(position + 1);
				}

				Node n = info.node;
				E[] dat = n.data;
				int i = info.offset;

				if (i == dat.length - 1)
				{
					dat[i] = null;
				}
				else
				{
					while (i < dat.length - 1)
					{
						dat[i] = dat[i + 1];
						dat[i + 1] = null;
						i++;
					}
				}
				n.data = dat;

			}
			size--;
			direction = NONE;
		}

		/**
		 * Replaces the last element returned by next or previous with the
		 * specified element (optional operation). This call can be made only if
		 * neither ListIterator.remove nor ListIterator.add have been called
		 * after the last call to next or previous.
		 */
		@Override
		public void set(E arg0)
		{
			NodeInfo info = find(nodeIndex);
			if (direction == NONE)
			{
				throw new IllegalStateException();
			}

			if (direction == AHEAD)
			{
				info.node.data[info.offset + 1] = arg0;
			}
			else
			{
				info.node.data[info.offset] = arg0;
			}
		}

	}

	/**
	 * Iterator to be used for traversing a DoublingList. This iterator is
	 * optional if you fully implement the ListIterator but is easier and
	 * partial point will be awarded if the one is correct and your ListIterator
	 * is wrong.
	 * 
	 * API: http://docs.oracle.com/javase/6/docs/api/java/util/Iterator.html
	 */
	private class DoublingIterator implements Iterator<E>
	{
		/**
		 * Index within the current Node.
		 */
		public int nodeIndex;

		/**
		 * Node at index.
		 */
		public Node cursor;

		private E element;

		public int direction;
		private static final int BEHIND = -1;
		private static final int NONE = 0;

		private DoublingIterator()
		{
			direction = NONE;
			cursor = head.next;
		}

		/**
		 * Returns true if the iteration has more elements. (In other words,
		 * returns true if next would return an element rather than throwing an
		 * exception.)
		 */

		@Override
		public boolean hasNext()
		{
			try
			{
				Node n = head.next;
				int c = 0;
				while (n.next != null)
				{
					c += n.count;
					n = n.next;
				}
				return nodeIndex < c - 1;
			}
			catch (NoSuchElementException e)
			{
				return false;
			}
		}

		/**
		 * Returns the next element in the iteration.
		 */
		@Override
		public E next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}

			if (cursor == head)
			{
				cursor = head.next;
				element = cursor.data[nodeIndex];
			}

			NodeInfo info = find(nodeIndex);
			// If the nodeIndex is bigger than the cursor's, go to next node
			if (info.offset >= cursor.data.length - 1)
			{
				cursor = cursor.next;
				element = cursor.data[info.offset];
			}
			else
			{
				element = cursor.data[info.offset]; // go to next index
			}

			nodeIndex++;
			direction = BEHIND;
			return element;
		}

		/**
		 * You do not need to implement this method
		 */
		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Node class that makes up a DoublingList. Feel free to add methods /
	 * constructors / variables you might find useful in here.
	 */
	public class Node
	{
		public Node next;
		public Node prev;
		public E[] data;
		public int count; // number of elements in this node

		public Node(Node next, Node prev, E[] data)
		{
			this.next = next;
			this.prev = prev;
			this.data = data;

			if (data != null)
			{
				int i = 0;
				while (i < data.length)
				{
					if (data[i] != null)
					{
						count++;
					}
					i++;
				}
			}
		}

		public Node(E[] data)
		{
			this.data = data;
			if (data != null)
			{
				int i = 0;
				while (i < data.length)
				{
					if (data[i] != null)
					{
						count++;
					}
					i++;
				}
			}
		}

		private int nodeLocation()
		{
			int nodeCount = 0;
			Node n = this;

			while (n.prev != head)
			{
				nodeCount++;
				n = n.prev;
			}

			return nodeCount;
		}

		public void addThing(E item)
		{
			data[count++] = item;
		}
	}

	/**
	 * NodeInfo class that you may find useful to use. Again, feel free to add
	 * methods / constructors / variables that you find useful in here.
	 */
	private class NodeInfo
	{
		public Node node;
		public int offset;

		public NodeInfo(Node node, int offset)
		{
			this.node = node;
			this.offset = offset;
		}

	}

	/**
	 * Helper method to find the current Node and offset index within the Node.
	 * 
	 * @param position
	 *            Given position to find the Node and offset.
	 * @return NodeInfo object.
	 */
	private NodeInfo find(int position)
	{
		NodeInfo current = new NodeInfo(head.next, 0);
		int total = 0;

		if (position < 0)
		{
			return new NodeInfo(head, 0);
		}

		else if (position >= size)
		{
			return new NodeInfo(tail, 0);
		}

		else
		{
			boolean found = false;
			while (!found)
			{
				if (total <= position && current.node.count + total > position)
				{
					found = true;
				}
				else
				{
					total += current.node.count;
					current.node = current.node.next;
				}
			}
		}

		current.offset = position - total;
		return current;
	}

	/**
	 * Returns an array list of the logical indexes of all the blank spaces
	 * 
	 * @return arr
	 */
	private ArrayList<Integer> getBlankSpacesLocations()
	{
		Node n = head.next;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int nodeCount = 0;

		while (n.next != null)
		{
			int i = 0;
			while (i < n.data.length)
			{
				if (n.data[i] == null)
				{
					arr.add(i + ((int) Math.pow(2, nodeCount) - 1));
				}
				i++;
			}
			n = n.next;
			nodeCount++;
		}

		return arr;
	}

	/**
	 * Finds closest blank space in the list to the position
	 * 
	 * @param position
	 * @return logical index of blank space
	 */
	private int findClosestBlank(int position)
	{
		ArrayList<Integer> arr = this.getBlankSpacesLocations();
		int closest = arr.get(0);
		int i = 0;

		while (i < arr.size() - 1)
		{
			if (Math.abs(closest - position) > Math.abs(arr.get(i + 1) - position))
			{
				closest = arr.get(i + 1);
			}
			i++;
		}

		return closest;
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes.
	 */
	public String toStringInternal()
	{
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes and the position of the iterator.
	 * 
	 * @param iter
	 *            an iterator for this list
	 */
	public String toStringInternal(ListIterator<E> iter)
	{
		int count = 0;
		int position = -1;
		if (iter != null)
		{
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Node current = head.next;
		while (current != tail)
		{
			sb.append('(');
			E data = current.data[0];
			if (data == null)
			{
				sb.append("-");
			}
			else
			{
				if (position == count)
				{
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < current.data.length; ++i)
			{
				sb.append(", ");
				data = current.data[i];
				if (data == null)
				{
					sb.append("-");
				}
				else
				{
					if (position == count)
					{
						sb.append("| ");
						position = -1;
					}
					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size() && count == size())
					{
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.next;
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
