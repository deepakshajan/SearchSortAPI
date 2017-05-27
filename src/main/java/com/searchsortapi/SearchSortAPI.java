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

package com.searchsortapi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.search.algorithms.SearchAlgorithm;
import com.search.algorithms.factory.BinarySearchAlgorithmFactory;
import com.search.algorithms.factory.LinearSearchAlgorithmFactory;
import com.searchsortapi.adapter.InputAdapter;
import com.searchsortapi.exceptions.SearchAlgorithmException;
import com.searchsortapi.exceptions.SortAlgorithmException;
import com.searchsortapi.factory.AlgorithmFactory;
import com.sort.algorithms.SortAlgorithm;
import com.sort.algorithms.factory.BubbleSortAlgorithmFactory;
import com.sort.algorithms.factory.HeapSortAlgorithmFactory;
import com.sort.algorithms.factory.InsertionSortAlgorithmFactory;
import com.sort.algorithms.factory.MergeSortAlgorithmFactory;
import com.sort.algorithms.factory.SelectionSortAlgorithmFactory;

/**
 * Facade class that contains static methods that can be used for searching and sorting a list or an array
 * 
 * <p>Class can be instantiated using the default constructor {@link #SearchSortAPI()} or the static method {@link #getBuilder()}. The default sorting algorithm is <b>Selection Sort</b> 
 * and the default searching algorithm is <b>Linear Search</b> 
 * <p><b>Refer the SampleClient.java source code for examples of usage</b>
 * @author Deepak Shajan
 *
 */
public final class SearchSortAPI {

	/**
	 * <p>If set to true -> Sorting is done in the reverse order from the below mentioned.
	 * <p>If set to false -> Sorting is done in the order mentioned in the compare method of the objects.
	 * <p><b>Note : There is a performance overhead for reversal.It is recommended to suitably modify the compare method of the class instead of opting for setting this option true.
	 */
	private boolean isSortReversed;

	/**
	 * The algorithm to be used for the search operation
	 */
	@SuppressWarnings("rawtypes")
	private SearchAlgorithm searchAlgorithm = null;

	/**
	 * The algorithm to be used for the sort operation
	 */
	@SuppressWarnings("rawtypes")
	private SortAlgorithm sortAlgorithm = null;
	
	private SearchSortAPIBuilder builder = null;

	public SearchSortAPI() {
		this.searchAlgorithm = AlgorithmFactory.getSearchAlgorithm(new LinearSearchAlgorithmFactory());
		this.sortAlgorithm = AlgorithmFactory.getSortAlgorithm(new SelectionSortAlgorithmFactory());
	}
	
	/**
	 * To construct a {@link SearchSortAPI}
	 * @param builder
	 */
	private SearchSortAPI(SearchSortAPIBuilder builder) {
		this.isSortReversed = builder.isSortReversed;
		this.searchAlgorithm = builder.searchAlgorithm;
		this.sortAlgorithm = builder.sortAlgorithm;
	}
	
	/**
	 * Returns a singleton instance of the SearchSortAPI builder.
	 * <p>Various properties like <ul><li>Search Algorithm<li>Sort Algorithm<li>Reverse Sort</ul> can be set on this instance.
	 * @return singleton instance of builder
	 */
	public SearchSortAPIBuilder getBuilder() { 
		if(null==builder)
			synchronized (this) {
				if(null==builder)
					this.builder = new SearchSortAPIBuilder().setSelectionSortAlgorithm().setLinearSearchAlgorithm().forwardSort();
			}
		return this.builder;
	}

	/**
	 * Searches for the first occurrence of the element in the passed array
	 * @param element -> item to be searched for
	 * @param array -> array in which the element is to be searched
	 * @return index of the first occurrence of the element in the array, -1 if the element is not found
	 */
	@SuppressWarnings("unchecked")
	public <T> int search(T element, T[] array) {
		if(null==array||array.length==0)
			return -1;
		else if(null==searchAlgorithm)
			throw new SearchAlgorithmException(" ERROR -> searchAlgorithm cannot be assigned with null");
		return searchAlgorithm.logic(element, array);
	}

	/**
	 * Searches for the first occurrence of the element in the passed list
	 * @param element -> item to be searched for
	 * @param list -> list in which the element is to be searched
	 * @return index of the first occurrence of the element in the list, -1 if the element is not found
	 */
	@SuppressWarnings("unchecked")
	public <T> int search(T element, List<T> list) {
		if(null==list||list.size()==0)
			return -1;
		else if(null==searchAlgorithm)
			throw new SearchAlgorithmException(" ERROR -> searchAlgorithm cannot be assigned with null");
		return searchAlgorithm.logic(element, InputAdapter.toArray(list));
	}

	/**
	 * Sorts the passed Array
	 * @param unSortedArray -> the array that needs to be sorted
	 * @return sorted array
	 */
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> T[] sort(T[] unSortedArray) {
		if(null==unSortedArray||unSortedArray.length==0)
			return unSortedArray;
		if(null==sortAlgorithm)
			throw new SortAlgorithmException(" ERROR -> sortAlgorithm cannot be assigned with null");
		if (isSortReversed)
			return (T[]) reverse(sortAlgorithm.logic(unSortedArray));
		else
			return (T[]) sortAlgorithm.logic(unSortedArray);
	}

	/**
	 * Sorts the passed List
	 * @param unSortedList -> the list that needs to be sorted
	 * @return sorted List
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends Comparable> List<T> sort(List<T> unSortedList) {
		if(null==unSortedList||unSortedList.size()==0)
			return unSortedList;
		if(null==sortAlgorithm)
			throw new SortAlgorithmException(" ERROR -> sortAlgorithm cannot be assigned with null");
		T[] unsortedArray = InputAdapter.toArray(unSortedList);
		return isSortReversed?reverse(Arrays.asList((T[]) sortAlgorithm.logic(unsortedArray))):Arrays.asList((T[]) sortAlgorithm.logic(unsortedArray));
	}

	public static final class SearchSortAPIBuilder {

		/**
		 * @author Deepak Shajan
		 * Builder class for SearchSortAPI used to set the various properties of the SearchSortAPI class
		 */

		private boolean isSortReversed;

		@SuppressWarnings("rawtypes")
		private SearchAlgorithm searchAlgorithm = null;

		@SuppressWarnings("rawtypes")
		private SortAlgorithm sortAlgorithm = null;

		public SearchSortAPIBuilder reverseSort() {
			this.isSortReversed = true;
			return this;
		}
		public SearchSortAPIBuilder forwardSort() {
			this.isSortReversed = false;
			return this;
		}
		@SuppressWarnings("rawtypes")
		public SearchSortAPIBuilder setCustomSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
			this.searchAlgorithm = searchAlgorithm;
			return this;
		}
		@SuppressWarnings("rawtypes")
		public SearchSortAPIBuilder setCustomSortAlgorithm(SortAlgorithm sortAlgorithm) {
			this.sortAlgorithm = sortAlgorithm;
			return this;
		}
		public SearchSortAPIBuilder setLinearSearchAlgorithm() {
			this.searchAlgorithm = AlgorithmFactory.getSearchAlgorithm(new LinearSearchAlgorithmFactory());
			return this;
		}
		public SearchSortAPIBuilder setBinarySearchAlgorithm() {
			this.searchAlgorithm = AlgorithmFactory.getSearchAlgorithm(new BinarySearchAlgorithmFactory());
			return this;
		}
		public SearchSortAPIBuilder setSelectionSortAlgorithm() {
			this.sortAlgorithm = AlgorithmFactory.getSortAlgorithm(new SelectionSortAlgorithmFactory());
			return this;
		}
		public SearchSortAPIBuilder setBubbleSortAlgorithm() {
			this.sortAlgorithm = AlgorithmFactory.getSortAlgorithm(new BubbleSortAlgorithmFactory());
			return this;
		}
		public SearchSortAPIBuilder setInsertionSortAlgorithm() {
			this.sortAlgorithm = AlgorithmFactory.getSortAlgorithm(new InsertionSortAlgorithmFactory());
			return this;
		}
		public SearchSortAPIBuilder setMergeSortAlgorithm() {
			this.sortAlgorithm = AlgorithmFactory.getSortAlgorithm(new MergeSortAlgorithmFactory());
			return this;
		}
		public SearchSortAPIBuilder setHeapSortAlgorithm() {
			this.sortAlgorithm = AlgorithmFactory.getSortAlgorithm(new HeapSortAlgorithmFactory());
			return this;
		}

		public SearchSortAPI build() {
			return new SearchSortAPI(this);
		}

	}

	/**
	 * Reverses the passed Array
	 * @param array -> the array to be reversed
	 * @return reversed array
	 */
	private <T> T[] reverse(T[] array) {
		int length = array.length-1, revIndex;
		for(int i=0;i<=length/2;i++) {
			revIndex = length-i;
			T temp = array[i];
			array[i] = array[revIndex];
			array[revIndex] = temp;
		}
		return array;
	}

	/**
	 * Reverse the passed List
	 * @param list -> the list to be reversed
	 * @return reversed list
	 */
	private <T> List<T> reverse(List<T> list) {
		Collections.reverse(list);
		return list;
	}

}
