package org.isf.bsunit.manager;

import org.isf.bsunit.model.BsUnit;
import org.isf.bsunit.service.BsUnitIoOperation;
import org.isf.generaldata.MessageBundle;
import org.isf.utils.exception.OHDataValidationException;
import org.isf.utils.exception.OHServiceException;
import org.isf.utils.exception.model.OHExceptionMessage;
import org.isf.utils.exception.model.OHSeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager class for BsUnit module.
 */
@Component
public class BsUnitBrowserManager {

	@Autowired
	private BsUnitIoOperation ioOperations;

	/**
	 * Returns all the stored {@link BsUnit}s.
	 *
	 * @return a list of blood sugar unit, <code>null</code> if the operation is failed.
	 * @throws OHServiceException
	 */
	public ArrayList<BsUnit> getBsUnit() throws OHServiceException {
		return ioOperations.getBsUnits();
	}

	/**
	 * Store the specified {@link BsUnit}.
	 *
	 * @param bsUnit the blood sugar unit to store.
	 * @return <code>true</code> if the {@link BsUnit} has been stored, <code>false</code> otherwise.
	 * @throws OHServiceException
	 */
	public boolean newBsUnit(BsUnit bsUnit) throws OHServiceException {
		validateBsUnit(bsUnit, true);
		return ioOperations.newBsUnit(bsUnit);
	}

	/**
	 * Updates the specified {@link BsUnit}.
	 *
	 * @param bsUnit the blood sugar unit to update.
	 * @return <code>true</code> if the blood sugar unit has been updated, false otherwise.
	 * @throws OHServiceException
	 */
	public boolean updateBsUnit(BsUnit bsUnit) throws OHServiceException {
		validateBsUnit(bsUnit, false);
		return ioOperations.updateBsUnit(bsUnit);
	}

	/**
	 * Checks if the specified code is already used by any {@link BsUnit}.
	 *
	 * @param code the code to check.
	 * @return <code>true</code> if the code is used, false otherwise.
	 * @throws OHServiceException
	 */
	public boolean codeControl(String code) throws OHServiceException {
		return ioOperations.isCodePresent(code);
	}

	/**
	 * Deletes the specified {@link BsUnit}.
	 *
	 * @param bsUnit the blood sugar unit to remove.
	 * @return <code>true</code> if the unit has been removed, <code>false</code> otherwise.
	 * @throws OHServiceException
	 */
	public boolean deleteBsUnit(BsUnit bsUnit) throws OHServiceException {
		return ioOperations.deleteBsUnit(bsUnit);
	}

	/**
	 * Verify if the object is valid for CRUD and return a list of errors, if any
	 *
	 * @param bsUnit
	 * @param insert <code>true</code> or updated <code>false</code>
	 * @throws OHServiceException
	 */
	protected void validateBsUnit(BsUnit bsUnit, boolean insert) throws OHServiceException {
		List<OHExceptionMessage> errors = new ArrayList<OHExceptionMessage>();
		String key = bsUnit.getCode();
		if (key.equals("")) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.bsunit.pleaseinsertacode"),
				OHSeverityLevel.ERROR));
		}
		if (key.length() > 10) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.bsunit.codetoolongmaxchars"),
				OHSeverityLevel.ERROR));
		}

		if (insert) {
			if (codeControl(key)) {
				errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.common.codealreadyinuse"),
					OHSeverityLevel.ERROR));
			}
		}
		if (bsUnit.getDescription() == null || bsUnit.getDescription().equals("")) {
			errors.add(new OHExceptionMessage(MessageBundle.getMessage("angal.hospital"), MessageBundle.getMessage("angal.bsunit.pleaseinsertavaliddescription"),
				OHSeverityLevel.ERROR));
		}
		if (!errors.isEmpty()) {
			throw new OHDataValidationException(errors);
		}
	}
}
