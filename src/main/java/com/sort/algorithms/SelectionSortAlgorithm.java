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
 * Provides the implementation for performing a selection sort
 * @author Deepak Shajan
 *
 */
@SuppressWarnings("rawtypes")
public final class SelectionSortAlgorithm<T extends Comparable> implements SortAlgorithm<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T[] logic(T[] array) {

		int jSize = array.length;
		int iSize = jSize-1;
		for(int i=0;i<iSize;i++)
			for(int j=i+1;j<jSize;j++)
				if(array[i].compareTo(array[j])>0) {
					T temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
	return array;
	}

}
