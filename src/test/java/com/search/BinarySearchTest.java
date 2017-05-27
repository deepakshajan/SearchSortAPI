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

package com.search;

import java.util.List;	

import com.searchsortapi.SearchSortAPI;
import com.searchsortapi.TestCaseWrapperLevel2;

/**
 * jUnit test class for binary search algorithm
 * <p> Since binary search requires sorted inputs for its operation , the various test methods are overridden to provide implementations different  from the {@link TestCaseWrapperLevel1} class. 
 * @author Deepak Shajan
 *
 */
public class BinarySearchTest extends TestCaseWrapperLevel2 {

	@Override
	protected void setUp() throws Exception {
		api = new SearchSortAPI().getBuilder().setBinarySearchAlgorithm().build();
	}

	@Override
	public void testSortIntegerArray() {
		xAssertIntegerArray(api.sort(nInputArray),false);
		api = api.getBuilder().reverseSort().build();
		xAssertIntegerArray(api.sort(nInputArray),true);
	}

	@Override
	public void testSortIntegerList() {
		xAssertInegerList(api.sort(nInputList),false);
		api = api.getBuilder().reverseSort().build();
		xAssertInegerList(api.sort(nInputList),true);
	}

	@Override
	public void testSearchIntegerArray() {
		Integer[] input = api.sort(nInputArray);
		assertEquals(21, api.search(200, input));
		assertEquals(11, api.search(25, input));
		assertEquals(31, api.search(75511446, input));
		assertEquals(3, api.search(-499, input));
		assertEquals(0, api.search(-120455, input));
		assertEquals(-1, api.search(1055, input));
		assertEquals(-1, api.search(-1055, input));
	}

	@Override
	public void testSearchIntegerList() {
		List<Integer> input = api.sort(nInputList);
		assertEquals(21, api.search(200, input));
		assertEquals(11, api.search(25, input));
		assertEquals(31, api.search(75511446, input));
		assertEquals(3, api.search(-499, input));
		assertEquals(0, api.search(-120455, input));
		assertEquals(-1, api.search(1055, input));
		assertEquals(-1, api.search(-1055, input));	
	}
	
	@Override
	public void testSortStringArray() {
		xAssertStringArray(api.sort(strInputArray),false);
		api = api.getBuilder().reverseSort().build();
		xAssertStringArray(api.sort(strInputArray),true);
	}

	@Override
	public void testSortStringList() {
		xAssertStringList(api.sort(strInputList),false);
		api = api.getBuilder().reverseSort().build();
		xAssertStringList(api.sort(strInputList),true);
	}

	@Override
	public void testSearchStringArray() {
		String[] input = api.sort(strInputArray);
		assertEquals(10, api.search("India", input));
		assertEquals(26, api.search("Sri Lanka", input));
		assertEquals(18, api.search("Norway", input));
		assertEquals(30, api.search("USA", input));
		assertEquals(14, api.search("Madagascar", input));
		assertEquals(-1, api.search("Bhutan", input));
		assertEquals(-1, api.search("india", input));
	}

	@Override
	public void testSearchStringList() {
		String[] input = api.sort(strInputArray);
		assertEquals(10, api.search("India", input));
		assertEquals(26, api.search("Sri Lanka", input));
		assertEquals(18, api.search("Norway", input));
		assertEquals(30, api.search("USA", input));
		assertEquals(14, api.search("Madagascar", input));
		assertEquals(-1, api.search("Bhutan", input));
		assertEquals(-1, api.search("india", input));
	}
	
}
