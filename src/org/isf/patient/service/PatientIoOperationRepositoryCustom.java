package org.isf.patient.service;


import org.isf.patient.model.Patient;

import java.util.Date;
import java.util.List;


public interface PatientIoOperationRepositoryCustom {

	List<Integer> findAllByHeightAndWeight(String regex);

	List<Patient> findAllByFilter(String firstName, String secondName, Date birthDate, char sex);
	
}
