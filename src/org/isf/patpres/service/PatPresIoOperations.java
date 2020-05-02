package org.isf.patpres.service;

import org.isf.patpres.model.PatientPresentation;
import org.isf.utils.db.TranslateOHServiceException;
import org.isf.utils.exception.OHServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.GregorianCalendar;

@Service
@Transactional(rollbackFor=OHServiceException.class)
@TranslateOHServiceException
public class PatPresIoOperations {

	@Autowired
	private PatPresIoOperationRepository repository;
	

	/**
	 * inserts a {@link PatientPresentation} in the DB
	 * 
	 * @param patPres - the {@link PatientPresentation} to insert
	 * @return <code>true</code> if the item has been inserted, <code>false</code> otherwise 
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean newPatientPresentation(PatientPresentation patPres) throws OHServiceException
	{
		boolean result = true;
		PatientPresentation savedVaccine = repository.save(patPres);
		result = (savedVaccine != null);
		return result;
	}

	/**
	 * updates a {@link PatientPresentation} 
	 * 
	 * @param patPres - the {@link PatientPresentation} to update
	 * @return <code>true</code> if the item has been updated, <code>false</code> otherwise 
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean updatePatientPresentation(PatientPresentation patPres) throws OHServiceException
	{
		boolean result = true;
		PatientPresentation savedVaccine = repository.save(patPres);
		result = (savedVaccine != null);
		return result;
	}

	/**
	 * deletes a {@link PatientPresentation} 
	 * 
	 * @param patPres - the {@link PatientPresentation} to delete
	 * @return <code>true</code> if the item has been deleted, <code>false</code> otherwise 
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean deletePatientPresentation(PatientPresentation patPres) throws OHServiceException
	{
		boolean result = true;
		repository.delete(patPres);
		return result;
	}

	/**
	 * checks if the code is already in use
	 *
	 * @param code - the patient vaccine code
	 * @return <code>true</code> if the code is already in use, <code>false</code> otherwise
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean isCodePresent(Integer code) throws OHServiceException
	{
		boolean result = true;
		result = repository.exists(code);
		return result;
	}
}
