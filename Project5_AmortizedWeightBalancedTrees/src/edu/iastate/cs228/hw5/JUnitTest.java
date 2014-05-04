package edu.iastate.cs228.hw5;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

/**
 * some tests
 * 
 * @author Yuxiang Zhang
 * 
 */

public class JUnitTest {

	@Test
	public void test001() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		assertEquals(3, tree.root.count());
	}

	@Test
	public void test002() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		assertEquals(2, tree.root.right.count());
	}

	@Test
	public void test003() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		assertEquals(1, tree.root.right.right.count());
	}

	@Test
	public void test011() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.rebalance(tree.root());
		assertEquals(5, tree.root.count());
	}

	@Test
	public void test012() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.rebalance(tree.root());
		assertEquals(2, tree.root.left.count());
	}

	@Test
	public void test013() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.rebalance(tree.root());
		assertEquals(2, tree.root.right.count());
	}

	@Test
	public void test021() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.rebalance(tree.root());
		assertEquals(4, tree.root.left.count());
	}

	@Test
	public void test022() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.rebalance(tree.root());
		assertEquals(1, tree.root.left.left.count());
	}

	@Test
	public void test023() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.rebalance(tree.root());
		assertEquals(2, tree.root.left.right.count());
	}

	@Test
	public void test031() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.rebalance(tree.root());
		assertEquals(3, tree.root.count());
	}

	@Test
	public void test032() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.rebalance(tree.root());
		assertEquals(1, tree.root.left.count());
	}

	@Test
	public void test033() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.rebalance(tree.root());
		assertEquals(1, tree.root.right.count());
	}

	@Test
	public void test041() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		assertEquals(3, tree.root.left.count());
	}

	@Test
	public void test042() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		assertEquals(5, tree.root.right.count());
	}

	@Test
	public void test043() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		assertEquals(3, tree.root.right.right.count());
	}

	@Test
	public void test051() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		assertTrue(tree.root.left.data.equals(2));
	}

	@Test
	public void test052() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		assertTrue(tree.root.right.data.equals(6));
	}

	@Test
	public void test053() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		assertTrue(tree.root.right.right.right.data.equals(8));
	}

	@Test
	public void test061() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.remove(4);
		tree.remove(5);
		tree.remove(6);
		assertTrue(tree.root.data.equals(7));
	}

	@Test
	public void test062() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.remove(4);
		tree.remove(5);
		tree.remove(6);
		assertTrue(tree.root.left.right.data.equals(3));
	}

	@Test
	public void test063() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.remove(4);
		tree.remove(5);
		tree.remove(6);
		assertTrue(tree.root.left.count() == 3);
	}

	@Test
	public void test071() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.remove(4);
		tree.remove(5);
		tree.remove(6);
		tree.rebalance(tree.root());
		assertTrue(tree.root.data.equals(3));
	}

	@Test
	public void test072() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.remove(4);
		tree.remove(5);
		tree.remove(6);
		tree.rebalance(tree.root());
		assertTrue(tree.root.right.left.data.equals(7));
	}

	@Test
	public void test073() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.remove(4);
		tree.remove(5);
		tree.remove(6);
		tree.rebalance(tree.root());
		assertTrue(tree.root.right.right.data.equals(9));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test081() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true, 1, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test082() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test083() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true, 0, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test091() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>();
		BalancedBSTSet<Integer> tree0 = new BalancedBSTSet<Integer>();
		tree0.add(9);
		tree.rebalance(tree0.root());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test092() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>();
		BalancedBSTSet<Integer> tree0 = new BalancedBSTSet<Integer>();
		tree0.add(9);
		tree.add(0);
		tree0.rebalance(tree.root());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test093() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>();
		BalancedBSTSet<Integer> tree0 = new BalancedBSTSet<Integer>();
		tree0.add(9);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree0.rebalance(tree.root().right());
	}

	@Test
	public void test101() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		assertTrue(tree.root.data.equals(7));
	}

	@Test
	public void test102() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		assertTrue(tree.root.left.right.data.equals(3));
	}

	@Test
	public void test103() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		assertTrue(tree.root.left.count() == 3);
	}

	@Test
	public void test111() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		tree.rebalance(tree.root());
		assertTrue(tree.root.data.equals(3));
	}

	@Test
	public void test112() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		tree.rebalance(tree.root());
		assertTrue(tree.root.right.left.data.equals(7));
	}

	@Test
	public void test113() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		tree.rebalance(tree.root());
		assertTrue(tree.root.right.right.data.equals(9));
	}
	
	@Test
	public void test121() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.rebalance(tree.root());
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		assertTrue(tree.root.left.data.equals(4));
	}
	
	@Test
	public void test122() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.rebalance(tree.root());
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		assertTrue(tree.root.data.equals(5));
	}
	
	@Test
	public void test123() {
		BalancedBSTSet<Integer> tree = new BalancedBSTSet<Integer>(true);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.rebalance(tree.root());
		Iterator<Integer> iter = tree.iterator();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		iter.next();
		iter.remove();
		assertTrue(tree.root.right.left.data.equals(6));
	}

}

