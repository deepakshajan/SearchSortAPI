/**
 * MIT License

Copyright (c) 2017 deepakshajan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.sort.algorithms;

/**
 * Provides implementation for the heap sort algorithm
 * @author Deepak Shajan
 *
 */
@SuppressWarnings("rawtypes")
public final class HeapSortAlgorithm<T extends Comparable> implements SortAlgorithm<T>{

	private static int N;

	@Override
	public T[] logic(T[] array) {
		heapify(array);        
		for (int i = N; i > 0; i--) {
			swap(array,0, i);
			N = N-1;
			maxheap(array, 0);
		}
		return array;
	}

	@SuppressWarnings("unused")
	private void sort(T[] array) {    
		heapify(array);        
		for (int i = N; i > 0; i--) {
			swap(array,0, i);
			N = N-1;
			maxheap(array, 0);
		}
	}     

	private void heapify(T[] array) {
		N = array.length-1;
		for (int i=N/2;i >= 0;i--)
			maxheap(array, i);        
	}

	@SuppressWarnings("unchecked")
	private void maxheap(T[] array, int i) { 
		int left = 2*i ;
		int right = 2*i + 1;
		int max = i;
		if (left <= N && array[left].compareTo(array[i])>0)
			max = left;
		if (right <= N && array[right].compareTo(array[max])>0)
			max = right;
		if (max != i) {
			swap(array, i, max);
			maxheap(array, max);
		}
	}    

	private void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp; 
	}    

}
