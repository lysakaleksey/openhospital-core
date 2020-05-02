package org.isf.patpres.manager;

import org.isf.generaldata.MessageBundle;
import org.isf.patpres.model.PatientPresentation;
import org.isf.patpres.service.PatPresIoOperations;
import org.isf.utils.exception.OHDataValidationException;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.utils.exception.model.OHSeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatPresManager {

	@Autowired
	private PatPresIoOperations ioOperations;

	/**
	 * inserts a {@link PatientPresentation} in the DB
	 *
	 * @param patPres - the {@link PatientPresentation} to insert
	 * @return <code>true</code> if the item has been inserted, <code>false</code> otherwise
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean newPatientPresentation(PatientPresentation patPres) throws OHServiceException {
		validatePatientPresentation(patPres);
		return ioOperations.newPatientPresentation(patPres);
	}

	/**
	 * updates a {@link PatientPresentation}
	 *
	 * @param patPres - the {@link PatientPresentation} to update
	 * @return <code>true</code> if the item has been updated, <code>false</code> otherwise
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean updatePatientPresentation(PatientPresentation patPres) throws OHServiceException {
		validatePatientPresentation(patPres);
		return ioOperations.updatePatientPresentation(patPres);
	}

	/**
	 * deletes a {@link PatientPresentation}
	 *
	 * @param patPres - the {@link PatientPresentation} to delete
	 * @return <code>true</code> if the item has been deleted, <code>false</code> otherwise
	 * @throws OHServiceException Exception it might throw
	 */
	public boolean deletePatientPresentation(PatientPresentation patPres) throws OHServiceException {
		return ioOperations.deletePatientPresentation(patPres);
	}

	/**
	 * Verify if the object is valid for CRUD and return a list of errors, if any
	 *
	 * @param patientPresentation Patient Presentation instance
	 * @throws OHDataValidationException Exception it might throw
	 */
	protected void validatePatientPresentation(PatientPresentation patientPresentation) throws OHDataValidationException {
		List<OHExceptionMessage> errors = new ArrayList<OHExceptionMessage>();

		if (patientPresentation.getPresentationDate() == null) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"),
				MessageBundle.getMessage("angal.patpres.pleaseinsertpresentationdate"),
				OHSeverityLevel.ERROR));
		}
		if (patientPresentation.getPatient() == null
			|| StringUtils.isEmpty(patientPresentation.getPatient().getName())
			|| StringUtils.isEmpty(String.valueOf(patientPresentation.getPatient().getSex()))) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"),
				MessageBundle.getMessage("angal.patpres.pleaseselectapatient"),
				OHSeverityLevel.ERROR));
		}
		if (!errors.isEmpty()) {
			throw new OHDataValidationException(errors);
		}
	}
}

