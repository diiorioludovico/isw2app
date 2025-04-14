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
package com.isw2.it.AgendaTest;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.isw2.it.Agenda.SimpleAgenda;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class SimpleAgendaTest {
	
	private static final String LABEL = "This is a mock appointment for: ";
	private static final String DynamicLABEL = "[Dynamic] ";

	// @Mock annotation is used to declare and create a mock instance
	@Mock
	SimpleAgenda agendaInterface;
	//	as an alternative you can use:
	//	SimpleAgenda agendaInterface = mock(SimpleAgenda.class);


	@Before
	public void createMocksAsReturnType() {
		Date d1 = (new GregorianCalendar(120, 4, 15)).getTime();
		// add the mocked behavior of for a set of given dates
		when(agendaInterface.getAppointment(d1)).thenReturn(LABEL + d1.toString());

		// in the following case the mock will reply for any instance of Date. 
		// If enabled the test will fail because it reacts also for "GregorianCalendar(119, 7, 31)" and "GregorianCalendar(190, 10, 31)"
		// Remember there are no guarantees on the order of execution among the @Before, thus the case "GregorianCalendar(190, 10, 31)" may become overwritten
//		when(agendaInterface.getAppointment(any(Date.class))).thenReturn(LABEL + d1.toString());
	}

	@Before
	public void createMocksSupplyingAMethod() {
		Date dNewOne = (new GregorianCalendar(190, 10, 31)).getTime();
		
		when(agendaInterface.getAppointment(dNewOne)).thenAnswer(new Answer<String>() {
//			@Override
			public String answer(InvocationOnMock arg0) throws Throwable {
				// Here you compute the result as you prefer
				String result = DynamicLABEL;
				Random r = new Random();
				
				if (r.nextInt() % 2 == 0) {
					result += "Sometimes we got this appointment .... ";
				}else {
					result += " ... others no!!!";					
				}
				return result;
			}

	      });
	}

	@Test
	public void aMoreComplexTest() {
		Date d1 = (new GregorianCalendar(120, 4, 15)).getTime();
		Date d2 = (new GregorianCalendar(119, 7, 31)).getTime();
		Date d3 = (new GregorianCalendar(190, 10, 31)).getTime();

		String label1 = agendaInterface.getAppointment(d1);
		String label2 = agendaInterface.getAppointment(d2);
		String label3 = agendaInterface.getAppointment(d3);
		
		boolean condition = (!label1.startsWith(DynamicLABEL)) && (label2 == null) && (label3.startsWith(DynamicLABEL));
		Assert.assertTrue(condition);
	}
}
