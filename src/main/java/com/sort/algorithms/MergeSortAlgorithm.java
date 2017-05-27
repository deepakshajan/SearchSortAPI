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

import java.lang.reflect.Array;

/**
 * Provides implementation for merge sort algorithm
 * @author Deepak Shajan
 *
 */
@SuppressWarnings("rawtypes")
public final class MergeSortAlgorithm<T extends Comparable> implements SortAlgorithm<T>{

	@Override
	public T[] logic(T[] array) {
		int length = array.length-1;
		return sort(array, 0, length);
	}

	private T[] sort(T[] array, int beg, int end) {
		if (beg < end) {
			int middle = (beg+end)/2;
			sort(array, beg, middle);
			sort(array, middle+1, end);
			merge(array, beg, middle, end);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	private T[] merge(T[] array, int beg, int middle, int end){

		int n1 = middle - beg + 1;
		int n2 = end - middle;
		T[] L = (T[]) Array.newInstance(array[0].getClass(), n1);
		T[] R = (T[]) Array.newInstance(array[0].getClass(), n2);

		for (int i=0; i<n1; ++i)
			L[i] = array[beg+i];
		for (int j=0; j<n2; ++j)
			R[j] = array[middle+1+j];

		int i = 0, j = 0;
		int k = beg;

		while (i<n1 && j<n2)
			if (L[i].compareTo(R[j])<=0)
				array[k++] = L[i++];
			else
				array[k++] = R[j++];

		while (i < n1)
			array[k++] = L[i++];

		while (j < n2)
			array[k++] = R[j++];

		return array;
	}


}

