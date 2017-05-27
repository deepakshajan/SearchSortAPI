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

package com.sample.client;

import java.util.Arrays;
import java.util.List;

import com.searchsortapi.SearchSortAPI;

/**
 * A sample client class showing the different ways to operate the {@link SearchSortAPI} class.
 * @author Deepak Shajan
 *
 */
final class SampleClient {

	private static final List<Integer> unSortedList = Arrays.asList(3,7,9,4,1,6,2,8,5);

	public static void main(String... args) {

		// Default instantiation
		searchAndSortDefInst();

		//instantiation using builder
		searchAndSortUsingBuilder();

		//custom search and sort algorithm
		customSearchAndSort();
	}

	private static void searchAndSortDefInst() {

		System.out.println("****** Default Instantiation ******");

		SearchSortAPI api = new SearchSortAPI();

		int index = api.search(6, unSortedList);
		System.out.println("Index for element 6 is -> " + index);

		List<Integer> sortedList  = api.sort(unSortedList);
		System.out.println("The sorted list is printed below");
		sortedList.stream().forEach(System.out::print);
	}

	private static void searchAndSortUsingBuilder() {

		System.out.println("\n");
		System.out.println("****** Builder Instantiation ******");

		SearchSortAPI api = new SearchSortAPI().getBuilder().setLinearSearchAlgorithm().setMergeSortAlgorithm().reverseSort().build();

		int index = api.search(6, unSortedList);
		System.out.println("Index for element 6 is -> " + index);

		List<Integer> sortedList  = api.sort(unSortedList);
		System.out.println("The reverse sorted list is printed below");
		sortedList.stream().forEach(System.out::print);
	}

	private static void customSearchAndSort() {

		System.out.println("\n");
		System.out.println("****** Custom algorithm Instantiation ******");

		SearchSortAPI api = new SearchSortAPI().getBuilder().setCustomSortAlgorithm(array->{Arrays.sort(array);return array;})
				.setCustomSearchAlgorithm((element,array)->Arrays.binarySearch(Arrays.stream(array).sorted().toArray(), element)).build();

		int index = api.search(6, unSortedList);
		System.out.println("Index for element 6 is -> " + index);

		List<Integer> sortedList  = api.sort(unSortedList);
		System.out.println("The sorted list is printed below");
		sortedList.stream().forEach(System.out::print);
	}

}