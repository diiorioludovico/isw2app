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
package com.isw2.it.Agenda.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.isw2.it.Agenda.SimpleAgenda;

public class MyAgenda implements SimpleAgenda {

	private Map<String, String> appointments;
	
	public List<String> getAppointments() {
		List<String> lst = new ArrayList<String>();
		for (String key : this.appointments.keySet()) {
			String label = this.appointments.get(key);
			lst.add(label);
		}

		return lst;
	}

	public String getAppointment(Date d) {
		String dateString = d.toString();
		String app = this.appointments.get(dateString);
		
		return app;
	}

	public void addAppointment(Date d, String label) {
		System.err.println("Reserving " + label + " on " + d.toString());
		
		// TODO Auto-generated method stub
		// This behavior is not implemented yet in this class
		// Uncomment the following lines in case you would like to enable it,
		// but notice that the test class AgendaTest injects mock on the Map at the attribute: this.appointments
		// thus even in this case the this.appointments.put will be ignored 
//		this.appointments.put(d.toString(), label);
//		System.out.println(this.appointments.get(d.toString()));
	}

}
