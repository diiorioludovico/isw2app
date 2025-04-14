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

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.isw2.it.Calculator.Calculator;

@RunWith(value=Parameterized.class)
public class ParametrizedCalculatorTestFoo {

	private int expectedResult;
	private int par; 

	
	@Parameters
	public static Collection<Integer[]> getOtherParameters(){
		return Arrays.asList(new Integer[][]{
				{1, 2}, // expectedResult, par
				{3, 3}, // expectedResult, par
				{-2, -4}, // expectedResult, par
				{0, 0}, // expectedResult, par
				{-17, -17}, // expectedResult, par
		});
	}

	public ParametrizedCalculatorTestFoo(int expectedResult, int par){
		this.expectedResult = expectedResult;
		this.par = par;
	}

	@Test
	public void testFoo() {
		Calculator calculator = new Calculator();
		int result = calculator.foo(this.par);
		Assert.assertEquals(this.expectedResult, result);
	}

}
