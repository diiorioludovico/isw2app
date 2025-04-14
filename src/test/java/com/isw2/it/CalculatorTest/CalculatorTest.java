package com.isw2.it.CalculatorTest;

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

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.isw2.it.Calculator.Calculator;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		double result = calculator.add(10, 50);
		Assert.assertEquals(60, result, 0);
	}

	@Test
	public void testFooOdd() {
		Calculator calculator = new Calculator();
		Random r = new Random();
		int oddValue = r.nextInt(Integer.MAX_VALUE/2)*2-1;
		int result = calculator.foo(oddValue);
		Assert.assertEquals(oddValue, result);
	}

	@Test
	public void testFooEven() {
		Calculator calculator = new Calculator();
		Random r = new Random();
		int evenValue = r.nextInt(Integer.MAX_VALUE/2)*2;
		int result = calculator.foo(evenValue);
		int expectedResult = evenValue/2;
		Assert.assertEquals(expectedResult, result);
	}

}
