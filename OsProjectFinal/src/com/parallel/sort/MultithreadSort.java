package com.parallel.sort;



//import java.util.Arrays;
//import java.util.Collections;
import java.util.Random;
import java.util.concurrent.*;
import java.util.*;


@SuppressWarnings("rawtypes") 
public class MultithreadSort {

	/** Threshold to apply insertion sort */
	private static final int THRESHOLD = 100;
	private static final int ARRAY_SIZE = 1000000;

	//////////////////////////////////////////////////////// quicksort begin

	static class QuickSort extends RecursiveAction
	{
		private Comparable[] a;
		private int low, high;

		public QuickSort(Comparable[] array, int l, int h) { // constructor
			if(array == null)
			{
				System.out.println("Pointer to the input array is NULL!\n");
				return;
			}
			a = array;
			low = l;
			high = h;
		}

		// the method where parallel computing will occur
		@Override
		protected void compute() 
		{
			if (low + THRESHOLD > high)
				insertionsort();

			else 
			{
				if(low < high) // subdivide, fork-compute-join
				{
					int partitionIndex = partition();

					QuickSort leftSort = new QuickSort(a, low, partitionIndex-1);
					QuickSort rightSort = new QuickSort(a, partitionIndex+1, high);
					invokeAll(leftSort, rightSort);
					// or, instead of invokeAll, we can write :
					//rightSort.fork();
					//leftSort.compute();
					//rightSort.join();
				}
			}
		}

		private int partition() 
		{
			Comparable pivot = a[high];

			int i = (low-1);

			for (int j = low; j < high; j++)
			{
				if (a[j].compareTo(pivot) <= 0) 
				{
					i++;

					Comparable swapTemp = a[i];
					a[i] = a[j];
					a[j] = swapTemp;
				}
			}

			Comparable swapTemp = a[i+1];
			a[i+1] = a[high];
			a[high] = swapTemp;

			return i+1;
		}


		private void insertionsort() 
		{
			for (int p = low + 1; p <= high; p++) 
			{
				Comparable tmp = a[p];
				int j;

				for (j = p; j > low && tmp.compareTo(a[j - 1]) < 0; j--)
					a[j] = a[j - 1];
				a[j] = tmp;
			}
		}

	}


	//////////////////////////////////////////////////////// quicksort end



	//////////////////////////////////////////////////////// mergesort begin

	static class MergeSort extends RecursiveAction
	{

		private Comparable[] a;
		private int low, high;

		public MergeSort(Comparable[] array, int l, int h) { // constructor
			if(array == null)
			{
				System.out.println("Pointer to the input array is NULL!\n");
				return;
			}
			a = array;
			low = l;
			high = h;
		}

		// the method where parallel computing will occur
		@Override
		protected void compute()
		{
			if (low + THRESHOLD > high)
				insertionsort(low, high);

			else 
			{
				if (low < high) {   // subdivide, fork-compute-join

					int mid = low + (high-low)/2; // find mid

					MergeSort leftSort = new MergeSort(a, low, mid); // sort left half
					MergeSort rightSort = new MergeSort(a, mid + 1, high); // sort right half
					invokeAll(leftSort, rightSort);
					merge(low, mid, high); // merge the sorted halves
				}
			}
		}


		// Merges two subarrays : arr[low:mid] and arr[mid+1:high]
		private void merge(int low, int mid, int high)
		{
			// Find sizes of two subarrays to be merged
			int size1 = mid - low + 1;
			int size2 = high - mid;

			// Create temporary arrays 
			Comparable[] left = new Comparable[size1];
			Comparable[] right = new Comparable[size2];

			// Copy data to temporary arrays
			for (int i = 0; i < size1; ++i)
				left[i] = a[low + i];
			for (int j = 0; j < size2; ++j)
				right[j] = a[mid + 1 + j];

			// Merge the temporary arrays 

			// Initial indexes of first and second subarrays
			int i = 0, j = 0;

			// Initial index of merged subarray array
			int k = low;
			while (i < size1 && j < size2) 
			{
				if (left[i].compareTo(right[j]) <= 0) 
				{
					a[k] = left[i];
					i++;
				}
				else 
				{
					a[k] = right[j];
					j++;
				}
				k++;
			}

			// Copy remaining elements of left[] if any 
			while (i < size1) {
				a[k] = left[i];
				i++;
				k++;
			}

			// Copy remaining elements of right[] if any 
			while (j < size2) {
				a[k] = right[j];
				j++;
				k++;
			}
		}

		private void insertionsort(int low, int high) 
		{
			for (int p = low + 1; p <= high; p++) 
			{
				Comparable tmp = a[p];
				int j;

				for (j = p; j > low && tmp.compareTo(a[j - 1]) < 0; j--)
					a[j] = a[j - 1];
				a[j] = tmp;
			}
		}

	}

	//////////////////////////////////////////////////////// mergesort end



	static Integer[] createArray(final int size) {
		Integer[] array = new Integer[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(1000);
		}
		return array;
	}


	//////////////////////////////////////////////////////// main begin


	public static void main(String[] args) throws InterruptedException  
	{
		//int proc = Runtime.getRuntime().availableProcessors();  
		//System.out.println("numbers of core available in your processor:"  + proc);  

		// create a pool of threads
		ForkJoinPool fjp = new ForkJoinPool();

		long startTime;
		long endTime;

		// Create two arrays of size ARRAY_SIZE, with the same randomly generated elements
		Integer[] array1, array2;

		try {
			array1 = createArray(ARRAY_SIZE);
			array2 = new Integer[ARRAY_SIZE];
			for (int i = 0; i < ARRAY_SIZE; i++) {
				array2[i] = array1[i];
			}
		}catch(OutOfMemoryError e)
		{
			System.out.println("Out of memory error!\n");
			//e.printStackTrace();
			return;
		}

		MergeSort mergeSort = new MergeSort(array1, 0, array1.length - 1);

		// Get the current time before sorting
		startTime = System.currentTimeMillis();

		try {
			fjp.invoke(mergeSort);
		}catch(StackOverflowError e)
		{
			System.out.println("Stack overflow error!\n");
			//e.printStackTrace();
			return;
		}catch(OutOfMemoryError e)
		{
			System.out.println("Out of memory error!\n");
			//e.printStackTrace();
			return;
		}

		// Get the current time after sorting
		endTime = System.currentTimeMillis();

		System.out.println("Time taken with MergeSort: " + 
				(endTime - startTime) + " millis");



		///////////////



		QuickSort quickSort = new QuickSort(array2, 0, array2.length - 1);

		// Get the current time before sorting
		startTime = System.currentTimeMillis();

		try {
			fjp.invoke(quickSort);
		}catch(StackOverflowError e)
		{
			System.out.println("Stack overflow error!\n");
			//e.printStackTrace();
			return;
		}catch(OutOfMemoryError e)
		{
			System.out.println("Out of memory error!\n");
			//e.printStackTrace();
			return;
		}


		// Get the current time after sorting
		endTime = System.currentTimeMillis();

		System.out.println("Time taken with QuickSort: " + 
				(endTime - startTime) + " millis");



	}
}

	//////////////////////////////////////////////////////// main end