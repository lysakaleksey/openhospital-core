package org.isf.bsunit.test;


import org.isf.bsunit.model.BsUnit;
import org.isf.utils.exception.OHException;

import static org.junit.Assert.assertEquals;

public class TestBsUnit {
	private final String code = "xyz/l";
	private final String description = "X.Y.Z";

	public BsUnit setup(boolean usingSet) throws OHException {
		BsUnit bsUnit;

		if (usingSet) {
			bsUnit = new BsUnit();
			_setParameters(bsUnit);
		} else {
			// Create BsUnit with all parameters
			bsUnit = new BsUnit(code, description);
		}

		return bsUnit;
	}

	public void _setParameters(BsUnit bsUnit) {
		bsUnit.setCode(code);
		bsUnit.setDescription(description);
	}

	public void check(BsUnit bsUnit) {
		assertEquals(code, bsUnit.getCode());
		assertEquals(description, bsUnit.getDescription());
	}
}
