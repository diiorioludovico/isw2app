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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.isw2.it.Agenda.SimpleAgenda;
import com.isw2.it.Agenda.impl.MyAgenda;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class AgendaTest {

	private static final String LABEL = "This is a mock appointment for: ";

	// @InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	SimpleAgenda agenda = new MyAgenda();

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	Map<String, String> mockedAppointments;

	
	
	@SuppressWarnings("unchecked")
	public AgendaTest() {
		Map<String, String> foo = new HashMap<String, String>();
		
		foo.put("Cane","Pluto");
		int sizeBefore = foo.size();
		
		foo = Mockito.mock(Map.class);
		int sizeAfter = foo.size();
		
		Assert.assertEquals(1, sizeBefore);
		Assert.assertEquals(0, sizeAfter);
	}
	
	@Before
	public void createMocks() {
		Date d1 = (new GregorianCalendar(120, 4, 15)).getTime();
		Date d2 = (new GregorianCalendar(119, 7, 31)).getTime();

		String key;

		key = d1.toString();
		// add the mocked behavior of for a set of given dates
		when(mockedAppointments.get(key)).thenReturn(LABEL + key);

		key = d2.toString();
		// 1. add the mocked behavior of for a set of given dates.
		// 2. Strict stubbing that requires that all declared stubs must be actually used during the tests.
		//	  If not, a specific exception will be thrown (i.e. UnnecessaryStubbingException). 
		//    The statement lenient() relax this requirement. Check the manual. 
		lenient().when(mockedAppointments.get(key)).thenReturn(LABEL + key);

		when(mockedAppointments.size()).thenReturn(2);
	}

	@Test
	public void mockTest() {
		for (String key : mockedAppointments.keySet()) {
			@SuppressWarnings("unused")
			String v = mockedAppointments.get(key);
// Do not worry, we will never reach this line. We are querying the object on 
// method that was not mocked (i.e. keySet).			
			Assert.fail();
		}

		int size = mockedAppointments.size();

		Assert.assertEquals(2, size);
	}

	@Test
	public void simpleTest() {
		Date dx = (new GregorianCalendar(222, 1, 15)).getTime();
		agenda.addAppointment(dx, "gulyx");
		int appCounter = agenda.getAppointments().size();
// Do not expect that appCounter is 1 or 2 (or more in general different than 0) ... 
// we are actually querying an object that refers a mocked instance!!!
// See the details of the implementation of the method: MyAgenda.getAppointments()
		Assert.assertEquals(0, appCounter);
	}

	@Test
	public void anotherSimpleTest() {
		Date dMocked = (new GregorianCalendar(120, 4, 15)).getTime();
		Date dNotMocked = (new GregorianCalendar(0, 1, 1)).getTime();

		String labelMocked = agenda.getAppointment(dMocked);
		String labelNotMocked = agenda.getAppointment(dNotMocked);

		boolean condition = labelMocked.equals(LABEL + dMocked.toString()) && (labelNotMocked == null);
		Assert.assertTrue(condition);
	}

	@Test
	public void verifyTest() {
//	Mockito.verify checks if certain behavior happened once

		boolean verified1 = true;
		boolean verified2 = true;
		boolean verified3 = true;
		boolean verified4 = true;

		Date dMocked = (new GregorianCalendar(120, 4, 15)).getTime();
		Date dNotMocked = (new GregorianCalendar(0, 1, 1)).getTime();

		
		mockedAppointments.put(dMocked.toString(), "appointment");
		
//		Verification on a mock with an assigned value but never used
		try {
			verify(mockedAppointments).get(dMocked.toString());
		} catch (Throwable e) {
			verified1 = false;
		}

//		Verification on a mock with an assigned value used once
		try {
			mockedAppointments.get(dMocked.toString());
			verify(mockedAppointments).get(dMocked.toString());
		} catch (Throwable e) {
			verified2 = false;
		}

//		Verification on a mock with an unassigned value but never used
		try {
			verify(mockedAppointments).get(dNotMocked.toString());
		} catch (Throwable e) {
			verified3 = false;
		}

//		Verification on a mock with an unassigned value used once
		try {
			mockedAppointments.get(dNotMocked.toString());
			verify(mockedAppointments).get(dNotMocked.toString());
		} catch (Throwable e) {
			verified4 = false;
		}

		boolean condition = (!verified1) && (verified2) && (!verified3) && (verified4);
		Assert.assertTrue(condition);
	}
}
