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
 * Wrapper class to add attributes and implementations to the actual jUnit {@link TestCase} class
 * <p> All jUnit test classes should extend this class in any of the levels instead of the actual {@link TestCase} class provided by jUnit.
 * This class({@link TestCaseWrapperLevel2}) provides the purpose of abstracting the test case execution logic from the actual test case classes.
 * <p> In case of adding unit test for a normal search/sort algorithm to the existing implementation, all thats to be done is to create a class that extends 
 * the class({@link TestCaseWrapperLevel2}) <b>or any higher level class</b> and provide the implementation for the abstract method {@link #setUp()}.An example for this can be 
 * found in the {@link SelectionSortTest} class. 
 * @author Deepak Shajan
 *
 */
public abstract class TestCaseWrapperLevel2 extends TestCaseWrapperLevel1 {

	protected final String[] strInputArray = new String[] {"India","Germany","China","Russia","Oman","South Africa","Sri Lanka","Bahrain","Argentina","Sweden","Japan","Chile","Brazil","Zimbabwe","USA",
			"Madagascar","France","UK","Nepal","Thailand","Canada","Egypt","Australia","Mexico","Peru","Iraq","Italy","Malaysia","Pakisthan","Rome","Spain","Norway",};

	protected final String[] strOutputArray = new String[] {"Argentina","Australia","Bahrain","Brazil","Canada","Chile","China","Egypt","France","Germany","India","Iraq","Italy","Japan","Madagascar",
			"Malaysia","Mexico","Nepal","Norway","Oman","Pakisthan","Peru","Rome","Russia","South Africa","Spain","Sri Lanka","Sweden","Thailand","UK","USA","Zimbabwe" };

	protected final String[] strOutputArrayReverse = new String[] {"Zimbabwe","USA","UK","Thailand","Sweden","Sri Lanka","Spain","South Africa","Russia","Rome","Peru","Pakisthan","Oman","Norway",
			"Nepal","Mexico","Malaysia","Madagascar","Japan","Italy","Iraq","India","Germany","France","Egypt","China","Chile","Canada","Brazil","Bahrain","Australia","Argentina"};

	protected final List<String> strInputList = Arrays.asList(strInputArray);

	protected final List<String> strOutputList = Arrays.asList(strOutputArray);

	protected final List<String> strOutputListReverse = Arrays.asList(strOutputArrayReverse);



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
