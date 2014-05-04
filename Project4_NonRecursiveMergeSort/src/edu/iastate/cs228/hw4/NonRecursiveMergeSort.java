package edu.iastate.cs228.hw4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * @author Camryn Williams
 */
public class NonRecursiveMergeSort
{

	static class Information
	{
		private boolean sorted;
		private int first;
		private int last;

		public Information(int firstIndex, int lastIndex, boolean sorted)
		{
			first = firstIndex;
			last = lastIndex;
			this.sorted = sorted;
		}

		public boolean isSorted()
		{
			return sorted;
		}

		public int firstIndex()
		{
			return first;
		}

		public int lastIndex()
		{
			return last;
		}
	}

	/**
	 * Generic version of merge sort. The type T is arbitrary, but the caller
	 * must supply a comparator. The algorithm must be implemented
	 * non-recursively and must run in O(n log n) time.
	 */
	public static <T> void mergeSort(T[] arr, Comparator<? super T> comp)
	{
		// TODO
		
		if(comp == null || arr == null)
		{
			throw new IllegalArgumentException();
		}
		
		Stack stack = new Stack();
		int first = 0; // first index
		int last = arr.length - 1; // last index

		Information info = new Information(first, last, false);
		stack.push(info);

		while (!stack.empty())
		{
			Information top = (Information) stack.pop();
			int firstIndex = top.firstIndex();
			int lastIndex = top.lastIndex();
			int mid = (lastIndex + firstIndex) / 2;

			if (top.isSorted()) // if the left and right side are sorted
			{
				T[] arr2 = (T[]) new Comparable[lastIndex - firstIndex + 1];

				// merge
				if (firstIndex < lastIndex)
				{
					int i = mid + 1;
					int j = firstIndex;

					for (int k = 0; k < arr2.length; k++)
					{
						if (j > mid)
						{
							arr2[k] = arr[i];
							i++;
						}
						else
						{
							if (i <= lastIndex)
							{
								if (comp.compare(arr[i], arr[j]) > 0)
								{
									arr2[k] = arr[j];
									j++;
								}

								else
								{
									arr2[k] = arr[i];
									i++;
								}
							}

							else
							{
								arr2[k] = arr[j];
								j++;
							}

						}
					}

					for (int l = 0; l < arr2.length; l++)
					{
						arr[firstIndex + l] = arr2[l];
					}
				}
			}

			else
			{
				if (firstIndex < lastIndex)
				{
					stack.push(new Information(firstIndex, lastIndex, true));
					stack.push(new Information(firstIndex, mid, false));
					stack.push(new Information(mid + 1, lastIndex, false));
				}
			}
		}
	}

	/**
	 * Another generic version of merge sort. This one imposes bounds on T to
	 * guarantee that we can call the compareTo() method on objects of type T.
	 * The algorithm must be implemented non-recursively and must run in O(n log
	 * n) time.
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] arr)
	{
		if(arr == null)
		{
			throw new IllegalArgumentException();
		}
		
		Stack stack = new Stack();
		int first = 0;
		int last = arr.length - 1;

		Information info = new Information(first, last, false);
		stack.push(info);

		while (!stack.empty())
		{
			Information top = (Information) stack.pop();
			int firstIndex = top.firstIndex();
			int lastIndex = top.lastIndex();
			int mid = (lastIndex + firstIndex) / 2;

			if (top.isSorted()) // if the left and right side are sorted
			{
				T[] arr2 = (T[]) new Comparable[lastIndex - firstIndex + 1];

				// merge
				if (firstIndex < lastIndex)
				{
					int i = mid + 1;
					int j = firstIndex;

					for (int k = 0; k < arr2.length; k++)
					{
						if (j > mid)
						{
							arr2[k] = arr[i];
							i++;
						}

						else
						{
							if (i <= lastIndex)
							{
								if (arr[i].compareTo(arr[j]) > 0)
								{
									arr2[k] = arr[j];
									j++;
								}

								else
								{
									arr2[k] = arr[i];
									i++;
								}
							}

							else
							{
								arr2[k] = arr[j];
								j++;
							}

						}
					}

					for (int l = 0; l < arr2.length; l++)
					{
						arr[firstIndex + l] = arr2[l];
					}
				}
			}

			else
			{
				if (firstIndex < lastIndex)
				{
					stack.push(new Information(firstIndex, lastIndex, true));
					stack.push(new Information(firstIndex, mid, false));
					stack.push(new Information(mid + 1, lastIndex, false));
				}
			}
		}
	}
}
