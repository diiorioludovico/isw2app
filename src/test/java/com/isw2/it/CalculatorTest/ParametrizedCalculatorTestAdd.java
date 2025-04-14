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
public class ParametrizedCalculatorTestAdd {

	private double expected;
	private double valueOne; 
	private double valueTwo; 

	@Parameters
	public static Collection<Integer[]> getParameters(){
		return Arrays.asList(new Integer[][]{
				{2, 1, 1}, // expected, valueOne, valueTwo
				{3, 2, 1}, // expected, valueOne, valueTwo
				{4, 3, 1}, // expected, valueOne, valueTwo
		});
	}

	public ParametrizedCalculatorTestAdd(double expected, double valueOne, double valueTwo){
		this.expected = expected;
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}
	
	@Test
	public void sumTest() {
		Calculator calculator = new Calculator();
		double result = calculator.add(this.valueOne, this.valueTwo);
		Assert.assertEquals(this.expected, result, 0);
	}

}
