package org.isf.tempunit.manager;

import org.isf.generaldata.MessageBundle;
import org.isf.tempunit.model.TempUnit;
import org.isf.tempunit.service.TempUnitIoOperation;
import org.isf.utils.exception.OHDataValidationException;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.utils.exception.model.OHSeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager class for TempUnit module.
 */
@Component
public class TempUnitBrowserManager {

	@Autowired
	private TempUnitIoOperation ioOperations;

	/**
	 * Returns all the stored {@link TempUnit}s.
	 *
	 * @return a list of temperature unit, <code>null</code> if the operation is failed.
	 * @throws OHServiceException
	 */
	public ArrayList<TempUnit> getTempUnit() throws OHServiceException {
		return ioOperations.getTempUnits();
	}

	/**
	 * Store the specified {@link TempUnit}.
	 *
	 * @param tempUnit the temperature unit to store.
	 * @return <code>true</code> if the {@link TempUnit} has been stored, <code>false</code> otherwise.
	 * @throws OHServiceException
	 */
	public boolean newTempUnit(TempUnit tempUnit) throws OHServiceException {
		validateTempUnit(tempUnit, true);
		return ioOperations.newTempUnit(tempUnit);
	}

	/**
	 * Updates the specified {@link TempUnit}.
	 *
	 * @param tempUnit the temperature unit to update.
	 * @return <code>true</code> if the temperature unit has been updated, false otherwise.
	 * @throws OHServiceException
	 */
	public boolean updateTempUnit(TempUnit tempUnit) throws OHServiceException {
		validateTempUnit(tempUnit, false);
		return ioOperations.updateTempUnit(tempUnit);
	}

	/**
	 * Checks if the specified code is already used by any {@link TempUnit}.
	 *
	 * @param code the code to check.
	 * @return <code>true</code> if the code is used, false otherwise.
	 * @throws OHServiceException
	 */
	public boolean codeControl(String code) throws OHServiceException {
		return ioOperations.isCodePresent(code);
	}

	/**
	 * Deletes the specified {@link TempUnit}.
	 *
	 * @param tempUnit the temperature unit to remove.
	 * @return <code>true</code> if the unit has been removed, <code>false</code> otherwise.
	 * @throws OHServiceException
	 */
	public boolean deleteTempUnit(TempUnit tempUnit) throws OHServiceException {
		return ioOperations.deleteTempUnit(tempUnit);
	}

	/**
	 * Verify if the object is valid for CRUD and return a list of errors, if any
	 *
	 * @param tempUnit
	 * @param insert   <code>true</code> or updated <code>false</code>
	 * @throws OHServiceException
	 */
	protected void validateTempUnit(TempUnit tempUnit, boolean insert) throws OHServiceException {
		List<OHExceptionMessage> errors = new ArrayList<OHExceptionMessage>();
		String key = tempUnit.getCode();
		if (key.equals("")) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.tempunit.pleaseinsertacode"),
				OHSeverityLevel.ERROR));
		}
		if (key.length() > 2) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.tempunit.codetoolongmaxchars"),
				OHSeverityLevel.ERROR));
		}

		if (insert) {
			if (codeControl(key)) {
				errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.common.codealreadyinuse"),
					OHSeverityLevel.ERROR));
			}
		}
		if (tempUnit.getDescription() == null || tempUnit.getDescription().equals("")) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.tempunit.pleaseinsertavaliddescription"),
				OHSeverityLevel.ERROR));
		}
		if (!errors.isEmpty()) {
			throw new OHDataValidationException(errors);
		}
	}
}
