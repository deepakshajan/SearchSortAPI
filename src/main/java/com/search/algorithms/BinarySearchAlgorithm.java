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

package com.search.algorithms;

/**
 * An implementation of the binary search algorithm.
 * The array or list to be sorted must be a sorted one in ascending order.
 * <p><b>Will return unexpected values if unsorted arrays or lists are passed.
 * @author Deepak Shajan
 *
 */
@SuppressWarnings("rawtypes")
public final class BinarySearchAlgorithm<T extends Comparable> implements SearchAlgorithm<T> {

	@SuppressWarnings("unchecked")
	@Override
	public int logic(T element, T[] array) {

		int length = array.length;
		int startPos = 0,endPos = length-1;
		int middlePos = (endPos-startPos)/2;
		while(array[middlePos].compareTo(element)!=0) {
			if(array[startPos].compareTo(element)>0||array[endPos].compareTo(element)<0)
				return -1;
			else if(array[middlePos].compareTo(element)>0)
				endPos = middlePos-1;
			else
				startPos = middlePos+1;
			middlePos = startPos+(endPos-startPos)/2;
		}
		return middlePos;
	}

}
