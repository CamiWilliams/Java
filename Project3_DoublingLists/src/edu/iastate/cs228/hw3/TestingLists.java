package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.ListIterator;

import java.util.NoSuchElementException;

import javax.swing.text.html.HTMLDocument.Iterator;

import junit.framework.Assert;

import org.junit.Test;

public class TestingLists {

	/**
	 * exceptions
	 * new list
	 * add to end of list when empty
	 * add to end of list when space available
	 * add to end of list when no space left
	 * add to pos when empty
	 * add to pos when space available and node not full
	 * add to pos when space available and node is full but predecessor has space
	 * add to pos when space available and node is full but predecessor has no space
	 * add to pos when pos == list size and last node is not full
	 * add to pos when pos == list size and last node is full but predecessor has space
	 * add to pos when pos == list size and last node is full and predecessor has no space
	 * add to pos when cap == list size and pos == list size
	 * add to pos when cap == list size and pos < list size
	 * remove pos so that list size == 0
	 * remove pos so that items must be shifted left
	 * remove pos so that list must be compacted
	 * listIterator at index 0
	 * listIterator at different index
	 * listIterator at end of list
	 * listIterator add tests
	 * listIterator remove tests
	 * listIterator hasNext
	 * listIterator next
	 * listIterator hasPrevious
	 * listIterator previous
	 * listIterator nextIndex
	 * listIterator previousIndex
	 * listIterator set NONE
	 * listIterator set AHEAD
	 * listITerator set BEHIND
	 * Node data test
	 * Node next test
	 * Node prev test
	 * 
	 */
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void removePosIndexException() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.remove(1);
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void addIndexException() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add(4, "A");
	}
	
	@Test(expected = NullPointerException.class)
	public void addPosNullException() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add(0, null);
	}
	
	@Test(expected = NullPointerException.class)
	public void addNullException() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add(null);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void listIteratorIndexException() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.listIterator(4);
	}
	
	@Test(expected = NullPointerException.class)
	public void listIteratorAddNullException() {
		DoublingList<String> myList = new DoublingList<String>();
		ListIterator<String> iter = myList.listIterator();
		iter.add(null);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void listIteratorNextNoException() {
		DoublingList<String> myList = new DoublingList<String>();
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.next();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void listIteratorPreviousNoException() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		ListIterator<String> iter = myList.listIterator();
		iter.previous();
	}
	
	@Test(expected = IllegalStateException.class)
	public void listIteratorSetIllegalException() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		ListIterator<String> iter = myList.listIterator();
		iter.set("A");
	}
	
	//TODO regular iterator exception test
	
	@Test
	public void newEmptyList() {
		DoublingList<String> myList = new DoublingList<String>();
		Assert.assertEquals("[]", myList.toStringInternal());
	}
	
	@Test
	public void addToEndEmpty() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		Assert.assertEquals("[(A)]", myList.toStringInternal());
	}
	
	@Test
	public void addToEndSpaceAvailable() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void addToEndNoSpace() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		Assert.assertEquals("[(A), (B, -)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosEmpty() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add(0, "A");
		Assert.assertEquals("[(A)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosNodeNotFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("C");
		myList.add(1, "B");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosNodeFullPreOpen() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("B");
		myList.add("A");
		myList.add("C");
		myList.remove(0);
		myList.add(0, "B");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosNodeFullPreFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("D");
		myList.add(2, "C");
		Assert.assertEquals("[(A), (B, C), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosListSizeNodeNotFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add(2, "C");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosListSizePreNotFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		myList.add("F");
		myList.add("G");
		myList.remove(0);
		myList.add(5, "H");
		Assert.assertEquals("[(B), (C, D), (E, F, G, H)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosListSizeListFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("E");
		myList.remove(3);
		myList.add(3, "D");
		Assert.assertEquals("[(A), (B, C), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosListSizeEqualsListCap() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add(3, "D");
		Assert.assertEquals("[(A), (B, C), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void addToPosLessThanListSizeEqualsListCap() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("D");
		myList.add(2, "C");
		Assert.assertEquals("[(A), (B, C), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeListZero() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.remove(0);
		Assert.assertEquals("[]", myList.toStringInternal());
	}
	
	@Test
	public void removeShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.remove(1);
		Assert.assertEquals("[(A), (C, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeAndCompact() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		myList.add("F");
		myList.add("G");
		myList.add("H");
		myList.remove(0);
		myList.remove(0);
		myList.remove(0);
		myList.remove(0);
		myList.remove(0);
		myList.remove(0);
		Assert.assertEquals("[(-), (G, H), (-, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorAtIndexZeroEmpty() {
		DoublingList<String> myList = new DoublingList<String>();
		ListIterator<String> iter = myList.listIterator();
		Assert.assertEquals(0, iter.nextIndex());
	}
	
	@Test
	public void listIteratorAtIndexZero() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(0);
		Assert.assertEquals(0, iter.nextIndex());
	}
	
	@Test
	public void listIteratorAtIndexNonZero() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(1);
		Assert.assertEquals(1, iter.nextIndex());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void listIteratorAddEmpty(){
		DoublingList<String> myList = new DoublingList<String>();
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.add("A");
		Assert.assertEquals("[(A)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorAheadAddFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("D");
		ListIterator<String> iter = myList.listIterator(3);
		iter.previous();
		iter.add("C");
		Assert.assertEquals("[(A), (B, C), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorAheadAddCursorPosition() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("D");
		ListIterator<String> iter = myList.listIterator(3);
		iter.previous();
		iter.add("C");
		Assert.assertEquals(3, iter.nextIndex());
	}
	
	@Test
	public void listIteratorAheadAddEmptyArray() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("C");
		myList.add("B");
		myList.remove(1);
		ListIterator<String> iter = myList.listIterator(1);
		iter.previous();
		iter.add("A");
		Assert.assertEquals("[(A), (C, -)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorAheadAddShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("X");
		myList.add("A");
		myList.add("C");
		myList.remove(0);
		ListIterator<String> iter = myList.listIterator(2);
		iter.previous();
		iter.add("B");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorAheadAddShiftRight() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(2);
		iter.previous();
		iter.add("B");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test (expected = IllegalStateException.class)
	public void listIteratorAheadAddTwice() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		ListIterator<String> iter = myList.listIterator(1);
		iter.previous();
		iter.add("A");
		iter.add("A");
	}
	
	@Test
	public void listIteratorBehindAddFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(2);
		iter.next();
		iter.add("D");
		Assert.assertEquals("[(A), (B, C), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorBehindAddEmptyArray() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.remove(1);
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.add("C");
		Assert.assertEquals("[(A), (C, -)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorBehindAddShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("X");
		myList.add("A");
		myList.add("B");
		myList.remove(0);
		ListIterator<String> iter = myList.listIterator(1);
		iter.next();
		iter.add("C");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorBehindAddShiftRight() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.add("B");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test (expected = IllegalStateException.class)
	public void listIteratorBehindAddTwice() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.add("A");
		iter.add("A");
	}
	
	@Test
	public void removeFirstArrayElementBehindShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(1);
		iter.next();
		iter.remove();
		Assert.assertEquals("[(A), (C, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeLastElementBehindNoShift() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(2);
		iter.next();
		iter.remove();
		Assert.assertEquals("[(A), (B, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeFirstArrayBehindElementShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		ListIterator<String> iter = myList.listIterator(1);
		iter.next();
		iter.remove();
		Assert.assertEquals("[(A), (C, -), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeMiddleArrayElementBehindShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		myList.add("F");
		myList.add("G");
		ListIterator<String> iter = myList.listIterator(4);
		iter.next();
		iter.remove();
		Assert.assertEquals("[(A), (B, C), (D, F, G, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeLastArrayElementBehindNoShift() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		myList.add("F");
		myList.add("G");
		ListIterator<String> iter = myList.listIterator(6);
		iter.next();
		iter.remove();
		Assert.assertEquals("[(A), (B, C), (D, E, F, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeFirstArrayElementAheadShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(2);
		iter.previous();
		iter.remove();
		Assert.assertEquals("[(A), (C, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeLastElementAheadNoShift() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(3);
		iter.previous();
		iter.remove();
		Assert.assertEquals("[(A), (B, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeFirstArrayAheadElementShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		ListIterator<String> iter = myList.listIterator(2);
		iter.previous();
		iter.remove();
		Assert.assertEquals("[(A), (C, -), (D, -, -, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeMiddleArrayElementAheadShiftLeft() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		myList.add("F");
		myList.add("G");
		ListIterator<String> iter = myList.listIterator(5);
		iter.previous();
		iter.remove();
		Assert.assertEquals("[(A), (B, C), (D, F, G, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeLastArrayElementAheadNoShift() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		myList.add("F");
		myList.add("G");
		ListIterator<String> iter = myList.listIterator(7);
		iter.previous();
		iter.remove();
		Assert.assertEquals("[(A), (B, C), (D, E, F, -)]", myList.toStringInternal());
	}
	
	@Test
	public void removeAndInvokeCompaction() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		myList.add("F");
		myList.add("G");
		myList.add("H");
		ListIterator<String> iter = myList.listIterator(8);
		iter.previous();
		iter.remove();
		iter.previous();
		iter.remove();
		iter.previous();
		iter.remove();
		iter.previous();
		iter.remove();
		iter.previous();
		iter.remove();
		Assert.assertEquals("[(A), (B, C), (-, -, -, -)]", myList.toStringInternal());
	}
	
	@Test (expected = IllegalStateException.class)
	public void removeTwice() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		ListIterator<String> iter = myList.listIterator(2);
		iter.next();
		iter.remove();
		iter.remove();
	}
	
	@Test
	public void listSize() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		Assert.assertEquals(3, myList.size());
	}
	
	@Test
	public void listIteratorNextIndex() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.next();
		iter.remove();
		Assert.assertEquals(1, iter.nextIndex());
	}

	@Test
	public void listIteratorPreviousIndex() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("C");
		myList.add("D");
		myList.add("E");
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.next();
		iter.remove();
		Assert.assertEquals(0, iter.previousIndex());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void listIteratorBehindSetEmpty() {
		DoublingList<String> myList = new DoublingList<String>();
		ListIterator<String> iter = myList.listIterator();
		iter.next();
		iter.set("B");
	}
	
	@Test
	public void listIteratorBehindSetFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("D");
		ListIterator<String> iter = myList.listIterator(2);
		iter.next();
		iter.set("C");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorBehindSet() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		ListIterator<String> iter = myList.listIterator(1);
		iter.next();
		iter.set("A");
		Assert.assertEquals("[(A), (A, -)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorBehindSetTwice() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		ListIterator<String> iter = myList.listIterator(1);
		iter.next();
		iter.set("A");
		iter.set("C");
		Assert.assertEquals("[(A), (C, -)]", myList.toStringInternal());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void listIteratorAheadSetEmpty() {
		DoublingList<String> myList = new DoublingList<String>();
		ListIterator<String> iter = myList.listIterator();
		iter.previous();
		iter.set("B");
	}
	
	@Test
	public void listIteratorAheadSetFull() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		myList.add("D");
		ListIterator<String> iter = myList.listIterator(3);
		iter.previous();
		iter.set("C");
		Assert.assertEquals("[(A), (B, C)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorAheadSet() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		ListIterator<String> iter = myList.listIterator(2);
		iter.previous();
		iter.set("A");
		Assert.assertEquals("[(A), (A, -)]", myList.toStringInternal());
	}
	
	@Test
	public void listIteratorAheadSetTwice() {
		DoublingList<String> myList = new DoublingList<String>();
		myList.add("A");
		myList.add("B");
		ListIterator<String> iter = myList.listIterator(2);
		iter.previous();
		iter.set("A");
		iter.set("C");
		Assert.assertEquals("[(A), (C, -)]", myList.toStringInternal());
	}
}
