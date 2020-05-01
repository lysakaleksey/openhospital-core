package org.isf.tempunit.service;

import org.isf.tempunit.model.TempUnit;
import org.isf.utils.db.TranslateOHServiceException;
import org.isf.utils.exception.OHServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Persistence class for the TempUnit module.
 */
@Service
@Transactional(rollbackFor = OHServiceException.class)
@TranslateOHServiceException
public class TempUnitIoOperation {

	@Autowired
	private TempUnitIoOperationRepository repository;

	/**
	 * Returns all the stored {@link TempUnit}s.
	 *
	 * @return a list of temperature unit.
	 * @throws OHServiceException if an error occurs retrieving the diseases list.
	 */
	public ArrayList<TempUnit> getTempUnits() throws OHServiceException {
		return new ArrayList<TempUnit>(repository.findAllByOrderByDescriptionAsc());
	}

	/**
	 * Updates the specified {@link TempUnit}.
	 *
	 * @param tempUnit the temperature unit to update.
	 * @return <code>true</code> if the temperature unit has been updated, false otherwise.
	 * @throws OHServiceException if an error occurs during the update operation.
	 */
	public boolean updateTempUnit(TempUnit tempUnit) throws OHServiceException {
		TempUnit savedTempUnit = repository.save(tempUnit);
		return (savedTempUnit != null);
	}

	/**
	 * Store the specified {@link TempUnit}.
	 *
	 * @param tempUnit the temperature unit to store.
	 * @return <code>true</code> if the {@link TempUnit} has been stored, <code>false</code> otherwise.
	 * @throws OHServiceException if an error occurs during the store operation.
	 */
	public boolean newTempUnit(TempUnit tempUnit) throws OHServiceException {
		boolean result = true;

		TempUnit savedTempUnit = repository.save(tempUnit);
		result = (savedTempUnit != null);

		return result;
	}

	/**
	 * Deletes the specified {@link TempUnit}.
	 *
	 * @param tempUnit the temperature unit to remove.
	 * @return <code>true</code> if the disease has been removed, <code>false</code> otherwise.
	 * @throws OHServiceException if an error occurs during the delete procedure.
	 */
	public boolean deleteTempUnit(TempUnit tempUnit) throws OHServiceException {
		repository.delete(tempUnit);
		return true;
	}

	/**
	 * Checks if the specified code is already used by any {@link TempUnit}.
	 *
	 * @param code the code to check.
	 * @return <code>true</code> if the code is used, false otherwise.
	 * @throws OHServiceException if an error occurs during the check.
	 */
	public boolean isCodePresent(String code) throws OHServiceException {
		return repository.exists(code);
	}
}
