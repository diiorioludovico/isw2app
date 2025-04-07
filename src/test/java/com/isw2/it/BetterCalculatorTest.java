package com.isw2.it;

/*
 *    Copyright (C) 2025 Guglielmo De Angelis (a.k.a. Gulyx)
 *    
 *    This file is part of the contents developed for the course
 * 	  IS2 (A.Y. 2024-2025) at Università di Tor Vergata in Rome. 
 *
 *    This is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as 
 *    published by the Free Software Foundation, either version 3 of the 
 *    License, or (at your option) any later version.
 *
 *    This software is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this source.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.isw2.it.Calculator;

public class BetterCalculatorTest {	
	private static Random r = null;
	private static Set<Integer> usedValues;
	private int rndValue;
	private Calculator calculator;

	@BeforeClass
	public static void configureRandomGenerator(){
		BetterCalculatorTest.r = new Random();
		BetterCalculatorTest.usedValues = new HashSet<Integer>();
	}

	@AfterClass
	public static void cleanUpEnvironmentAndReport(){
		System.out.print("The following random seeds have been generated: ");
		for (int i : BetterCalculatorTest.usedValues) {
			System.out.print(i + "; ");
		}
		BetterCalculatorTest.usedValues.clear();
	}

	@Before
	public void configureTheEnvironment(){
		this.calculator = new Calculator();

		do {
			this.rndValue = BetterCalculatorTest.r.nextInt(Integer.MAX_VALUE);
		}
		while (BetterCalculatorTest.usedValues.contains(rndValue));
	}

	@After
	public void releaseResources(){
		BetterCalculatorTest.usedValues.add(this.rndValue);	
	}

	@Test
	public void testAdd() {
		double result = this.calculator.add(10, 50);
		Assert.assertEquals(60, result, 0);
	}

	@Test
	public void testFooOdd() {
		int oddValue = (this.rndValue/2)*2-1;
		int result = this.calculator.foo(oddValue);
		Assert.assertEquals(oddValue, result);
	}

	@Test
	public void testFooEven() {
		int evenValue = (this.rndValue/2)*2;
		int result = this.calculator.foo(evenValue);
		int expectedResult = evenValue/2;
		Assert.assertEquals(expectedResult, result);
	}

}
