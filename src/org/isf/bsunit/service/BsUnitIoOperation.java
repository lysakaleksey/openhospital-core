package org.isf.bsunit.service;

import org.isf.bsunit.model.BsUnit;
import org.isf.utils.db.TranslateOHServiceException;
import org.isf.utils.exception.OHServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Persistence class for the BsUnit module.
 */
@Service
@Transactional(rollbackFor = OHServiceException.class)
@TranslateOHServiceException
public class BsUnitIoOperation {

	@Autowired
	private BsUnitIoOperationRepository repository;

	/**
	 * Returns all the stored {@link BsUnit}s.
	 *
	 * @return a list of blood sugar unit.
	 * @throws OHServiceException if an error occurs retrieving the diseases list.
	 */
	public ArrayList<BsUnit> getBsUnits() throws OHServiceException {
		return new ArrayList<BsUnit>(repository.findAllByOrderByDescriptionAsc());
	}

	/**
	 * Updates the specified {@link BsUnit}.
	 *
	 * @param bsUnit the blood sugar unit to update.
	 * @return <code>true</code> if the blood sugar unit has been updated, false otherwise.
	 * @throws OHServiceException if an error occurs during the update operation.
	 */
	public boolean updateBsUnit(BsUnit bsUnit) throws OHServiceException {
		BsUnit savedBsUnit = repository.save(bsUnit);
		return (savedBsUnit != null);
	}

	/**
	 * Store the specified {@link BsUnit}.
	 *
	 * @param bsUnit the blood sugar unit to store.
	 * @return <code>true</code> if the {@link BsUnit} has been stored, <code>false</code> otherwise.
	 * @throws OHServiceException if an error occurs during the store operation.
	 */
	public boolean newBsUnit(BsUnit bsUnit) throws OHServiceException {
		boolean result = true;

		BsUnit savedBsUnit = repository.save(bsUnit);
		result = (savedBsUnit != null);

		return result;
	}

	/**
	 * Deletes the specified {@link BsUnit}.
	 *
	 * @param bsUnit the blood sugar unit to remove.
	 * @return <code>true</code> if the disease has been removed, <code>false</code> otherwise.
	 * @throws OHServiceException if an error occurs during the delete procedure.
	 */
	public boolean deleteBsUnit(BsUnit bsUnit) throws OHServiceException {
		repository.delete(bsUnit);
		return true;
	}

	/**
	 * Checks if the specified code is already used by any {@link BsUnit}.
	 *
	 * @param code the code to check.
	 * @return <code>true</code> if the code is used, false otherwise.
	 * @throws OHServiceException if an error occurs during the check.
	 */
	public boolean isCodePresent(String code) throws OHServiceException {
		return repository.exists(code);
	}
}
