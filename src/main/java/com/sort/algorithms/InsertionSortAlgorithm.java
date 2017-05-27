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
 * Provides an implementation of the insertion sort algorithm
 * @author Deepak Shajan
 *
 */
@SuppressWarnings("rawtypes")
public final class InsertionSortAlgorithm<T extends Comparable> implements SortAlgorithm<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T[] logic(T[] array) {

		T temp;
		int length = array.length;
		for (int i=1;i<length;i++)
			for(int j=i;j>0;j--)
				if(array[j].compareTo(array[j-1])<0){
					temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
		return array;
	}

}
