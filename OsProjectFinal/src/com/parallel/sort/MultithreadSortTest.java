package com.parallel.sort;



import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import org.junit.Assert;
import org.junit.Test;

import com.parallel.sort.MultithreadSort.MergeSort;
import com.parallel.sort.MultithreadSort.QuickSort;



public class MultithreadSortTest {
	

	ForkJoinPool testPool = new ForkJoinPool();
	MultithreadSort multithreadedtest;
	
	//creating an array of random size
	private static Integer[] createTestArray(final int size) {
		Integer[] array = new Integer[size];
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = rand.nextInt(1000);
		}
		return array;
	}
	
	//Create Mixed Integer Array//
	private static Integer[] createMixTestArray(final int size, final int low, final int high) {
			Integer[] array = new Integer[size];
			@SuppressWarnings("unused")
			Random rand = new Random();
			for (int i = 0; i < size; i++) {
				array[i] = (int)((Math.random() * (high-low)) + low);
			}
			return array;
	}
	
    //Printing of an array
	/*private void printForMe(Integer[] arr) {
		for(int n:arr){	
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.print("\n");
	}*/
	
	
	///////////////////////////.....................Quick Sort testing................////////////////////////
	

	//1-Testing quick sort with an empty array
	@Test
	public void test_quick_sort_with_empty_array() {
		Integer[] randomTestArray = { };
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
			
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
			
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr,randomTestArray);
		
	}
		

	//2-Testing Quick sort on an array of random size even	
	@Test
	public void test_quick_sort_should_return_sorted_array_random_even() {
		Integer[] randomTestArray = createTestArray(500);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
	
	}
	
		
	//3-Testing quick sort on an array of random size odd
	@Test
	public void test_quick_sort_should_return_sorted_array_random_odd() {
		Integer[] randomTestArray = createTestArray(499);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
			
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
			
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
		
	}
		
			
	//4-Testing quick sort on an array of negative integers of random size even
	@Test
	public void test_quick_sort_should_return_sorted_array_negative_even() {
		Integer[] randomTestArray = createMixTestArray(5000, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr,randomTestArray);
	
	}
	
	
	//5-Testing quick sort on an array of negative integers of random size odd
	@Test
	public void test_quick_sort_should_return_sorted_array_negative_even_odd() {
		Integer[] randomTestArray = createMixTestArray(501, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr,randomTestArray);
	
	}
	
		
	//6-Testing quick sort with an array containing both positive and negative numbers.
	@Test
	public void test_quick_sort_with_Mix_of_Positive_and_negative_array() {
		Integer[] randomTestArray = createMixTestArray(100, -300, 150);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr,randomTestArray);
	}
	
	
	//7- Testing quick sort with an array already sorted in descending order with mixed integers
	@Test
	public void test_quick_sort_with_array_in_descending_order() {
		Integer[] randomTestArray = createMixTestArray(500, -300, 150);
		Arrays.sort(randomTestArray, Collections.reverseOrder());
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(sampleArr);
		
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
		
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
		
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
	
	}
	
	
	//8- Testing quick sort with an array already sorted in ascending order with mixed integers
	@Test
	public void test_quick_sort_with_array_in_ascending_order() {
		Integer[] testArray = createMixTestArray(500, -5000, 9500);
		Arrays.sort(testArray);
		Integer[] randomTestArray = testArray.clone();
			
			
		//Arrays.sort(randomTestArray, Collections.reverseOrder());
		//Integer[] sampleArr = randomTestArray.clone();
		//printForMe(nonsorted);
			
		QuickSort quickSortSTU = new QuickSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(quickSortSTU);
			
		//printForMe(randomTestArray);
		//Arrays.sort(sampleArr);
			
		//String sortedArray = Arrays.toString(testArray);
		Assert.assertArrayEquals(testArray,randomTestArray);
		
	}
	
	
	
	
	 //////////////////////////////...........Testing Merge Sort...............////////////////////////////////////
	
	
	//1-Testing Merge sort with an empty array
	@Test
	public void test_merge_sort_with_empty_array() {
		Integer[] randomTestArray = { };
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		String sortedArray = Arrays.toString(sampleArr);
		Assert.assertEquals(sortedArray, Arrays.toString(randomTestArray));
				
	}
		
	//2-Testing Merge sort on an array of random size even	
	@Test
	public void test_merge_sort_should_return_sorted_array_random_even() {
		Integer[] randomTestArray = createTestArray(500);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
			
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
			
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
			
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
		
	}
		
		
	//3-Testing Merge Sort on an array of random size odd
	@Test
	public void test_merge_sort_should_return_sorted_array_random_odd() {
		Integer[] randomTestArray = createTestArray(1001);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		//String sortedArray = Arrays.toString(sampleArr);
		Assert.assertArrayEquals(sampleArr, randomTestArray);
				
	}
		
	
	//4-Testing Merge sort on an array of negative integers of random size even
	@Test
	public void test_merge_sort_should_return_sorted_array_negative_even() {
		Integer[] randomTestArray = createMixTestArray(9990, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		//String sortedArray = Arrays.toString(testArray);
		Assert.assertArrayEquals(sampleArr,randomTestArray);
	}
	
	
	//5-Testing Merge sort on an array of negative integers of random size odd
	@Test
	public void test_merge_sort_should_return_sorted_array_negative_even_odd() {
		Integer[] randomTestArray = createMixTestArray(999, -100000, -8);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		//String sortedArray = Arrays.toString(testArray);
		Assert.assertArrayEquals(sampleArr,randomTestArray);
	}
				
	
	//6-Testing merge sort with an array containing both positive and negative numbers.
	@Test
	public void test_merge_sort_with_Mix_of_Positive_and_negative_array() {
		Integer[] randomTestArray = createMixTestArray(6580, -300, 150);
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(randomTestArray);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		//String sortedArray = Arrays.toString(testArray);
		Assert.assertArrayEquals(sampleArr,randomTestArray);	
	}
	
	
	//7- Testing merge sort with an array already sorted in descending order with mixed integers
	@Test
	public void test_merge_sort_with_array_in_descending_order() {
		Integer[] randomTestArray = createMixTestArray(599, -300, 150);
		Arrays.sort(randomTestArray, Collections.reverseOrder());
		Integer[] sampleArr = randomTestArray.clone();
		//printForMe(sampleArr);
					
		MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		Arrays.sort(sampleArr);
					
		//String sortedArray = Arrays.toString(testArray);
		Assert.assertArrayEquals(sampleArr,randomTestArray);
						
	}
				
	//8- Testing merge sort with an array already sorted in ascending order with mixed integers
	@Test
	public void test_merge_sort_with_array_in_ascending_order() {
		Integer[] testArray = createMixTestArray(799, -5000, 9500);
		Arrays.sort(testArray);
		Integer[] randomTestArray = testArray.clone();
					
					
		//Arrays.sort(randomTestArray, Collections.reverseOrder());
		//Integer[] sampleArr = randomTestArray.clone();
		//printForMe(nonsorted);
					
		 MergeSort mergeSortSTU = new MergeSort(randomTestArray, 0, randomTestArray.length - 1);
		testPool.invoke(mergeSortSTU);
					
		//printForMe(randomTestArray);
		//Arrays.sort(sampleArr);
					
		//String sortedArray = Arrays.toString(testArray);
		Assert.assertArrayEquals(testArray,randomTestArray);
				
	}
	
	////...................Testing Create array module............////////////
	
	@Test
	public void test_create_array_function() {
		new MultithreadSort();
		Integer[] testArray = MultithreadSort.createArray(1000);
		Assert.assertEquals(1000, Array.getLength(testArray));
	}
	
	
	
	////////..................Exceptions............../////////////
	
	//Testing out of memory exception
	 /*@Before
	    public void init() {
	        multithreadSort = new MultithreadSort();
	    }
	 @After
	    public void tearDown() {
	        multithreadSort = null;
	    }
	 @Test(expected = Error.class)
	    public void test_Stack_overflow_Error() {
		 Integer[] testArray = createMixTestArray(100000000, -5000, 9500);
		 QuickSort quickSortSTU = new QuickSort(testArray, 0, testArray.length - 1);
		 testPool.invoke(quickSortSTU);
	    }
	
	//9-Testing with array value as null
		@Test(expected = NullPointerException.class)
		public void test_quick_sort_null() {
			Integer[] testArray = null; 
			QuickSort quickSortSTU = new QuickSort(testArray, 0, 1);
			testPool.invoke(quickSortSTU);

			//Assert.assertEquals(null, Arrays.toString(testArray));
		}*/
			
	 
	 

}
		
	



