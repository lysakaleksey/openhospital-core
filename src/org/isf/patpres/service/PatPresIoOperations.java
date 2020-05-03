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
@Transactional(rollbackFor = OHServiceException.class)
@TranslateOHServiceException
public class PatPresIoOperations {

	@Autowired
	private PatPresIoOperationRepository repository;

	public ArrayList<PatientPresentation> getPatientPresentation(boolean minusOneWeek) throws OHServiceException {
		GregorianCalendar timeTo = new GregorianCalendar();
		GregorianCalendar timeFrom = new GregorianCalendar();

		if (minusOneWeek) {
			timeFrom.add(GregorianCalendar.WEEK_OF_YEAR, -1);
		}

		return getPatientPresentation(-1, null, timeFrom, timeTo, null, null, null, null);
	}

	public ArrayList<PatientPresentation> getPatientPresentation(int patId, String patName, GregorianCalendar dateFrom, GregorianCalendar dateTo,
																 String referredFrom, String specificSymptoms, String prescribed, String referredTo) {
		ArrayList<Integer> patPresCodes = (ArrayList<Integer>) repository.findAllByFilter(patId, patName, dateFrom, dateTo, referredFrom, specificSymptoms, prescribed, referredTo);

		ArrayList<PatientPresentation> resultList = new ArrayList<PatientPresentation>(patPresCodes.size());
		for (int i = 0; i < patPresCodes.size(); i++) {
			Integer code = patPresCodes.get(i);
			PatientPresentation patientPresentation = repository.findOne(code);

			resultList.add(i, patientPresentation);
		}

		return resultList;
	}

	/**
	 * inserts a {@link PatientPresentation} in the DB
	 *
	 * @param patPres - the {@link PatientPresentation} to insert
	 * @return <code>true</code> if the item has been inserted, <code>false</code> otherwise
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean newPatientPresentation(PatientPresentation patPres) throws OHServiceException {
		boolean result = true;
		PatientPresentation savedPresentation = repository.save(patPres);
		result = (savedPresentation != null);
		return result;
	}

	/**
	 * updates a {@link PatientPresentation}
	 *
	 * @param patPres - the {@link PatientPresentation} to update
	 * @return <code>true</code> if the item has been updated, <code>false</code> otherwise
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean updatePatientPresentation(PatientPresentation patPres) throws OHServiceException {
		boolean result = true;
		PatientPresentation savedPresentation = repository.save(patPres);
		result = (savedPresentation != null);
		return result;
	}

	/**
	 * deletes a {@link PatientPresentation}
	 *
	 * @param patPres - the {@link PatientPresentation} to delete
	 * @return <code>true</code> if the item has been deleted, <code>false</code> otherwise
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean deletePatientPresentation(PatientPresentation patPres) throws OHServiceException {
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
	public boolean isCodePresent(Integer code) throws OHServiceException {
		boolean result = true;
		result = repository.exists(code);
		return result;
	}
}
