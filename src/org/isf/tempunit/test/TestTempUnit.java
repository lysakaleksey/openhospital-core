package org.isf.tempunit.test;

import org.isf.tempunit.model.TempUnit;
import org.isf.utils.exception.OHException;

import static org.junit.Assert.assertEquals;

public class TestTempUnit {
	private final String code = "T";
	private final String description = "9.TEST";

	public TempUnit setup(boolean usingSet) throws OHException {
		TempUnit tempUnit;

		if (usingSet) {
			tempUnit = new TempUnit();
			_setParameters(tempUnit);
		} else {
			// Create BsUnit with all parameters
			tempUnit = new TempUnit(code, description);
		}

		return tempUnit;
	}

	public void _setParameters(TempUnit tempUnit) {
		tempUnit.setCode(code);
		tempUnit.setDescription(description);
	}

	public void check(TempUnit tempUnit) {
		assertEquals(code, tempUnit.getCode());
		assertEquals(description, tempUnit.getDescription());
	}
}
