package org.isf.bsunit.test;

import org.isf.bsunit.model.BsUnit;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;

import java.util.List;

public class TestBsUnitContext {
	private static List<BsUnit> savedBsUnit;

	@SuppressWarnings("unchecked")
	public void saveAll(DbJpaUtil jpa) throws OHException {
		jpa.beginTransaction();
		jpa.createQuery("SELECT * FROM BSUNIT", BsUnit.class, false);
		savedBsUnit = (List<BsUnit>) jpa.getList();
		jpa.commitTransaction();
	}

	public List<BsUnit> getAllSaved() throws OHException {
		return savedBsUnit;
	}

	@SuppressWarnings("unchecked")
	public void deleteNews(DbJpaUtil jpa) throws OHException {
		jpa.beginTransaction();
		jpa.createQuery("SELECT * FROM BSUNIT", BsUnit.class, false);
		List<BsUnit> BsUnits = (List<BsUnit>) jpa.getList();
		for (BsUnit bsUnit : BsUnits) {
			int index = savedBsUnit.indexOf(bsUnit);

			if (index == -1) {
				jpa.remove(bsUnit);
			}
		}
		jpa.commitTransaction();
	}
}
