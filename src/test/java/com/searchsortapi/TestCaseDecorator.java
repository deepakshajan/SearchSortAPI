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
import java.util.List;

import com.sort.SelectionSortTest;

import junit.framework.TestCase;

/**
 * Wrapper/Decorator class to add attributes and implementations to the actual jUnit {@link TestCase} class
 * <p> All jUnit test classes should extend this class instead of the actual {@link TestCase} class provided by jUnit.
 * This class({@link TestCaseDecorator}) provides the purpose of abstracting the test case execution logic from the actual test case classes.
 * <p> In case of adding unit test for a normal search/sort algorithm to the existing implementation, all thats to be done is to create a class that extends this class({@link TestCaseDecorator}) and provide
 * the implementation for the abstract method {@link #setUp()}.An example for this can be found in the {@link SelectionSortTest} class. 
 * @author Deepak Shajan
 *
 */
public abstract class TestCaseDecorator extends TestCase {

	/**
	 * The instance of {@link SearchSortAPI} on which the various test cases are executed.
	 * <p> All the jUnit test cases in this application is being executed on this instance.
	 */
	protected SearchSortAPI api = null;

	protected final Integer[] nInputArray = new Integer[] {85,78,44,55,102,20011,-1,75511446,25,8,0,5,102,-499,857,44,56,-105,-1,200,25,785,201,5688,-499,-1006,1466,2045,25,785,412,-120455};

	protected final Integer[] nOutputArray = new Integer[] {-120455,-1006,-499,-499,-105,-1,-1,0,5,8,25,25,25,44,44,55,56,78,85,102,102,200,201,412,785,785,857,1466,2045,5688,20011,75511446};

	protected final Integer[] nOutputArrayReverse = new Integer[] {75511446,20011,5688,2045,1466,857,785,785,412,201,200,102,102,85,78,56,55,44,44,25,25,25,8,5,0,-1,-1,-105,-499,-499,-1006,-120455}; 

	protected final List<Integer> nInputList = Arrays.asList(nInputArray);

	protected final List<Integer> nOutputList = Arrays.asList(nOutputArray);

	protected final List<Integer> nOutputListReverse = Arrays.asList(nOutputArrayReverse);

	protected final String[] strInputArray = new String[] {"India","Germany","China","Russia","Oman","South Africa","Sri Lanka","Bahrain","Argentina","Sweden","Japan","Chile","Brazil","Zimbabwe","USA",
			"Madagascar","France","UK","Nepal","Thailand","Canada","Egypt","Australia","Mexico","Peru","Iraq","Italy","Malaysia","Pakisthan","Rome","Spain","Norway",};

	protected final String[] strOutputArray = new String[] {"Argentina","Australia","Bahrain","Brazil","Canada","Chile","China","Egypt","France","Germany","India","Iraq","Italy","Japan","Madagascar",
			"Malaysia","Mexico","Nepal","Norway","Oman","Pakisthan","Peru","Rome","Russia","South Africa","Spain","Sri Lanka","Sweden","Thailand","UK","USA","Zimbabwe" };

	protected final String[] strOutputArrayReverse = new String[] {"Zimbabwe","USA","UK","Thailand","Sweden","Sri Lanka","Spain","South Africa","Russia","Rome","Peru","Pakisthan","Oman","Norway",
			"Nepal","Mexico","Malaysia","Madagascar","Japan","Italy","Iraq","India","Germany","France","Egypt","China","Chile","Canada","Brazil","Bahrain","Australia","Argentina"};

	protected final List<String> strInputList = Arrays.asList(strInputArray);

	protected final List<String> strOutputList = Arrays.asList(strOutputArray);

	protected final List<String> strOutputListReverse = Arrays.asList(strOutputArrayReverse);


	/**
	 * Use this method to setup the {@link #api} variable for each jUnit test class.
	 * <p> The various pre-defined test case methods such as {@link #testSearchIntegerArray()},{@link#testSortIntegerList()} in {@link TestCaseDecorator} class gets executed using the {@link #api} variable 
	 * as it is configured in this method.
	 */
	@Override
	protected abstract void setUp() throws Exception;

	@Override
	protected void tearDown() throws Exception {
		api = null; // runs after the execution of each test method
	}


	public void testSortIntegerArray() {
		xAssertIntegerArray(api.sort(nInputArray),false);
		api = api.getBuilder().reverseSort().build();
		xAssertIntegerArray(api.sort(nInputArray),true);
	}

	public void testSortIntegerList() {
		xAssertInegerList(api.sort(nInputList),false);
		api = api.getBuilder().reverseSort().build();
		xAssertInegerList(api.sort(nInputList),true);
	}

	public void testSearchIntegerArray() {
		assertEquals(19, api.search(200, nInputArray));
		assertEquals(8, api.search(25, nInputArray));
		assertEquals(7, api.search(75511446, nInputArray));
		assertEquals(13, api.search(-499, nInputArray));
		assertEquals(31, api.search(-120455, nInputArray));
		assertEquals(-1, api.search(1055, nInputArray));
		assertEquals(-1, api.search(-1055, nInputArray));
	}

	public void testSearchIntegerList() {
		assertEquals(19, api.search(200, nInputList));
		assertEquals(8, api.search(25, nInputList));
		assertEquals(7, api.search(75511446, nInputList));
		assertEquals(13, api.search(-499, nInputList));
		assertEquals(31, api.search(-120455, nInputList));
		assertEquals(-1, api.search(1055, nInputList));
		assertEquals(-1, api.search(-1055, nInputList));
	}

	public void testSortStringArray() {
		xAssertStringArray(api.sort(strInputArray),false);
		api = api.getBuilder().reverseSort().build();
		xAssertStringArray(api.sort(strInputArray),true);
	}

	public void testSortStringList() {
		xAssertStringList(api.sort(strInputList),false);
		api = api.getBuilder().reverseSort().build();
		xAssertStringList(api.sort(strInputList),true);
	}

	public void testSearchStringArray() {
		assertEquals(0, api.search("India", strInputArray));
		assertEquals(6, api.search("Sri Lanka", strInputArray));
		assertEquals(31, api.search("Norway", strInputArray));
		assertEquals(14, api.search("USA", strInputArray));
		assertEquals(15, api.search("Madagascar", strInputArray));
		assertEquals(-1, api.search("Bhutan", strInputArray));
		assertEquals(-1, api.search("india", strInputArray));
	}

	public void testSearchStringList() {
		assertEquals(0, api.search("India", strInputList));
		assertEquals(6, api.search("Sri Lanka", strInputList));
		assertEquals(31, api.search("Norway", strInputList));
		assertEquals(14, api.search("USA", strInputList));
		assertEquals(15, api.search("Madagascar", strInputList));
		assertEquals(-1, api.search("Bhutan", strInputList));
		assertEquals(-1, api.search("india", strInputList));
	}



	protected void xAssertIntegerArray(Integer[] actualOutput, boolean reverseMode) {
		int length = actualOutput.length;
		Integer[] expectedOutput = reverseMode?nOutputArrayReverse:nOutputArray;
		for(int i=0;i<length;i++)
			assert expectedOutput[i].equals(actualOutput[i]);
	}

	protected void xAssertInegerList(List<Integer> actualOutput, boolean reverseMode) {
		int size = actualOutput.size();
		List<Integer> expectedOutput = reverseMode?nOutputListReverse:nOutputList;
		for(int i=0;i<size;i++)
			assert expectedOutput.get(i).equals(actualOutput.get(i));
	}

	protected void xAssertStringArray(String[] actualOutput, boolean reverseMode) {
		int length = actualOutput.length;
		String[] expectedOutput = reverseMode?strOutputArrayReverse:strOutputArray;
		for(int i=0;i<length;i++)
			assert expectedOutput[i].equals(actualOutput[i]);
	}

	protected void xAssertStringList(List<String> actualOutput, boolean reverseMode) {
		int size = actualOutput.size();
		List<String> expectedOutput = reverseMode?strOutputListReverse:strOutputList;
		for(int i=0;i<size;i++)
			assert expectedOutput.get(i).equals(actualOutput.get(i));
	}

}
